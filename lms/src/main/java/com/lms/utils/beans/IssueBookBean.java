package com.lms.utils.beans;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bhushan on 19/5/17.
 */
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class IssueBookBean {
    private String membershipId;
    private Set<String> isbns = new HashSet<>();
}
