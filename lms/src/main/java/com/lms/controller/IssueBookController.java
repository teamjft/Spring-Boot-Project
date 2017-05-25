package com.lms.controller;

import static com.lms.utils.constants.UrlMappingConstant.ASSIGN_BOOK_PATH;
import static com.lms.utils.constants.UrlMappingConstant.CREATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.INDEX_PATH;
import static com.lms.utils.constants.UrlMappingConstant.ISSUED_BOOK_PATH;
import static com.lms.utils.constants.UrlMappingConstant.ISSUE_BOOK__BASE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.VALIDATE_USER_FOR_ASSIGN_BOOK_PATH;
import static com.lms.utils.constants.UrlMappingConstant.VIEW_PATH;
import static com.lms.utils.constants.ViewConstant.ISSUE_BOOK_ASSIGN_VIEW;
import static com.lms.utils.constants.ViewConstant.ISSUE_BOOK_CREATE_VIEW;
import static com.lms.utils.constants.ViewConstant.ISSUE_BOOK_VIEW;
import static com.lms.utils.constants.ViewConstant.ISSUE_INDEX_VIEW;
import static com.lms.utils.constants.ViewConstant.ISSUE_MEMBERSHIP_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_HOME_VIEW;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Sets;
import com.lms.config.security.SecUser;
import com.lms.models.Book;
import com.lms.models.IssueBook;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.User;
import com.lms.services.book.BookService;
import com.lms.services.issuebook.IssueBookService;
import com.lms.services.library.LibraryService;
import com.lms.services.membership.MembershipService;
import com.lms.services.user.UserService;
import com.lms.utils.beans.IssueBookBean;
import com.lms.utils.helper.PaginationHelper;
import com.lms.utils.helper.SecurityUtil;
import com.lms.utils.modelutil.IssueBookStatus;

/**
 * Created by bhushan on 19/5/17.
 */
@Controller
@RequestMapping(value = ISSUE_BOOK__BASE_PATH)
@PreAuthorize("isAuthenticated()")
public class IssueBookController {

    @Autowired
    private IssueBookService issueBookService;
    @Autowired
    private UserService userService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private BookService bookService;

    @RequestMapping(value = CREATE_PATH)
    public ModelAndView create() {
        return new ModelAndView(ISSUE_BOOK_CREATE_VIEW);
    }

    @RequestMapping(INDEX_PATH)
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        Page<IssueBook> page = issueBookService.getPageRequest(memberShip.getLibrary(), currentPageNumber);
        return PaginationHelper.getModelAndView(ISSUE_INDEX_VIEW, page, "issues");
    }

    @RequestMapping(value = VIEW_PATH)
    public ModelAndView view(@PathVariable String uuid) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        IssueBook issueBook = issueBookService.findByUuid(library, uuid);
        if (issueBook == null) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        return new ModelAndView(ISSUE_BOOK_VIEW, "issueBook", issueBook);
    }

    @RequestMapping(ISSUED_BOOK_PATH)
    public ModelAndView userIssuedBooks(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        Page<IssueBook> page = issueBookService.getPageRequestForCurrentUser(memberShip.getUser(), currentPageNumber);
        return PaginationHelper.getModelAndView(ISSUE_MEMBERSHIP_VIEW, page, "issues");
    }


    @RequestMapping(value = VALIDATE_USER_FOR_ASSIGN_BOOK_PATH)
    public ModelAndView validateUserForAssignBook(HttpServletRequest request) {
        String username = request.getParameter("username");
        Locale locale = LocaleContextHolder.getLocale();
        if (username == null || username.equals(StringUtils.EMPTY)) {
            return new ModelAndView(ISSUE_BOOK_CREATE_VIEW, "error", messageSource.getMessage("filed.cant.be.empty",new Object[] {"username"}, locale));
        }
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        User user = userService.loadByUsername(username);
        MemberShip memberShip = membershipService.findByLibraryAndUser(library, user);
        if (memberShip == null) {
            return new ModelAndView(ISSUE_BOOK_CREATE_VIEW, "error", messageSource.getMessage("user.is.not.member.of.library", new Object[] {username, library.getName()} , locale));
        }

        Integer count = issueBookService.findByUserNameLibraryAndStatus(username, secUser.getLibraryId(), IssueBookStatus.ASSIGNED);
        Integer maxNumberOfBookAllow = null;
        if (memberShip.getCurrentSubscription() != null) {
            maxNumberOfBookAllow = memberShip.getCurrentSubscription().getMembershipPlan().getMaxNumberOfBookAllow();
        }
        if (maxNumberOfBookAllow == null) {
            return new ModelAndView(ISSUE_BOOK_CREATE_VIEW, "error", messageSource.getMessage("no.current.subscription.for.user", new Object[] {username} , locale));
        }

        if (count >  maxNumberOfBookAllow) {
            return new ModelAndView(ISSUE_BOOK_CREATE_VIEW, "error", messageSource.getMessage("user.have.already.assigned.max.limit", null , locale));
        }
        IssueBookBean issueBookBean = new IssueBookBean();
        issueBookBean.setMembershipId(memberShip.getUuid());
        return new ModelAndView(ISSUE_BOOK_ASSIGN_VIEW, "issueBook", issueBookBean);
    }

    @RequestMapping(value = ASSIGN_BOOK_PATH)
    public ModelAndView assign(@ModelAttribute("issueBook") IssueBookBean issueBookBean) {
        if (issueBookBean.getMembershipId() == null || issueBookBean.getMembershipId().equals(StringUtils.EMPTY)) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        MemberShip memberShip = membershipService.findByUuid(issueBookBean.getMembershipId());
        if (memberShip == null) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        int currentBookSize = issueBookBean.getIsbns().size();
        Locale locale = LocaleContextHolder.getLocale();
        List<Book> books = bookService.findByLibraryAndIsbnIn(memberShip.getLibrary(), issueBookBean.getIsbns());
        if (currentBookSize == 0 || books == null || books.size() == 0) {
            ModelAndView modelAndView = new ModelAndView(ISSUE_BOOK_ASSIGN_VIEW, "error",  messageSource.getMessage("enter.isbn.for.assign.book",null, locale));
            modelAndView.addObject("issueBook", issueBookBean);
            return modelAndView;
        }

        issueBookService.save(memberShip, Sets.newHashSet(books));

        return new ModelAndView(ISSUE_BOOK_CREATE_VIEW, "success",  messageSource.getMessage("successfully.assigned.book",new Object[] {memberShip.getUser().getUsername()}, locale));
    }

}
