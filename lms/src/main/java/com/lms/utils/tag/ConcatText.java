package com.lms.utils.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by bhushan on 17/5/17.
 */
public class ConcatText extends SimpleTagSupport {
    private String[] strings;

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    @Override
    public void doTag()
            throws JspException, IOException
    {
        if (strings != null) {
            String var = StringUtils.EMPTY;
            for (int i =0; i< strings.length; i++) {
                var.concat(strings[i]);
            }
          /* Wrap text with limitation */
            JspWriter out = getJspContext().getOut();
            out.println(var);
        }
    }

}
