package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import model.Category;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		String searchCategory="";
		if(request.getParameter("searchcategory")!=null) {
			searchCategory=request.getParameter("searchcategory");
		}
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> listCategory = categoryDAO.getCategoriesSearch(searchCategory);
		int numberOfItemCat=categoryDAO.numberOfItemCat();
		int numberOfPageCat= (int) Math.ceil((float)numberOfItemCat/DefineUtil.NUMBER_PER_PAGE);
		int currentPageCat=1;
		try {
			currentPageCat=Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		
		}
		if(currentPageCat>numberOfPageCat ||currentPageCat<1)	{
			currentPageCat=1;
		}
		int offset=(currentPageCat-1)*DefineUtil.NUMBER_PER_PAGE;

		List<Category> categories = categoryDAO.getCategoriesPanigation(offset);
		if(!"".equals(searchCategory)) {
			categories=listCategory;
		}
		request.setAttribute("numberOfItemCat", numberOfItemCat);
		request.setAttribute("numberOfPageCat", numberOfPageCat);
		request.setAttribute("currentPageCat", currentPageCat);
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
