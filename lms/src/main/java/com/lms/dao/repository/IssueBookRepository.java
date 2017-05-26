package com.lms.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.models.Book;
import com.lms.models.Issue;
import com.lms.models.IssueBook;
import com.lms.models.Library;
import com.lms.models.User;
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

    Page<IssueBook> findByLibrary(Library library, Pageable pageable);
    Page<IssueBook> findByUser(User user, Pageable pageable);
    IssueBook findByUuidAndLibrary(String uuid, Library library);
    Page<IssueBook> findByLibraryAndUserAndIssueBookStatus(Library library, User user, IssueBookStatus issueBookStatus, Pageable pageable);

    }

