package com.lms.services.issue;

import java.util.List;

import com.lms.models.Issue;
import com.lms.models.MemberShip;

/**
 * Created by bhushan on 9/4/17.
 */
public interface IssueService {
    Issue findByUuid(String uuid);
    Issue save(Issue issue);
    List<Issue> findByMembership(MemberShip memberShip);
}
