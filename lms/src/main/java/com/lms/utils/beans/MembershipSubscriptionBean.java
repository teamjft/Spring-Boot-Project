package com.lms.utils.beans;

import java.util.Date;
import com.lms.utils.enums.MembershipSubscriptionStatus;

/**
 * Created by bhushan on 12/5/17.
 */
public class MembershipSubscriptionBean {
    private Long membershipPlanUuid;
    private Long memberShipUuid;
    private Long userId;
    private Date startDate;
    private Date endDate;
    MembershipSubscriptionStatus membershipSubscriptionStatus;
}
