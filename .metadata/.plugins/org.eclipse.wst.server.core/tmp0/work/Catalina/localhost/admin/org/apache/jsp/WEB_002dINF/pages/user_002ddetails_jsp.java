/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.0.23
 * Generated at: 2023-06-27 18:43:10 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import model.bean.UserBean;

public final class user_002ddetails_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/fragments/navbar.jspf", Long.valueOf(1683477100543L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("model.bean.UserBean");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<link\r\n");
      out.write("	href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\"\r\n");
      out.write("	rel=\"stylesheet\">\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("	<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/user-details.css\" rel='stylesheet' type='text/css' />\r\n");
      out.write("<title>User</title>\r\n");
      out.write("</head>\r\n");
      out.write(" <body>\r\n");
      out.write(" ");
      out.write("<nav class=\"navbar navbar-expand-sm bg-primary navbar-dark\">\r\n");
      out.write("  <div class=\"container-fluid\">\r\n");
      out.write("    <a class=\"navbar-brand\" href=\"#\">Admin Panel</a>\r\n");
      out.write("    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collapsibleNavbar\">\r\n");
      out.write("      <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("    </button>\r\n");
      out.write("    <div class=\"collapse navbar-collapse\" id=\"collapsibleNavbar\">\r\n");
      out.write("      <ul class=\"navbar-nav\">\r\n");
      out.write("        <li class=\"nav-item\">\r\n");
      out.write("          <a class=\"nav-link\" href=\"/admin/users\">Users</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"nav-item\">\r\n");
      out.write("          <a class=\"nav-link\" href=\"/admin/categories\">Categories</a>\r\n");
      out.write("        </li>\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("          <a class=\"nav-link\" href=\"/admin/logs\">Logs</a>\r\n");
      out.write("        </li>\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("          <a class=\"nav-link\" href=\"/admin/logout\">Logout</a>\r\n");
      out.write("        </li>\r\n");
      out.write("      </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</nav>");
      out.write('\r');
      out.write('\n');
      out.write(' ');

    UserBean user=null;
	Object o=request.getAttribute("user");
    if(o!=null){
    	user=(UserBean)o;
    }
	
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("        <div>\r\n");
      out.write("            <h3 class=\"title\">User account details</h3>\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4\"> \r\n");
      out.write("                <label for=\"fname\" class=\"form-label\">First name:</label>\r\n");
      out.write("                <input type=\"text\" class=\"form-control\" id=\"fname\" readonly value=\"");
      out.print(user.getFirstName() );
      out.write("\" name=\"fname\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4\">\r\n");
      out.write("                <label for=\"lname\" class=\"form-label\">Last name:</label>\r\n");
      out.write("                <input type=\"text\" class=\"form-control\" id=\"lname\" readonly value=\"");
      out.print(user.getLastName() );
      out.write("\" name=\"lname\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4\">\r\n");
      out.write("                <label for=\"email\" class=\"form-label\">E-mail:</label>\r\n");
      out.write("                <input type=\"email\" class=\"form-control\" id=\"email\" readonly value=\"");
      out.print(user.getEmail());
      out.write("\" name=\"email\">\r\n");
      out.write("                </div>\r\n");
      out.write("                \r\n");
      out.write("           </div>\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\">\r\n");
      out.write("                <label for=\"phone\" class=\"form-label\">Phone number:</label>\r\n");
      out.write("                <input type=\"tel\" class=\"form-control\" id=\"phone\" readonly value=\"");
      out.print(user.getPhone() );
      out.write("\" name=\"phone\" pattern=\"^\\d{9}$\">\r\n");
      out.write("                </div>\r\n");
      out.write("                  <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\"> \r\n");
      out.write("                <label for=\"uname\" class=\"form-label\">Username:</label>\r\n");
      out.write("                <input type=\"text\" class=\"form-control\" id=\"uname\" readonly value=\"");
      out.print(user.getUsername() );
      out.write("\" name=\"uname\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\">\r\n");
      out.write("                <label for=\"pwd\" class=\"form-label\">Password:</label>\r\n");
      out.write("                <input type=\"password\" class=\"form-control\" id=\"pwd\" readonly value=\"some lame pass\" name=\"pwd\">\r\n");
      out.write("                </div>\r\n");
      out.write("           </div>\r\n");
      out.write("           <div class=\"row\">\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\">\r\n");
      out.write("                <label for=\"adr\" class=\"form-label\">Address:</label>\r\n");
      out.write("                <input type=\"text\" class=\"form-control\" id=\"adr\" readonly value=\"");
      out.print(user.getAddress() );
      out.write("\" name=\"adr\" >\r\n");
      out.write("                </div>\r\n");
      out.write("                 <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\"> \r\n");
      out.write("                <label for=\"city\" class=\"form-label\">City:</label>\r\n");
      out.write("                <input type=\"text\" class=\"form-control\" id=\"city\" readonly value=\"");
      out.print(user.getCity() );
      out.write("\" name=\"city\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\">\r\n");
      out.write("                <label for=\"country\" class=\"form-label\">Country:</label>\r\n");
      out.write("                <input type=\"text\" class=\"form-control\" id=\"country\" readonly value=\"");
      out.print(user.getCountry() );
      out.write("\" name=\"country\">\r\n");
      out.write("                </div>\r\n");
      out.write("           </div>\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\"> \r\n");
      out.write("                <label for=\"type\" class=\"form-label\">Type:</label>\r\n");
      out.write("                <input type=\"text\" class=\"form-control\" id=\"type\" readonly value=");
      out.print(user.getType() );
      out.write(" name=\"type\"> \r\n");
      out.write("				</div>\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\">\r\n");
      out.write("                <label for=\"status\" class=\"form-label\">Status:</label>\r\n");
      out.write("                <input type=\"text\" class=\"form-control\" id=\"status\" readonly value=");
      out.print(user.getStatus() );
      out.write(" name=\"status\"> \r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-xs-4 col-md-4 col-sm-4 mt-2\">\r\n");
      out.write("  				<label for=\"formFile\" class=\"form-label\">Profile picture:</label>\r\n");
      out.write("  				<input type=\"text\" class=\"form-control\" id=\"formFile\" readonly value=");
      out.print(user.getAvatarPath() );
      out.write(" name=\"formFile\"> \r\n");
      out.write("				</div>\r\n");
      out.write("           </div>            \r\n");
      out.write("        </div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
