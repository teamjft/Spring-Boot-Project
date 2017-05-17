package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.MembershipSubscription;

/**
 * Created by bhushan on 17/5/17.
 */
public interface MembershipSubscriptionRepository extends JpaRepository<MembershipSubscription, Long> {
}
