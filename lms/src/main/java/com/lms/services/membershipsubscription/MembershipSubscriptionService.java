package com.lms.services.membershipsubscription;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.MembershipSubscription;

/**
 * Created by bhushan on 17/5/17.
 */
public interface MembershipSubscriptionService {
    MembershipSubscription get(Long id);
    MembershipSubscription findByUuid(String libraryId, String uuid);
    List<MembershipSubscription> getAll();
    MembershipSubscription create(MembershipSubscription membershipSubscription);
    void delete(Long id);
    void update(MembershipSubscription membershipSubscription);
    Page<MembershipSubscription> getPageRequest(String libraryId, Integer pageNumber);
}
