package com.lms.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.repository.MailRepository;
import com.lms.models.MailContent;

/**
 * Created by bhushan on 20/4/17.
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailRepository mailRepository;

    @Override
    @Transactional(readOnly = true)
    public MailContent get(Long id) {
        return mailRepository.getOne(id);
    }

    @Override
    @Transactional
    public MailContent save(MailContent mailContent) {
        return mailRepository.save(mailContent);
    }
}
