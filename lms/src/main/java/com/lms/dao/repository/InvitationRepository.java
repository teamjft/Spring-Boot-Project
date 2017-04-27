package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Invitation;

/**
 * Created by bhushan on 25/4/17.
 */
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Invitation findByUuid(String uuid);
    Invitation findByTokenAndDeletedFalse(String uuid);
}
