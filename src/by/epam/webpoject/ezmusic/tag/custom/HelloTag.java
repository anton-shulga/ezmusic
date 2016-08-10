package by.epam.webpoject.ezmusic.tag.custom;

import by.epam.webpoject.ezmusic.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Антон on 08.08.2016.
 */
@SuppressWarnings("serial")
public class HelloTag extends TagSupport {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        String output = null;
        if(user.isAdmin()){
            output = "Hello, " + user.getLogin() + "! You are admin!";
        }else {
            output = "Hello, " + user.getLogin() + "! You are user!";
        }
        try {
            pageContext.getOut().write("<hr/>" + output + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
