package com.lms.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lms.utils.enums.Currency;
import com.lms.utils.enums.PeriodType;
import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 2/5/17.
 */
@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class MembershipPlan extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 7273340727724676211L;

    @ManyToOne
    private Library library;
    @Column(unique = true, nullable =  false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false, length = 2044)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodType periodType = PeriodType.MONTH;
    private Integer unit = 1;
    @Column(nullable = false)
    private Integer maxNumberOfBookAllow;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency = Currency.USD;
    private Integer maxNumberOfAllowDays;

}
