package com.lms.utils.beans;

import static com.lms.utils.enums.SaveImageServiceType.LOCALSTROGE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lms.utils.enums.SaveImageServiceType;

/**
 * Created by bhushan on 17/4/17.
 */
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class LibraryBean {
    private String email;
    private String uuid;
    private String description;
    private String name;
    private SaveImageServiceType saveImageServiceType = LOCALSTROGE;
}
