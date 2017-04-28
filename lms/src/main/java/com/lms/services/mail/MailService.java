package com.lms.services.mail;

import com.lms.models.MailContent;

/**
 * Created by bhushan on 20/4/17.
 */
public interface MailService {
    MailContent get(Long id);
    MailContent save(MailContent mailContent);

}
