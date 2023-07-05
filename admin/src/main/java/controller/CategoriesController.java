package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.AttributeBean;
import model.bean.CategoryBean;
import model.dao.CategoryDao;
import model.dao.ProductDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns="/categories/*")
public class CategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			return;
		}
		switch(request.getRequestURI()) {
		case "/admin/categories/new":
			System.out.println("new");
			request.getRequestDispatcher("/WEB-INF/pages/category-new.jsp").forward(request, response);
			break;
		case "/admin/categories/add":
			System.out.println("add");
			addCategory(request,response);
			break;
		case "/admin/categories/edit":
			System.out.println("editc");
			showCategoryEdit(request,response);
			break;
		case "/admin/categories/update":
			System.out.println("updatr");
		    editCategory(request,response);
			break;
		case "/admin/categories/attributes":
			System.out.println("atr");
			listAttributes(request,response);
			break;
		case "/admin/categories/attributes/new":
			System.out.println("newatr");
			request.setAttribute("selectedCat",request.getParameter("id"));
			System.out.println(request.getParameter("id"));
			request.getRequestDispatcher("/WEB-INF/pages/attribute-new.jsp").forward(request, response);
			break;
		case "/admin/categories/attributes/delete":
			System.out.println("delatr");
			deleteAttribute(request,response);
			break;
		case "/admin/categories/attributes/add":
			System.out.println("addatr");
			addAttribute(request,response);
			break;
		case "/admin/categories/attributes/edit":
			System.out.println("editatr");
			showAttributeEdit(request,response);
			break;
		case "/admin/categories/attributes/update":
			System.out.println("updatr");
		    editAttribute(request,response);
			break;
		case "/admin/categories/delete":
			System.out.println("del");
			deleteCategory(request,response);
			break;
		default:
			listCategories(request,response);
			break;
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int page = 1;
			int recordsPerPage = 10;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			int noOfRecords = CategoryDao.countAll();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("numOfPagesCategories", noOfPages);
			request.setAttribute("currentPageCategories", page);
			request.setAttribute("categories", CategoryDao.getAll((page - 1) * recordsPerPage, recordsPerPage));
			request.getRequestDispatcher("/WEB-INF/pages/categories.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void listAttributes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("selectedCategoryId", id.toString());
			ArrayList<AttributeBean> attributes=CategoryDao.getAtributtesByCategoryId(id);
			int page = 1;
			int recordsPerPage = 10;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			int noOfRecords = attributes==null?0:attributes.size();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("numOfPagesAttributes", noOfPages);
			request.setAttribute("currentPageAttributes", page);
			request.setAttribute("attributes", attributes.subList((page - 1) * recordsPerPage, (noOfRecords<10)?noOfRecords:recordsPerPage));
			request.getRequestDispatcher("/WEB-INF/pages/attributes.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			ProductDao.deleteByCategoryId(id);
			CategoryDao.deleteById(id);
			
			response.sendRedirect("/admin/categories");
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void addAttribute(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			AttributeBean attribute = new AttributeBean();
			attribute.setCategoryId(Long.parseLong(request.getParameter("id")));
			attribute.setName(request.getParameter("aname"));
			attribute.setValue(request.getParameter("value"));
			attribute.setUnit(request.getParameter("unit"));
			attribute.setPossibleValues(request.getParameter("pvalues"));
			CategoryBean cat=CategoryDao.getById(Long.parseLong(request.getParameter("id")));
			cat.getSpecialAttributes().add(attribute);
			CategoryDao.update(cat);
			response.sendRedirect("/admin/categories/attributes?id="+cat.getId());
		} catch (NumberFormatException | SQLException  | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void editAttribute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			AttributeBean attribute = new AttributeBean();
			attribute.setCategoryId(Long.parseLong(request.getParameter("id")));
			attribute.setName(request.getParameter("aname"));
			attribute.setValue(request.getParameter("value"));
			attribute.setUnit(request.getParameter("unit"));
			attribute.setPossibleValues(request.getParameter("pvalues"));
			CategoryBean cat=CategoryDao.getById(Long.parseLong(request.getParameter("id")));
			cat.getSpecialAttributes().remove(Integer.parseInt(request.getParameter("sel")));
			cat.getSpecialAttributes().add(attribute);
			CategoryDao.update(cat);
			response.sendRedirect("/admin/categories/attributes?id="+cat.getId());
		} catch (NumberFormatException | SQLException  | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void showAttributeEdit(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Integer selectedId=Integer.parseInt(request.getParameter("sel"));
			CategoryBean cat=CategoryDao.getById(Long.parseLong(request.getParameter("id")));
			AttributeBean attribute = cat.getSpecialAttributes().get(selectedId);
			request.setAttribute("selectedId", selectedId);
			request.setAttribute("selectedAttr", attribute);
			request.getRequestDispatcher("/WEB-INF/pages/attribute-edit.jsp").forward(request, response);
		} catch (NumberFormatException | SQLException  | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void deleteAttribute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			CategoryBean cat=CategoryDao.getById(Long.parseLong(request.getParameter("id")));
			cat.getSpecialAttributes().remove(Integer.parseInt(request.getParameter("sel")));
			CategoryDao.update(cat);
			response.sendRedirect("/admin/categories/attributes?id="+cat.getId());
		} catch (NumberFormatException | SQLException  | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void addCategory(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			CategoryBean category = new CategoryBean();
			category.setName(request.getParameter("cname"));
			category.setSpecialAttributes(new ArrayList<>());
			CategoryDao.add(category);
			response.sendRedirect("/admin/categories");
		} catch (NumberFormatException | SQLException  | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void showCategoryEdit(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			CategoryBean category=CategoryDao.getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("selectedCat", category);
			request.getRequestDispatcher("/WEB-INF/pages/category-edit.jsp").forward(request, response);
		} catch (NumberFormatException | SQLException  | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void editCategory(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			CategoryBean category = CategoryDao.getById(Long.parseLong(request.getParameter("id")));
			category.setName(request.getParameter("cname"));
			CategoryDao.update(category);
			response.sendRedirect("/admin/categories");
		} catch (NumberFormatException | SQLException  | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
