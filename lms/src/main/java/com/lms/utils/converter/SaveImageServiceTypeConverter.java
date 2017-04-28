package com.lms.utils.converter;

import java.beans.PropertyEditorSupport;

import com.lms.utils.enums.SaveImageServiceType;

/**
 * Created by bhushan on 19/4/17.
 */
public class SaveImageServiceTypeConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        String capitalized = text.toUpperCase();
        SaveImageServiceType saveImageServiceType = SaveImageServiceType.valueOf(capitalized);
        setValue(saveImageServiceType);
    }
}
