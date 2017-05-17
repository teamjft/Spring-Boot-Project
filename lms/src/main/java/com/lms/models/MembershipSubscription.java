package com.lms.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.enums.MembershipSubscriptionStatus;
import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 3/5/17.
 */
@Entity
@Setter @Getter
public class MembershipSubscription extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1505237268357573575L;
    @OneToOne
    private MembershipPlan membershipPlan;
    @ManyToOne
    private MemberShip memberShip;
    @ManyToOne
    private User user;
    private Date startDate;
    private Date endDate;
    @Enumerated(EnumType.STRING)
    MembershipSubscriptionStatus membershipSubscriptionStatus;
    @OneToMany(mappedBy = "membershipSubscription", cascade = CascadeType.ALL)
    private Set<Invoice> invoices = new HashSet<>();

}
