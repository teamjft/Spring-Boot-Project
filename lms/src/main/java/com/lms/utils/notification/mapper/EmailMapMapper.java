package com.lms.utils.notification.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by bhushan on 21/4/17.
 */
public interface EmailMapMapper<T> {
 Map<String, String> getMap();
 List getTargetParameters();
 void setMap(T t);
}
