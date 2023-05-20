package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.UserBean;
import model.dao.CommentDao;
import model.dao.OrderDao;
import model.dao.OrderItemDao;
import model.dao.ProductDao;
import model.dao.UserDao;
import model.enumeration.UserStatus;
import model.enumeration.UserType;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns="/users/*")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			return;
		}
		switch(request.getRequestURI()) {
		case "/admin/users/new":
			System.out.println("new");
			request.getRequestDispatcher("/WEB-INF/pages/user-new.jsp").forward(request, response);;
			break;
		case "/admin/users/add":
			System.out.println("add");
			addUser(request,response);
			break;
		case "/admin/users/details":
			System.out.println("det");
			showUser(request,response);
			break;
		case "/admin/users/edit":
			System.out.println("edi");
			showUserEdit(request,response);
			break;
		case "/admin/users/update":
			System.out.println("edi");
			updateUser(request,response);
			break;
		case "/admin/users/delete":
			System.out.println("del");
			deleteUser(request,response);
			break;
		default:
			listUsers(request,response);
			break;
		}
		System.out.println(request.getRequestURI());
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int page = 1;
			int recordsPerPage = 10;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			int noOfRecords = UserDao.countAll();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("numOfPagesUsers", noOfPages);
			request.setAttribute("currentPageUsers", page);
			request.setAttribute("users", UserDao.getAll((page - 1) * recordsPerPage, recordsPerPage));
			request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			UserBean user=new UserBean();
			
			user.setAddress(request.getParameter("adr"));
			user.setCity(request.getParameter("city"));
			user.setCountry(request.getParameter("country"));
			user.setFirstName(request.getParameter("fname"));
			user.setLastName(request.getParameter("lname"));
			user.setUsername(request.getParameter("uname"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("pwd"));
			user.setType(UserType.valueOf(request.getParameter("type")));
			user.setStatus(UserStatus.valueOf(request.getParameter("status")));
			user.setPhone(request.getParameter("phone"));
			user.setAvatarPath(request.getParameter("file"));
			UserDao.add(user);
	
			response.sendRedirect("/admin/users");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {			
			UserBean user=new UserBean();
			user.setAddress(request.getParameter("adr"));
			user.setCity(request.getParameter("city"));
			user.setCountry(request.getParameter("country"));
			user.setId( Long.parseLong(request.getParameter("id")));
			user.setFirstName(request.getParameter("fname"));
			user.setLastName(request.getParameter("lname"));
			user.setUsername(request.getParameter("uname"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("pwd"));
			user.setType(UserType.valueOf(request.getParameter("type")));
			user.setStatus(UserStatus.valueOf(request.getParameter("status")));
			user.setPhone(request.getParameter("phone"));
			user.setAvatarPath(request.getParameter("file"));
			UserDao.update(user);
	
			response.sendRedirect("/admin/users");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void showUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			UserBean user=UserDao.getById(id);
			request.setAttribute("user", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/user-details.jsp").forward(request, response);
	}
	private void showUserEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			UserBean user=UserDao.getById(id);
			request.setAttribute("user", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/user-edit.jsp").forward(request, response);
	}
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			UserDao.deleteById(id);
			OrderItemDao.deleteByUserId(id);
			OrderDao.deleteByUserId(id);
			ProductDao.deleteByUserId(id);
			CommentDao.deleteByUserId(id);
			
			response.sendRedirect("/admin/users");
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
