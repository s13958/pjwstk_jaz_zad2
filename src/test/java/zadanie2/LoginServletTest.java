package zadanie2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import servlets.LoginServlet;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest extends Mockito {

	@Test
	public void shouldRedirectToProfileServletIfLoginIsCorrect() throws IOException, ServletException {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		LoginServlet sut = new LoginServlet();

		// when
		when(request.getParameter("name")).thenReturn("admin");
		when(request.getParameter("password")).thenReturn("1234");
		sut.doGet(request, response);

		// then
		verify(response).sendRedirect("/ProfileServlet");
	}

	@Test
	public void shouldRedirectToErrorPageIfLoginIsIncorrect() throws IOException, ServletException {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		LoginServlet sut = new LoginServlet();

		// when
		when(request.getParameter("name")).thenReturn("invalid");
		when(request.getParameter("password")).thenReturn("user");
		sut.doGet(request, response);

		// then
		verify(response).sendRedirect("/errorpage.jsp");
	}
}
