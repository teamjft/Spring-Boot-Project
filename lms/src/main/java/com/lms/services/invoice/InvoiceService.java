package com.lms.services.invoice;

import com.lms.models.MemberShip;
import com.lms.models.PaymentInstrument;
import com.lms.utils.beans.MembershipPlanBean;
import com.lms.utils.paymentbuilder.Response;

/**
 * Created by bhushan on 17/5/17.
 */
public interface InvoiceService {
   void createInvoice(MembershipPlanBean membershipPlanBean, PaymentInstrument paymentInstrument, MemberShip memberShip, Response response);
}
