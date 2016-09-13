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
            output = "<div class=\"userView\">\n" +
                    "   <img src=\""+ user.getPhotoPath() + "\" alt=\"\" class=\"circle\">\n" +
                    "   <span class=\"black-text\">"+ user.getLogin() + "</span>\n" +
                    "</div>";
        }else {
            output = "<div class=\"userView\">\n" +
                    "   <img src=\""+ user.getPhotoPath() + "\" alt=\"\" class=\"circle\">\n" +
                    "   <span class=\"black-text\">"+ user.getLogin() + "</span>\n" +
                    "   <br>" +
                    "   <span class=\"black-text\">" + user.getBalance() + "</span>\n"+
                    "   <div class=\"input-field col s6 required\">\n" +
                    "       <input id=\"id-money-amount\" name= type=\"text\" required>\n" +
                    "   </div>\n" +
                    "   <label for=\"id-money-amount\"><fmt:message key=\"label.name\"/></label>" +
                    "   <button class=\"btn col s12\" onclick=\"addFunds()\">" +
                    "       <i class=\"material-icons\">add</i>" +
                    "   </button>";
        }
        try {
            pageContext.getOut().write("<hr/>" + output + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
