package com.lms.services.invoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.models.Invoice;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;
import com.lms.models.MembershipSubscription;
import com.lms.models.Payment;
import com.lms.models.PaymentInstrument;
import com.lms.services.membershipplan.MembershipPlanService;
import com.lms.services.membershipsubscription.MembershipSubscriptionService;
import com.lms.services.payment.PaymentService;
import com.lms.services.paymentinstrument.PaymentInstrumentService;
import com.lms.utils.beans.MembershipPlanBean;
import com.lms.utils.enums.MembershipSubscriptionStatus;
import com.lms.utils.modelutil.MembershipStatus;
import com.lms.utils.paymentbuilder.Response;

/**
 * Created by bhushan on 17/5/17.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private MembershipPlanService membershipPlanService;
    @Autowired
    private MembershipSubscriptionService membershipSubscriptionService;
    @Autowired
    private PaymentInstrumentService paymentInstrumentService;
    @Autowired
    private PaymentService paymentService;

    @Override
    @Transactional
    public void createInvoice(MembershipPlanBean membershipPlanBean, PaymentInstrument paymentInstrument, MemberShip memberShip, Response response) {
        MembershipPlan membershipPlan = membershipPlanService.findByUuid(membershipPlanBean.getUuid());
        MembershipSubscription membershipSubscription = new MembershipSubscription();
        Integer quantity = response.getQuantity();
        Date endDate = paymentInstrumentService.calculateExpiredDate(membershipPlanBean, quantity);
        BigDecimal totalAmount = paymentInstrumentService.calculateTotalPrice(membershipPlanBean, quantity);
        membershipSubscription.setUser(memberShip.getUser());
        membershipSubscription.setStartDate(new Date());
        membershipSubscription.setEndDate(endDate);
        membershipSubscription.setMemberShip(memberShip);
        membershipSubscription.setMembershipPlan(membershipPlan);
        membershipSubscription.setMembershipSubscriptionStatus(MembershipSubscriptionStatus.ACTIVE);

        Invoice invoice = new Invoice();
        invoice.setTotalAmount(totalAmount);
        invoice.setStartDate(new Date());
        invoice.setEndDate(endDate);
        invoice.setCurrency(membershipPlan.getCurrency());
        invoice.setDescription(invoice.buildDescription(membershipPlan));
        invoice.setUser(memberShip.getUser());

        Set<Invoice> invoices = new HashSet<>();
        invoices.add(invoice);

        membershipSubscription.setInvoices(invoices);
        invoice.setMembershipSubscription(membershipSubscription);
        membershipSubscriptionService.create(membershipSubscription);

        Payment payment = new Payment();
        payment.setPayAmount(totalAmount);
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus(response.getPaymentStatus());
        payment.setPaymentMethod(response.getPaymentMethod());
        payment.setInvoice(invoice);
        payment.setPaymentStatus(response.getPaymentStatus());
        payment.setTransactionId(response.getTransactionId());
        payment.setCurrency(membershipPlan.getCurrency());
        paymentService.create(payment);
        memberShip.setMembershipStatus(MembershipStatus.ACTIVE);
        memberShip.setCurrentSubscription(membershipSubscription);
        paymentInstrumentService.update(paymentInstrument);

    }

}
