package com.lms.models;

import static com.lms.utils.enums.SaveImageServiceType.LOCALSTROGE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.enums.NotificationServiceType;
import com.lms.utils.enums.SaveImageServiceType;
import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 8/4/17.
 */
@Entity
@Getter @Setter
public class Library extends AbstractEntity implements Serializable {
    public Library() {
        super();
    }
    @Column(unique = true, nullable = false)
    private String email;
    private static final long serialVersionUID = 6224739398701187639L;
    @Column(nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    Set<MemberShip> memberShips = new HashSet<>();
    @OneToOne
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "library")
    private Set<Book> books = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "library")
    private List<Issue> issues = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private SaveImageServiceType saveImageServiceType = LOCALSTROGE;
    @Enumerated(EnumType.STRING)
    private NotificationServiceType notificationServiceType = NotificationServiceType.EMAIL;
    @OneToMany(mappedBy = "library")
    private Set<MembershipPlan> membershipPlans = new HashSet<>();
}
