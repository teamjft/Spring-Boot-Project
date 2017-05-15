package com.lms.services.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lms.utils.beans.MembershipPlanBean;
import com.lms.utils.beans.PaymentInstrumentBean;
import com.lms.utils.constants.Constant;
import com.lms.utils.enums.PaymentMethod;
import com.lms.utils.paymentbuilder.PaymentRequest;
import com.lms.utils.paymentbuilder.Response;
import com.lms.utils.paymentbuilder.paypalcreditcard.PapalCreditRequest;
import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;

/**
 * Created by bhushan on 10/5/17.
 */
@Service
public class PaypalCreditCardPaymentServiceImpl implements PaymentService {
    @Value("${paypal.clientID}")
    private String clientId;
    @Value("${paypal.clientSecret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    @Override
    public Response pay(PaymentRequest paymentRequest) throws Exception {
        PapalCreditRequest papalCreditRequest = (PapalCreditRequest)paymentRequest.getRequest();
        MembershipPlanBean membershipPlanBean = papalCreditRequest.getMembershipPlanBean();
        Item item = new Item();
        item.setName(membershipPlanBean.getName());
        item.setCurrency(membershipPlanBean.getCurrency().toString());
        item.setPrice(membershipPlanBean.getPrice().toString());
        item.setQuantity("1");

        List<Item> itms = new ArrayList<>();
        itms.add(item);
        ItemList itemList = new ItemList();
        itemList.setItems(itms);

        PaymentInstrumentBean paymentInstrumentBean = papalCreditRequest.getPaymentInstrumentBean();
        Address billingAddress = new Address();
        billingAddress.setLine1(paymentInstrumentBean.getLine1());
        billingAddress.setCity(paymentInstrumentBean.getCity());
        billingAddress.setState(paymentInstrumentBean.getState());
        billingAddress.setPostalCode(paymentInstrumentBean.getPostalCode());
        billingAddress.setCountryCode(paymentInstrumentBean.getCountryCode());
        APIContext context = new APIContext(clientId, clientSecret, mode);
        CreditCard creditCard = new CreditCard()
                .setType(paymentInstrumentBean.getCreditCardType().toString())
                .setNumber(paymentInstrumentBean.getNumber())
                .setExpireMonth(paymentInstrumentBean.getExpirationMonth())
                .setExpireYear(paymentInstrumentBean.getExpirationYear())
                .setCvv2(paymentInstrumentBean.getCvv2())
                .setFirstName(paymentInstrumentBean.getFirstName())
                .setLastName(paymentInstrumentBean.getLastName());
        /*creditCard.setBillingAddress(billingAddress)*/;

        Amount amount = new Amount();
        amount.setCurrency(membershipPlanBean.getCurrency().toString());
        amount.setTotal(papalCreditRequest.getTotalAmount().toString());



        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setInvoiceNumber(papalCreditRequest.getInvoiceNumber());
        transaction.setItemList(itemList);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        FundingInstrument fundingInstrument = new FundingInstrument();
        fundingInstrument.setCreditCard(creditCard);
        List<FundingInstrument> fundingInstrumentList = new ArrayList<>();
        fundingInstrumentList.add(fundingInstrument);

        Payer payer = new Payer();
        payer.setPaymentMethod(Constant.PAYMENT_METHOD_CREDIT_CARD);
        payer.setFundingInstruments(fundingInstrumentList);

        Payment payment = new Payment();
        payment.setIntent(Constant.INTENT_SALE);
        payment.setPayer(payer);
        payment.setTransactions(transactions);


        payment = payment.create(context);
        Response response = Response.builder().paymentMethod(PaymentMethod.PAYPAL_CREDIT_CARD).transactionId(payment.getId()).build();
        return response;
    }
}
