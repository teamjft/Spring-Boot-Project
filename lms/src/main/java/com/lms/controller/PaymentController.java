package com.lms.controller;

import static com.lms.utils.constants.UrlMappingConstant.CREATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.PAYMENT_BASE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.PAYMENT_PAY_PATH;
import static com.lms.utils.constants.ViewConstant.MEMBERSHIP_PLAN_PURCHASE_VIEW;
import static com.lms.utils.constants.ViewConstant.PAYMENT_CREATE_VIEW;
import static com.lms.utils.constants.ViewConstant.PAYMENT_SUCCESS_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_HOME_VIEW;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.factory.PaymentFactory;
import com.lms.config.security.SecUser;
import com.lms.config.security.SecurityService;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;
import com.lms.models.PaymentInstrument;
import com.lms.services.invoice.InvoiceService;
import com.lms.services.membership.MembershipService;
import com.lms.services.membershipplan.MembershipPlanService;
import com.lms.services.transaction.PaymentTransactionService;
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
@RequestMapping(value = PAYMENT_BASE_PATH)
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
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SecurityService securityService;

    @Value("${lcm.customer.support.email}")
    private String supportEmail;


    @RequestMapping(value = CREATE_PATH)
    @PreAuthorize("@userMembershipSecurityServiceImpl.canUserBuyPlan()")
    public ModelAndView paymentCreate(@Valid @ModelAttribute("cart") OrderCartBean cartBean, BindingResult result) {
        if (cartBean.getPlanUuid() == null) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        MembershipPlan membershipPlan =  membershipPlanService.findByUuidAndLibrary(cartBean.getPlanUuid(), memberShip.getLibrary());
        if(result.hasErrors()) {

            if (membershipPlan == null) {
                return new ModelAndView(REDIRECT_HOME_VIEW);
            }
            List<ObjectError> errors = result.getAllErrors();
            ModelAndView modelAndView = new ModelAndView(MEMBERSHIP_PLAN_PURCHASE_VIEW);
            modelAndView.addObject("plan", membershipPlan);
            OrderCartBean orderCartBean = OrderCartBean.builder().planUuid(membershipPlan.getUuid()).quantity(1).build();
            modelAndView.addObject("cart", orderCartBean);
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }

        PaymentInstrument paymentInstrument = paymentInstrumentService.findFirstEnableTrue(memberShip.getUser());
        PaymentInstrumentBean paymentInstrumentBean;
        if (paymentInstrument != null) {
            paymentInstrumentBean = PaymentInstrumentBean.buildEntityToBean(paymentInstrument);
        } else {
            paymentInstrumentBean = new PaymentInstrumentBean();
        }
        paymentInstrumentBean.setMembershipPlanId(membershipPlan.getUuid());
        paymentInstrumentBean.setOrderCart(cartBean);
        paymentInstrumentBean.setMembershipPlanId(membershipPlan.getUuid());
        ModelAndView modelAndView = new ModelAndView(PAYMENT_CREATE_VIEW);
        modelAndView.addObject("paymentDetails", paymentInstrumentBean);
        return modelAndView;
    }

    /*TODO : Break this mapping logic per payment strategy*/
    @RequestMapping(value = PAYMENT_PAY_PATH)
    @PreAuthorize("@userMembershipSecurityServiceImpl.canUserBuyPlan()")
    public ModelAndView pay( @Valid @ModelAttribute("paymentDetails")PaymentInstrumentBean paymentInstrumentBean, BindingResult result) {
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView(PAYMENT_CREATE_VIEW);
            modelAndView.addObject("paymentDetails", paymentInstrumentBean);
            return modelAndView;
        }
        PaymentInstrument paymentInstrument = null;
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        if(!StringUtils.isEmpty(paymentInstrumentBean.getUuid())) {
            paymentInstrument = paymentInstrumentService.findByUUid(paymentInstrumentBean.getUuid());
            paymentInstrument.setCreditCardType(paymentInstrumentBean.getCreditCardType());
            paymentInstrument.setCvv2(paymentInstrumentBean.getCvv2());
            paymentInstrument.setExpirationMonth(paymentInstrument.getExpirationMonth());
            paymentInstrument.setExpirationYear(paymentInstrument.getExpirationYear());
            paymentInstrument.setNumber(paymentInstrument.getNumber());

        } else {
            paymentInstrument =  PaymentInstrumentBean.buildBeanToEntity(paymentInstrumentBean);
            paymentInstrument.setUser(memberShip.getUser());
        }
        MembershipPlan membershipPlan = membershipPlanService.findByUuid(paymentInstrumentBean.getMembershipPlanId());
        MembershipPlanBean membershipPlanBean = MembershipPlanBean.buildEntityToBean(membershipPlan);
        PaymentTransactionService paymentTransactionService = paymentFactory.getPaymentService(paymentInstrumentBean.getPaymentMethod());
        BigDecimal amount = paymentInstrumentService.calculateTotalPrice(membershipPlanBean, paymentInstrumentBean.getOrderCart().getQuantity());
        PaymentRequest<PapalCreditRequest> paymentRequest
                = new PapalCreditCardBuilder(paymentInstrumentBean, membershipPlanBean, amount);

        try {
            Response response = paymentTransactionService.pay(paymentRequest);
            invoiceService.createInvoice(membershipPlanBean, paymentInstrument, memberShip, response);

        } catch (Exception e) {
            log.error("Error occur during payment for user name: {} membership uuid {}", secUser.getUsername(), memberShip.getUuid());
            Locale locale = LocaleContextHolder.getLocale();
            ModelAndView modelAndView = new ModelAndView(PAYMENT_CREATE_VIEW);
            if (e instanceof PayPalRESTException) {
                Error error = ((PayPalRESTException) e).getDetails();
                modelAndView.addObject("errorDetails", error.getDetails());
                modelAndView.addObject("paymentDetails", paymentInstrumentBean);

            } else {
                modelAndView.addObject("error", messageSource.getMessage("some.thing.going.wrong", new Object[] {supportEmail} , locale));
            }
            return modelAndView;
        }
        securityService.reauthenticate(secUser.getUsername());
        ModelAndView modelAndView = new ModelAndView(PAYMENT_SUCCESS_VIEW);
        modelAndView.addObject("paymentDetails", paymentInstrumentBean);
        modelAndView.addObject("plan", membershipPlan);
        return modelAndView;
    }
}
