package com.lms.services.issue;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.dao.repository.IssueRepository;
import com.lms.models.Issue;

/**
 * Created by bhushan on 9/4/17.
 */
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Issue findByUuid(String uuid) {
        return issueRepository.findByUuid(uuid);
    }
}
