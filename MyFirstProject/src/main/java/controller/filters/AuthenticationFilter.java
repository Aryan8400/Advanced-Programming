package controller.filters;

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

public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Cast the request and response to HttpServletRequest and HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get the requested URI
		String uri = req.getRequestURI();
		
		if(uri.endsWith(".css") || uri.endsWith("home.jsp") ){
			chain.doFilter(request, response);
			return;
		}
		
		// Check if the request is for login or logout
		boolean isLogin = uri.endsWith("login.jsp");
		boolean isLoginServlet = uri.endsWith("LoginServlet");
		boolean isLogoutServlet = uri.endsWith("LogoutServlet");

		// Get the session and check if user is logged in
		HttpSession session = req.getSession(false);
		boolean isLoggedIn = session != null && session.getAttribute("username") != null;

		// If user is not logged in and not trying to login, redirect to login page
		if (!isLoggedIn && !(isLogin || isLoginServlet)) {
			res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
		} 
		// If user is logged in and not trying to logout, redirect to home page
		else if (isLoggedIn && !(!isLogin || isLogoutServlet)) {
			res.sendRedirect(req.getContextPath() + "/pages/home.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {}
}
