package com.lms.services.issuebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.repository.IssueBookRepository;
import com.lms.models.Book;
import com.lms.models.Issue;
import com.lms.models.IssueBook;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;
import com.lms.models.User;
import com.lms.services.issue.IssueService;
import com.lms.utils.helper.DateHelper;
import com.lms.utils.modelutil.IssueBookStatus;

/**
 * Created by bhushan on 22/5/17.
 */
@Service
public class IssueBookServiceImpl implements IssueBookService {
    @Autowired
    private IssueBookRepository issueBookRepository;
    @Autowired
    private IssueService issueService;

    @Override
    @Transactional(readOnly = true)
    public Integer findByUserNameLibraryAndStatus(String userName, String libraryUuid, IssueBookStatus issueBookStatus) {
        return issueBookRepository.findByUserNameLibraryAndStatus(userName, libraryUuid, issueBookStatus);
    }

    @Override
    @Transactional
    public void save(MemberShip memberShip, Set<Book> bookSet) {
        List<IssueBook> issueBooks = new ArrayList<>();
        User user = memberShip.getUser();
        Library library = memberShip.getLibrary();
        MembershipPlan membershipPlan = memberShip.getCurrentSubscription().getMembershipPlan();

        for (Book book : bookSet) {
            IssueBook issueBook = new IssueBook();
            issueBook.setUser(user);
            issueBook.setBook(book);
            issueBook.setIssueBookStatus(IssueBookStatus.ASSIGNED);
            issueBooks.add(issueBook);
        }

        Issue issue = new Issue();
        issue.setUser(user);
        issue.setIssueBooks(issueBooks);
        issue.setIssueDate(new Date());
        issue.setLibrary(library);
        issue.setNumberOfBookAssigned(bookSet.size());
        issue.setReturnDate(DateHelper.addDays(new Date(), membershipPlan.getMaxNumberOfAllowDays()));

        issueService.save(issue);
    }
}
