package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FilterImageUrlRewrite implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) request;
        String requestURI = hsr.getRequestURI();
        if (requestURI.contains("/image/")) {
            String[] requestURISplit = requestURI.split("/");
            String fileName = requestURISplit[requestURISplit.length - 1];
            String newRequestURI = "/images?filename="+fileName;
            request.getRequestDispatcher(newRequestURI).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
