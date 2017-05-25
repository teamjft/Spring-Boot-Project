package com.lms.services.issuebook;

import static com.lms.utils.constants.Constant.PAGE_SIZE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.lms.utils.helper.PaginationHelper;
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
        MembershipPlan membershipPlan = memberShip.getCurrentSubscription().getMembershipPlan();
        Issue issue = new Issue();
        issue.setMemberShip(memberShip);
        issue.setIssueDate(new Date());
        issue.setNumberOfBookAssigned(bookSet.size());
        issue.setReturnDate(DateHelper.addDays(new Date(), membershipPlan.getMaxNumberOfAllowDays()));
        for (Book book : bookSet) {
            IssueBook issueBook = new IssueBook();
            issueBook.setUser(user);
            issueBook.setBook(book);
            issueBook.setIssueBookStatus(IssueBookStatus.ASSIGNED);
            issueBook.setIssue(issue);
            issueBook.setLibrary(memberShip.getLibrary());
            issueBooks.add(issueBook);
        }
        issue.setIssueBooks(issueBooks);
        issueService.save(issue);
    }

    @Override
    public Page<IssueBook> getPageRequest(Library library, Integer pageNumber) {
        return issueBookRepository.findByLibrary(library,  PaginationHelper.getPageRequest(pageNumber));
    }

    @Override
    public Page<IssueBook> getPageRequestForCurrentUser(User user, Integer pageNumber) {
        return issueBookRepository.findByUser(user, PaginationHelper.getPageRequest(pageNumber));
    }

    @Override
    public IssueBook findByUuid(Library library, String uuid) {
        return issueBookRepository.findByUuidAndLibrary(uuid, library);
    }
}
