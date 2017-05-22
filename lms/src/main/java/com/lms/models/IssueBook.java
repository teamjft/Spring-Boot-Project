package com.lms.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.modelutil.AbstractEntity;
import com.lms.utils.modelutil.IssueBookStatus;

/**
 * Created by bhushan on 8/4/17.
 */
@Entity @Setter @Getter
public class IssueBook extends AbstractEntity implements Serializable {
    public IssueBook() {
        super();
    }
    private static final long serialVersionUID = -514509548231607074L;
    @OneToOne
    private Book book;
    @Enumerated(EnumType.STRING)
    private IssueBookStatus issueBookStatus;
    @ManyToOne
    private User user;
    @ManyToOne
    private Issue issue;

}
