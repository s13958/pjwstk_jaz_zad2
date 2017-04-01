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

import users.User;
import users.UserRole;

@WebFilter({ "permissions.jsp", "/PermissionsServlet" })
public class PermissionFilter implements Filter {

	public PermissionFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();

		if (session.getAttribute("usersession") == null) {
			((HttpServletResponse) response).sendRedirect("errorpage.jsp");
		}

		User user = (User) session.getAttribute("usersession");
		UserRole userRole = user.getUserRole();

		if (!(userRole.equals(UserRole.ADMINISTRATOR)))
			((HttpServletResponse) response).sendRedirect("errorpage.jsp");

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
