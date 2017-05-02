package com.lms.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 2/5/17.
 */
@Entity
public class MembershipPlan extends AbstractEntity {
    @Column(unique = true, nullable =  false)
    private String name;
    private BigDecimal price;
    private Integer length;

}
