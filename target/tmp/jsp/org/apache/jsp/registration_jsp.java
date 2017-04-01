package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class registration_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(2);
    _jspx_dependants.add("/includes/header.html");
    _jspx_dependants.add("/includes/footer.html");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('	');
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("  <link href=\"//fonts.googleapis.com/css?family=Raleway:400,300,600\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"includes/normalize.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"includes/skeleton.css\">\n");
      out.write("<title>ZADANIE 2</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<div class=\"twelve columns\">\n");
      out.write("\t\t\t\t<h1>Zadanie 2</h1>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<div id=\"navbar\" class=\"two columns\">\n");
      out.write("\t\t\t<h2>Sub pages</h2>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("\t\t\t\t\t<li><a href=\"login.jsp\">Sign in</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"registration.jsp\">Sign up</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"/ProfileServlet\">Go to profile</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"premium.jsp\">Check premium content</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"/UserListServlet\">Check users</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"permissions.jsp\">Manage permissions</a></li>\n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t<div class=\"ten columns\">");
      out.write("\n");
      out.write("\t<h1>Registration</h1>\n");
      out.write("\t<form action=\"RegistrationServlet\" method=\"get\">\n");
      out.write("\t\t<label>Username: <input type=\"text\" name=\"username\"/></label>\n");
      out.write("\t\t<label>Password: <input type=\"password\" name=\"password\"/></label>\n");
      out.write("\t\t<label>Confirm Password: <input type=\"password\" name=\"confirmPassword\"/></label>\n");
      out.write("\t\t<label>Email: <input type=\"email\" name=\"email\"/></label>\n");
      out.write("\t\t<input type=\"submit\" value=\"Send\" />\n");
      out.write("\t</form>\n");
      out.write("\t");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>");
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
