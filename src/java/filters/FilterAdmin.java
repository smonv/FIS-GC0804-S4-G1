package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FilterAdmin implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
//        if(session.getAttribute("admin") == null){
//            session.setAttribute("msg", "Please log in to access that page!");
//            res.sendRedirect(req.getContextPath() + "/login-admin.xhtml");
//        }else{
//            chain.doFilter(request, response);
//        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
}
