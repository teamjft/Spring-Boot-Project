package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Mail;

/**
 * Created by bhushan on 20/4/17.
 */

public interface MailRepository extends JpaRepository<Mail, Long> {
}
