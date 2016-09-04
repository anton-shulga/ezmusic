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
        if(user.getIsAdmin()){
            output = "Hello, " + user.getLogin() + "! You are admin!";
        }else {
            output = "Hello, " + user.getLogin() + "! <br>You are user! Balance : " + user.getBalance() + "<br>" +
                    "<div class=\"row\">\n" +
                    "   <div class=\"input-field col s8 required\">\n" +
                    "       <input id=\"id-money-amount\" name= type=\"text\" required>\n" +
                    "   </div>\n" +
                    "   <label for=\"id-money-amount\">Add funds</label>" +
                    "   <button class=\"btn-floating waves-effect waves-light black\" onclick=\"addFunds()\">" +
                    "       <i class=\"material-icons\">add</i>" +
                    "   </button>\n" +
                    "</div>";
        }
        try {
            pageContext.getOut().write("<hr/>" + output + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
