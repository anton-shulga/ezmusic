package by.epam.webpoject.ezmusic.tag.custom;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Антон on 17.09.2016.
 */
public class MessageTag extends TagSupport {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int doStartTag() throws JspException {

        try {
            pageContext.getOut().write("<script> Materialize.toast(" + "\"" + message + "\"" + ", 4000);</script>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
