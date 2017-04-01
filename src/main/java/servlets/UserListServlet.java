package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.HyperSQLUserManagementRepository;
import repository.UserManagementRepository;
import users.User;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserManagementRepository db = HyperSQLUserManagementRepository.getInstance();
		List<User> dbusers = db.getUsers();
		response.getWriter().flush();
		request.getRequestDispatcher("includes/header.html").include(request, response);
		PrintWriter out = response.getWriter();
		out.println("<h1>Users list</h1>");
		out.println("<table>");
		out.println("<tr><th>Username</th><th>Password</th><th>Email</th><th>Role</th></tr>");
		for (User user : dbusers) {
			out.println("<tr><td>" + user.getUsername() + "</td>" + "<td>" + user.getPassword() + "</td>" + "<td>"
					+ user.getEmail() + "</td>" + "<td>" + user.getUserRole() + "</td></tr>");
		}
		out.println("</table>");
		request.getRequestDispatcher("includes/footer.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
