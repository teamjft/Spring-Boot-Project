package com.lms.utils.notification.mapper;

import java.util.List;

/**
 * Created by bhushan on 30/4/17.
 */
@FunctionalInterface
public interface IEmailMapMapper {
    List getTargetParameters();
}
