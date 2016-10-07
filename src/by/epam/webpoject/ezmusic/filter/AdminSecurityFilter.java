package by.epam.webpoject.ezmusic.filter;


import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Антон on 07.09.2016.
 */
public class AdminSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        User currentUser = null;
        if (session != null) {
            currentUser = (User) session.getAttribute(RequestParameter.USER);
        }
        if (currentUser == null) {
            request.setAttribute(RequestParameter.MESSAGE, MessageKey.ADMIN_LOGIN);
            request.getServletContext().getRequestDispatcher(JspPageName.LOGIN).forward(request, response);
        } else {
            if (!currentUser.getIsAdmin()) {
                request.setAttribute(RequestParameter.MESSAGE, MessageKey.ADMIN_LOGIN);
                request.getServletContext().getRequestDispatcher(JspPageName.LOGIN).forward(request, response);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
