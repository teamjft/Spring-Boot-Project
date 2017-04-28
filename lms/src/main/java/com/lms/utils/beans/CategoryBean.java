package com.lms.utils.beans;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by bhushan on 17/4/17.
 */
@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class CategoryBean {
    private Long id;
    @NotEmpty
    @Size(max = 225)
    private String name;
    private String uuid;
}
