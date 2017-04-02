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

@WebFilter({ "login.jsp", "registration.jsp" })
public class RegistrationAndLoginFilter implements Filter {

	public RegistrationAndLoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();

		if (!(session.getAttribute("usersession") == null)) {
			User user = (User) session.getAttribute("usersession");
			UserRole userRole = user.getUserRole();

			if (userRole.equals(UserRole.ADMINISTRATOR) || userRole.equals(UserRole.PREMIUM)
					|| userRole.equals(UserRole.REGULAR))
				((HttpServletResponse) response).sendRedirect("/ProfileServlet");
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
