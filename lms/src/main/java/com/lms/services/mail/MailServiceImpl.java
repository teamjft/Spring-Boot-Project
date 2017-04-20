package com.lms.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.repository.MailRepository;
import com.lms.models.Mail;

/**
 * Created by bhushan on 20/4/17.
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailRepository mailRepository;

    @Override
    @Transactional(readOnly = true)
    public Mail get(Long id) {
        return mailRepository.getOne(id);
    }

    @Override
    @Transactional
    public Mail save(Mail mail) {
        return mailRepository.save(mail);
    }
}
