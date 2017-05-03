package com.lms.services.membershipplan;

import java.util.List;

import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;

/**
 * Created by bhushan on 3/5/17.
 */
public interface MembershipPlanService {
    MembershipPlan get(Long id);
    List<MembershipPlan> getAll();
    MembershipPlan create(MembershipPlan membershipPlan);
    MembershipPlan update(MembershipPlan membershipPlan);
    void deleteUser(Long id);
    MembershipPlan findByUuid(String uuid);
    List<MembershipPlan> findByMembership(MemberShip memberShip);
}
