package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.HyperSQLUserManagementRepository;
import repository.UserManagementRepository;
import users.User;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("usersession");
		UserManagementRepository db = HyperSQLUserManagementRepository.getInstance();

		response.getWriter().flush();
		request.getRequestDispatcher("includes/header.html").include(request, response);

		response.getWriter().println("<h1>Profil</h1>");
		response.getWriter().println("<b>Username: </b>" + user.getUsername());
		response.getWriter().println("<br/><b>Email: </b>" + db.getEmail(user));
		response.getWriter().println("<br/><b>Role: </b>" + user.getUserRole());

		request.getRequestDispatcher("includes/footer.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
