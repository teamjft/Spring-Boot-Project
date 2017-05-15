package com.lms.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.factory.PaymentFactory;
import com.lms.config.security.SecUser;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;
import com.lms.models.PaymentInstrument;
import com.lms.services.membership.MembershipService;
import com.lms.services.membershipplan.MembershipPlanService;
import com.lms.services.payment.PaymentService;
import com.lms.services.paymentinstrument.PaymentInstrumentService;
import com.lms.utils.beans.MembershipPlanBean;
import com.lms.utils.beans.OrderCartBean;
import com.lms.utils.beans.PaymentInstrumentBean;
import com.lms.utils.helper.SecurityUtil;
import com.lms.utils.paymentbuilder.PaymentRequest;
import com.lms.utils.paymentbuilder.Response;
import com.lms.utils.paymentbuilder.paypalcreditcard.PapalCreditCardBuilder;
import com.lms.utils.paymentbuilder.paypalcreditcard.PapalCreditRequest;
import com.paypal.api.payments.Error;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Created by bhushan on 11/5/17.
 */
@Controller
@RequestMapping(value = "/payment")
@PreAuthorize("isAuthenticated()")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentInstrumentService paymentInstrumentService;
    @Autowired
    private MembershipPlanService membershipPlanService;
    @Autowired
    private PaymentFactory paymentFactory;
    @Autowired
    private MembershipService membershipService;



    @RequestMapping(value = "/create")
    public ModelAndView paymentCreate(@Valid @ModelAttribute("cart") OrderCartBean cartBean, BindingResult result) {
        if (cartBean.getPlanUuid() == null) {
            return new ModelAndView("redirect:/");
        }
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        MembershipPlan membershipPlan =  membershipPlanService.findByUuidAndLibrary(cartBean.getPlanUuid(), memberShip.getLibrary());
        if(result.hasErrors()) {

            if (membershipPlan == null) {
                return new ModelAndView("forward:/");
            }
            List<ObjectError> errors = result.getAllErrors();
            ModelAndView modelAndView = new ModelAndView("plan/purchase");
            modelAndView.addObject("plan", membershipPlan);
            OrderCartBean orderCartBean = OrderCartBean.builder().planUuid(membershipPlan.getUuid()).quantity(1).build();
            modelAndView.addObject("cart", orderCartBean);
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }

        PaymentInstrument paymentInstrument = paymentInstrumentService.findFirstEnableTrue();
        PaymentInstrumentBean paymentInstrumentBean;
        if (paymentInstrument != null) {
            paymentInstrumentBean = PaymentInstrumentBean.buildEntityToBean(paymentInstrument);
        } else {
            paymentInstrumentBean = new PaymentInstrumentBean();
        }
        paymentInstrumentBean.setMembershipPlanId(membershipPlan.getUuid());
        paymentInstrumentBean.setOrderCart(cartBean);
        paymentInstrumentBean.setMembershipPlanId(membershipPlan.getUuid());
        ModelAndView modelAndView = new ModelAndView("payment/create");
        modelAndView.addObject("paymentDetails", paymentInstrumentBean);
        return modelAndView;
    }

    @RequestMapping(value = "/pay")
    public ModelAndView pay( @Valid @ModelAttribute("paymentDetails")PaymentInstrumentBean paymentInstrumentBean, BindingResult result) {
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("payment/create");
            modelAndView.addObject("paymentDetails", paymentInstrumentBean);
            return modelAndView;
        }
        PaymentInstrument paymentInstrument =  PaymentInstrumentBean.buildBeanToEntity(paymentInstrumentBean);
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        paymentInstrument.setUser(memberShip.getUser());
        MembershipPlan membershipPlan = membershipPlanService.findByUuid(paymentInstrumentBean.getMembershipPlanId());
        MembershipPlanBean membershipPlanBean = MembershipPlanBean.buildEntityToBean(membershipPlan);
        PaymentService paymentService = paymentFactory.getPaymentService(paymentInstrumentBean.getPaymentMethod());
        BigDecimal ammount = paymentInstrumentService.calculateTotalPrice(membershipPlanBean, paymentInstrumentBean.getOrderCart().getQuantity());
        /*TODO move it with factory*/
        PaymentRequest<PapalCreditRequest> paymentRequest
                = new PapalCreditCardBuilder(paymentInstrumentBean, membershipPlanBean, ammount);

        try {
            Response response = paymentService.pay(paymentRequest);
        } catch (Exception e) {
            log.error("Error occur during payment for user uuid: {} membership uuid {} subscription {}");
            if (e instanceof PayPalRESTException) {
                Error error = ((PayPalRESTException) e).getDetails();
                ModelAndView modelAndView = new ModelAndView("payment/create");
                modelAndView.addObject("errorDetails", error.getDetails());
                modelAndView.addObject("paymentDetails", paymentInstrumentBean);
                return modelAndView;
            }
        }


        return new ModelAndView("payment/create", "paymentDetails", paymentInstrumentBean);
    }


}
