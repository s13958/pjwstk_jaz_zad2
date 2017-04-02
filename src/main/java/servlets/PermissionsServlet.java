package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.HyperSQLUserManagementRepository;
import repository.UserManagementRepository;

@WebServlet("/PermissionsServlet")
public class PermissionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PermissionsServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		UserManagementRepository db = HyperSQLUserManagementRepository.getInstance();
		String username = request.getParameter("username");
		if (db.grantPremium(username)) {
			response.sendRedirect("/UserListServlet");
		} else {
			response.sendRedirect("errorpage.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
