package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/UserProfileServlet","/CheckOutServlet"})
public class LoginFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("LoginFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String user = (session != null) ? (String) session.getAttribute("isUser") : null;

        String loginURI = req.getContextPath() + "/login.jsp";
        System.out.println(loginURI);

        boolean loggedInUser = (user != null);

        if (loggedInUser) { 
            chain.doFilter(request, response); // Continue the request
        } else {
            res.sendRedirect(loginURI); // User is not logged in, redirect to login page
        }
    }

    @Override
    public void destroy() {
        // Close resources if needed
    }
}
