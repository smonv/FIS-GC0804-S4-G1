package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/client/*")
public class FilterClient implements Filter {
    
    private FilterConfig config;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        if (session.getAttribute("client") == null) {
            session.setAttribute("msg", "Please log in to access that page!");
            session.setAttribute("order_product_details", null);
            session.setAttribute("edit_products", null);
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
        } else if (session.getAttribute("admin") != null) {
            res.sendRedirect(req.getContextPath() + "/403.xhtml");
        } else {
            chain.doFilter(request, response);
        }
        //chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        this.config = null;
    }
    
}
