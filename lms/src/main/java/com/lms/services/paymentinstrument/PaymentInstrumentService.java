package com.lms.services.paymentinstrument;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.lms.models.PaymentInstrument;
import com.lms.utils.beans.MembershipPlanBean;
import com.lms.utils.enums.PeriodType;
import com.lms.utils.helper.DateHelper;

/**
 * Created by bhushan on 12/5/17.
 */
public interface PaymentInstrumentService {
    PaymentInstrument findFirstEnableTrue();

    default BigDecimal calculateTotalPrice(MembershipPlanBean membershipPlanBean, int quantity) {
        if (quantity == 0) {
            return membershipPlanBean.getPrice();
        }
        BigDecimal price = membershipPlanBean.getPrice();
        price.multiply(new BigDecimal(quantity));
        return price.multiply(new BigDecimal(quantity));
    }

    default Date calculateExpiredDate(MembershipPlanBean membershipPlanBean, int quantity) {
        PeriodType periodType = membershipPlanBean.getPeriodType();
         Integer unit = membershipPlanBean.getUnit();
        Date expiredDate = null;
        switch (periodType) {
            case DAY:
                int days =unit * quantity;
                expiredDate= DateHelper.addDays(new Date(), days);
                break;
            case MONTH:
                int months = unit*quantity;
                DateUtils.addMonths(new Date(), months);
                expiredDate = DateUtils.addMonths(new Date(), months);
                break;
            case YEAR:
                int years = unit*quantity;
                expiredDate = DateUtils.addYears(new Date(), years);
                break;
        }
        return expiredDate;
    }
}
