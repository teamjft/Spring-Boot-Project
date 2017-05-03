package com.lms.utils.beans;

import java.math.BigDecimal;

import com.lms.utils.enums.PeriodType;

/**
 * Created by bhushan on 3/5/17.
 */
public class MembershipPlanBean {
    private Long libraryId;
    private String name;
    private BigDecimal price;
    private String description;
    private PeriodType periodType = PeriodType.MONTH;
    private Integer unit = 1;
    private Integer maxNumberOfBookAllow;
}
