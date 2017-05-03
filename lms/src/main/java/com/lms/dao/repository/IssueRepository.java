package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Issue;

/**
 * Created by bhushan on 3/5/17.
 */
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Issue findByUuid(String uuid);
}
