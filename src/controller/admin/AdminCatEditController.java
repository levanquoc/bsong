package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.CategoryDAO;
import model.Category;
import utils.AuthUtil;

public class AdminCatEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public AdminCatEditController() {
		super();

		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		 
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("ID : "+id);
			CategoryDAO cat=new CategoryDAO();
			Category item=cat.getById(id);
			request.setAttribute("cat", item);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
			rd.forward(request, response);

		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CategoryDAO categoryDAO= new CategoryDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Category cat = new Category(id, name);
		int countRecordUpdated = categoryDAO.update(cat);
		StringBuilder sbd = new StringBuilder();
		String url = "";
		if(countRecordUpdated > 0) {
			url = sbd.append(request.getContextPath())
					.append("/admin/cat/index?msg=")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
		response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+GlobalConstant.ERROR);
	}

}
