package com.lms.services.membershipplan;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lms.models.Library;
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
    MembershipPlan findByUuidAndLibrary(String uuid, Library library);
    MembershipPlan findByName(String name);
    List<MembershipPlan> findByLibrary(Library library);
    Page<MembershipPlan> getPageRequest(Integer pageNumber);
}
