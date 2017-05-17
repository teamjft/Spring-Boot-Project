package com.lms.services.membershipsubscription;

import java.util.List;

import com.lms.models.MembershipSubscription;

/**
 * Created by bhushan on 17/5/17.
 */
public interface MembershipSubscriptionService {
    MembershipSubscription get(Long id);
    List<MembershipSubscription> getAll();
    MembershipSubscription create(MembershipSubscription membershipSubscription);
    void delete(Long id);
    void update(MembershipSubscription membershipSubscription);
}
