package com.lms.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;

/**
 * Created by bhushan on 3/5/17.
 */
public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Long> {
    MembershipPlan findByUuid(String uuid);
    MembershipPlan findByName(String name);
    List<MembershipPlan> findByLibraryAndEnabledTrue(Library library);
}
