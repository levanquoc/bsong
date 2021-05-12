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

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
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
		CategoryDAO categoryDAO = new CategoryDAO();
		String name = request.getParameter("name");
		if ("".equals(name)) {
			response.sendRedirect(request.getContextPath() +"/admin/cat/add?msg=empty");
			return;
		}

		Category category = new Category(name);
		if(categoryDAO.hasCategory(category)) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/add?msg=1");
			return;
		}
		int countRecordInserted = categoryDAO.add(category);
		StringBuilder sbd = new StringBuilder();
		String url = "";
		if (countRecordInserted > 0) {
			url = sbd.append(request.getContextPath()).append("/admin/cat/index?msg=").append(GlobalConstant.SUCCESS)
					.toString();
			response.sendRedirect(url);
			return;
		}
		// fail
		response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=" + GlobalConstant.ERROR);
		return;

	}
}
