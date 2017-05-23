package com.lms.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Issue;
import com.lms.models.MemberShip;

/**
 * Created by bhushan on 3/5/17.
 */
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Issue findByUuid(String uuid);
    List<Issue> findByMemberShip(MemberShip memberShip);
}
