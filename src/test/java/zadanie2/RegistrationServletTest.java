package zadanie2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import servlets.RegistrationServlet;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServletTest extends Mockito {

	@Test
	public void shouldRedirectToLoginPageIfUsernameIsAvailableAndPasswordIsCorrect()
			throws IOException, ServletException {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RegistrationServlet sut = new RegistrationServlet();

		// when
		when(request.getParameter("name")).thenReturn("user9");
		when(request.getParameter("password")).thenReturn("1234");
		when(request.getParameter("confirmPassword")).thenReturn("1234");
		when(request.getParameter("email")).thenReturn("user9@homepage.com");
		sut.doGet(request, response);

		// then
		verify(response).sendRedirect("login.jsp");
	}

	@Test
	public void shouldRedirectToErrorPageIfPasswordsAreNotMatching() throws IOException, ServletException {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RegistrationServlet sut = new RegistrationServlet();

		// when
		when(request.getParameter("name")).thenReturn("user9");
		when(request.getParameter("password")).thenReturn("123224");
		when(request.getParameter("confirmPassword")).thenReturn("1234");
		when(request.getParameter("email")).thenReturn("user9@homepage.com");
		sut.doGet(request, response);

		// then
		verify(response).sendRedirect("errorpage.jsp");
	}
	
	@Test
	public void shouldRedirectToErrorPageIfUserIsAlreadyInDatabase() throws IOException, ServletException {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RegistrationServlet sut = new RegistrationServlet();

		// when
		when(request.getParameter("name")).thenReturn("admin");
		when(request.getParameter("password")).thenReturn("1234");
		when(request.getParameter("confirmPassword")).thenReturn("1234");
		when(request.getParameter("email")).thenReturn("admin@homepage.com");
		sut.doGet(request, response);

		// then
		verify(response).sendRedirect("errorpage.jsp");
	}

}
