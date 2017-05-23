package com.lms.utils.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bhushan on 2/5/17.
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class IssueDataCount {
    private Long numberOFIssuedBook;
    private Long numberOFReturnedBook;
}
