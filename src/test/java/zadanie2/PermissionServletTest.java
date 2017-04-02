package zadanie2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import servlets.PermissionsServlet;

@RunWith(MockitoJUnitRunner.class)
public class PermissionServletTest extends Mockito {
	
	@Test
	public void shouldRedirectToUserListServletIfUserIsInDatabase() throws IOException, ServletException {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PermissionsServlet sut = new PermissionsServlet();

		// when
		when(request.getParameter("name")).thenReturn("admin");
		when(request.getParameter("password")).thenReturn("1234");
		sut.doGet(request, response);

		// then
		verify(response).sendRedirect("/UserListServlet");
	}

	@Test
	public void shouldRedirectToErrorPageIfUserIsNotInDatabase() throws IOException, ServletException {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PermissionsServlet sut = new PermissionsServlet();

		// when
		when(request.getParameter("name")).thenReturn("xyz");
		when(request.getParameter("password")).thenReturn("xxxx");
		sut.doGet(request, response);

		// then
		verify(response).sendRedirect("/errorpage.jsp");
	}

}
