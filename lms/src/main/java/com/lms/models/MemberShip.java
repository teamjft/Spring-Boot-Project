package com.lms.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.lms.utils.modelutil.AbstractEntity;
import com.lms.utils.modelutil.MembershipStatus;

/**
 * Created by bhushan on 8/4/17.
 */
@Entity
@Setter @Getter
public class MemberShip extends AbstractEntity implements Serializable {
    public MemberShip() {
        super();
    }

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    private MembershipStatus membershipStatus = MembershipStatus.SUSPENDED;
    @Type(type = "yes_no")
    @Column(nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean admin;
    @Type(type = "yes_no")
    @Column(nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean librarian;
    @Type(type = "yes_no")
    @Column(nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean lastUsed;
    private Date expiredDate;
    @OneToMany(mappedBy = "memberShip")
    private Set<MembershipSubscription> membershipSubscriptions = new HashSet<>();
    @OneToOne
    private MembershipSubscription currentSubscription;

}
