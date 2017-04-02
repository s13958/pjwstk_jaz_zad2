package zadanie2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import servlets.UserListServlet;

@RunWith(MockitoJUnitRunner.class)
public class UserListServletTest extends Mockito {
	
	@Test
	public void shouldPrintListOfAvailableUsers()
			throws IOException, ServletException {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		UserListServlet sut = new UserListServlet();
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);

		// when
		sut.doGet(request, response);

		// then
		verify(writer).println("<h1>Users list</h1>");
	}

}
