package com.lms.utils.beans;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lms.models.MembershipPlan;
import com.lms.utils.customannotation.annotaion.FieldSize;
import com.lms.utils.enums.Currency;
import com.lms.utils.enums.PeriodType;

/**
 * Created by bhushan on 3/5/17.
 */
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
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
    private Currency currency = Currency.INR;

    public static MembershipPlanBean buildEntityToBean(MembershipPlan plan) {
        return   MembershipPlanBean.builder()
                .name(plan.getName())
                .id(plan.getId())
                .uuid(plan.getUuid())
                .libraryId(plan.getLibrary().getId())
                .price(plan.getPrice())
                .description(plan.getDescription())
                .periodType(plan.getPeriodType())
                .unit(plan.getUnit())
                .maxNumberOfBookAllow(plan.getMaxNumberOfBookAllow())
                .currency(plan.getCurrency())
                .build();
    }

    public static MembershipPlan buildBeanToEntity(MembershipPlanBean membershipPlanBean) {
        MembershipPlan membershipPlan = new MembershipPlan();
        membershipPlan.setMaxNumberOfBookAllow(membershipPlanBean.getMaxNumberOfBookAllow());
        membershipPlan.setName(membershipPlanBean.getName());
        membershipPlan.setDescription(membershipPlanBean.getDescription());
        membershipPlan.setPrice(membershipPlanBean.getPrice());
        membershipPlan.setUnit(membershipPlanBean.getUnit());
        membershipPlan.setPeriodType(membershipPlanBean.getPeriodType());
        return membershipPlan;

    }
}
