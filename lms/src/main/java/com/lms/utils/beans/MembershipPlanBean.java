package com.lms.utils.beans;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.customannotation.annotaion.FieldSize;
import com.lms.utils.enums.PeriodType;

/**
 * Created by bhushan on 3/5/17.
 */
@Getter @Setter
public class MembershipPlanBean {
    private Long id;
    private String uuid;
    private Long libraryId;
    @FieldSize(fieldName = "Name")
    private String name;
    @FieldSize(fieldName = "Price", min = 0)
    private BigDecimal price;
    @FieldSize(fieldName = "Description", max = 2044)
    private String description;
    private PeriodType periodType = PeriodType.MONTH;
    @FieldSize(fieldName = "Unit",max =100000, nullable = false)
    private Integer unit = 1;
    @FieldSize(fieldName = "Max number of book allow",max =100000, nullable = false)
    private Integer maxNumberOfBookAllow;
}
