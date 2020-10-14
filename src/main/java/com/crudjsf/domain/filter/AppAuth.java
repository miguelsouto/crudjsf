package com.crudjsf.domain.filter;

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

import com.crudjsf.domain.model.Login;

/**
 * Servlet Filter implementation class AppAuth
 */
@WebFilter(filterName = "AppAuth", urlPatterns = "/app/*")
public class AppAuth implements Filter {

    public AppAuth() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		Login login = (Login) session.getAttribute("usuario");
		if(login == null) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
