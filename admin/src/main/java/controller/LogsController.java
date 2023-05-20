package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LogDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/logs")
public class LogsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			return;
		}
		try {
			int page = 1;
			int recordsPerPage = 10;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			int noOfRecords = LogDao.countAll();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("numOfPagesLogs", noOfPages);
			request.setAttribute("currentPageLogs", page);
			request.setAttribute("logs", LogDao.getAll((page - 1) * recordsPerPage, recordsPerPage));
			
			System.out.println(request.getAttribute("numOfPagesLogs"));
			System.out.println(request.getAttribute("currentPageLogs"));
			System.out.println(page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/logs.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
