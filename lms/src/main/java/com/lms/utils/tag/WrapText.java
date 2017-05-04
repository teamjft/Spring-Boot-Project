package com.lms.utils.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by bhushan on 4/5/17.
 */
public class WrapText extends SimpleTagSupport {
    private String text;
    private int characterLimit = 10;

    public void setText(String text) {
        this.text = text;
    }

    public void setCharacterLimit(int characterLimit) {
        this.characterLimit = characterLimit;
    }

    StringWriter stringWriter = new StringWriter();
    @Override
    public void doTag()
            throws JspException, IOException
    {
        if (text != null) {
          /* Wrap text with limitation */
            JspWriter out = getJspContext().getOut();
            String wrap = wrapText(text, characterLimit);
            out.println(StringEscapeUtils.escapeHtml4(wrap));
        }
    }

    private String wrapText(String text, int characterLimit) {
        return text.length() > characterLimit ? text.substring(0, characterLimit) + "..." : text;
    }
}
