package com.lms.utils.notification.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by bhushan on 21/4/17.
 */
public abstract class EmailMapMapper {

 public abstract List getTargetParameters();

 Map<String, String> map;

 public Map<String, String> getMap() {
  return map;
 }

 boolean validate() {
  return map != null;
 }

}
