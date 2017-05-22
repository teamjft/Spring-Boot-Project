package com.lms.services.issuebook;

import java.util.Set;

import com.lms.models.Book;
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
}
