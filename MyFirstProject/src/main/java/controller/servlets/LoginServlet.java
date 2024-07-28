package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import util.StringUtils;

/**
 * @author prithivi
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		int loginResult = dbController.getStudentLoginInfo(userName, password);

		if (loginResult == 1) {
			HttpSession userSession = request.getSession();
			userSession.setAttribute("username", userName);
			userSession.setMaxInactiveInterval(30*30);
			
			Cookie userCookie= new Cookie("user", userName);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
		    request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_LOGIN_MESSAGE);
		    response.sendRedirect(request.getContextPath() + StringUtils.WELCOME_PAGE);
		} else if (loginResult == 0) {
		    request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_LOGIN_MESSAGE);
		    request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		} else {
		    request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
		    request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}
	}
}
