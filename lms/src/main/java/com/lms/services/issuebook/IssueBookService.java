package com.lms.services.issuebook;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.lms.models.Book;
import com.lms.models.IssueBook;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;
import com.lms.models.User;
import com.lms.utils.modelutil.IssueBookStatus;

/**
 * Created by bhushan on 22/5/17.
 */
public interface IssueBookService {
    Integer findByUserNameLibraryAndStatus(String userName, String libraryUuid, IssueBookStatus issueBookStatus);
    void save(MemberShip memberShip, Set<Book> bookSet);
    Page<IssueBook> getPageRequest(Library library, Integer pageNumber);
    Page<IssueBook> getPageRequestForCurrentUser(User user, Integer pageNumber);
    IssueBook findByUuid(Library library, String uuid);
}
