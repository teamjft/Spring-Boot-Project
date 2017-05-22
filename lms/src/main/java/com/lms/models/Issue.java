package com.lms.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 8/4/17.
 */
@Entity @Getter @Setter
public class Issue extends AbstractEntity implements Serializable {
    public Issue(){
        super();
    }
    private static final long serialVersionUID = 4729983415346677814L;
    private Long issueNumber;
    private Date issueDate;
    private Date returnDate;
    private Integer numberOfBookAssigned;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private List<IssueBook> issueBooks = new ArrayList<>();
    @ManyToOne
    private User user;
    @ManyToOne
    private Library library;

}
