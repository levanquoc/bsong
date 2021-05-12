package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UserDao;
import model.User;
import utils.AuthUtil;
import utils.DefineUtil;



public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

    public AdminIndexUserController() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		String searchname="";

		if(request.getParameter("searchuser")!=null) {
			searchname=request.getParameter("searchuser");
			
		}
		UserDao userDao=new UserDao();
		ArrayList<User> listUser= userDao.getSearchname(searchname);
		int numberOfItemUser=userDao.getnumberOfItemUser();
		int numberOfPageUser=  (int)(Math.ceil((float)numberOfItemUser/DefineUtil.NUMBER_PER_PAGE));
		int currentPageUser=1;
		try {
			currentPageUser =Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			
		}
		if(currentPageUser >numberOfPageUser || currentPageUser<1)	{
			currentPageUser=1;
		}
		int offset=(currentPageUser-1)*DefineUtil.NUMBER_PER_PAGE;
		
		ArrayList<User> users= userDao.getItemsPanigation(offset);
		if(!"".equals((searchname))) {
			users=listUser;
		}
		request.setAttribute("users", users);
		request.setAttribute("numberOfItemUser", numberOfItemUser);
		request.setAttribute("numberOfPageUser", numberOfPageUser);
		request.setAttribute("currentPageUser", currentPageUser);
		RequestDispatcher rd=request.getRequestDispatcher("/views/admin/user/index.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
