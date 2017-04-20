package com.lms.services.mail;

import com.lms.models.Mail;

/**
 * Created by bhushan on 20/4/17.
 */
public interface MailService {
    Mail get(Long id);
    Mail save(Mail mail);

}
