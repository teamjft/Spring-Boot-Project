package com.lms.dao;

import static com.lms.models.QBook.book;
import static com.lms.models.QIssue.issue;
import static com.lms.models.QIssueBook.issueBook;
import static com.lms.models.QLibrary.library;
import static com.lms.models.QMemberShip.memberShip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lms.utils.beans.LibraryDataCount;
import com.lms.utils.modelutil.IssueBookStatus;
import com.lms.utils.modelutil.MembershipStatus;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.ConstructorExpression;

/**
 * Created by bhushan on 16/4/17.
 */
@Repository
public class LibraryDao {
    @PersistenceContext
    private EntityManager entityManager;
    public LibraryDataCount basicCountInfoOfLibrary(String uuid) {
        JPAQuery query = new JPAQuery(entityManager);
        return query.from(library)
                .leftJoin(library.books, book)
                .leftJoin(library.memberShips, memberShip).on(memberShip.membershipStatus.eq(MembershipStatus.ACTIVE))
                .leftJoin(memberShip.issues, issue)
                .leftJoin(issue.issueBooks, issueBook).on(issueBook.issueBookStatus.eq(IssueBookStatus.ASSIGNED))
                .where(library.uuid.eq(uuid))
                .list(ConstructorExpression.create(LibraryDataCount.class, book.id.countDistinct(),
                        memberShip.id.countDistinct(), book.totalNumberOfCopies.coalesce(0).asNumber().sum(), issueBook.id.countDistinct())).get(0);


    }
}

