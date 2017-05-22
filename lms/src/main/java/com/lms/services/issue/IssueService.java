package com.lms.services.issue;

import com.lms.models.Issue;

/**
 * Created by bhushan on 9/4/17.
 */
public interface IssueService {
    Issue findByUuid(String uuid);
    Issue save(Issue issue);
}
