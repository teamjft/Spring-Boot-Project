package com.lms.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.enums.Currency;
import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 3/5/17.
 */
@Entity
@Setter @Getter
public class Invoice extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 2263072318772750675L;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private BigDecimal totalAmount;
    @ManyToOne
    private User user;
    private BigDecimal balanceAmount;
    @Column(nullable = false, length = 2024)
    private String description;
    @ManyToOne
    private MembershipSubscription membershipSubscription;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @OneToMany(mappedBy = "invoice")
    private Set<Payment> payments = new HashSet<>();

    public String buildDescription(MembershipPlan membershipPlan) {
        return String.format("Membership plan : %s activated from %s  to %s", membershipPlan.getName(), startDate, endDate);
    }

}
