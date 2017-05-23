package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.models.IssueBook;
import com.lms.utils.modelutil.IssueBookStatus;

/**
 * Created by bhushan on 22/5/17.
 */
public interface IssueBookRepository extends JpaRepository<IssueBook, Long> {
    @Query("select count(ib.id) from IssueBook ib " +
            "join  ib.user ibuser " +
            "join ib.issue iss join iss.memberShip m " +
            "join m.library ibl where ibuser.username = ?1 and ibl.uuid= ?2 and ib.issueBookStatus = ?3")
    Integer findByUserNameLibraryAndStatus(String userName, String libraryUuid, IssueBookStatus issueBookStatus);
    }
