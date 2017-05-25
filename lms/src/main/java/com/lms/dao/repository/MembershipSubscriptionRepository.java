package com.lms.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.models.MemberShip;
import com.lms.models.MembershipSubscription;

/**
 * Created by bhushan on 17/5/17.
 */
public interface MembershipSubscriptionRepository extends JpaRepository<MembershipSubscription, Long> {
    @Query(value = "select ms from MembershipSubscription ms join ms.memberShip m join m.library l where l.uuid= ?1" ,
            countQuery = "select count(ms.id) from MembershipSubscription ms join ms.memberShip m join m.library l where l.uuid= ?1")
    Page<MembershipSubscription> findByLibraryUuid(String libraryUuid, Pageable pageable);

    @Query("select ms from MembershipSubscription ms join ms.memberShip m join m.library l where l.uuid= ?1 and ms.uuid= ?2" )
    MembershipSubscription  findBySubscriptionUuidAndLibraryUuid(String libraryUuid, String uuid);
}
