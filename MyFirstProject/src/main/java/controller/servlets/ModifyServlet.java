package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbControler = new DatabaseController();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateId = request.getParameter("updateId");
		String deleteId = request.getParameter("deleteId");
		System.out.println("post triggered" + deleteId);

		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("put triggered");

		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		if (dbControler.deleteStudentInfo(req.getParameter("deleteId")) == 1) {
			req.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_DELETE_MESSAGE);
			resp.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
		} else {
			req.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_DELETE_MESSAGE);
			resp.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
		}
	}

}
