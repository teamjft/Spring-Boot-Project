package com.lms.utils.beans;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lms.utils.customannotation.annotaion.FieldSize;
import com.lms.utils.customannotation.annotaion.NotEmptyAndNull;

/**
 * Created by bhushan on 15/5/17.
 */
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class OrderCartBean {
    @NotEmptyAndNull
    private String planUuid;
    @FieldSize(max=999999999)
    private Integer quantity;
}
