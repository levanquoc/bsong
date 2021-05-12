package controller.admin;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import model.User;
import utils.AuthUtil;
import utils.StringUtil;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
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
		int id=0;
		try {
			 id=Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=2");
			return;
		}
		
		UserDao item=new UserDao();
		HttpSession session= request.getSession();
		User userLogin=(User)session.getAttribute("userLogin");
		if("admin".equals(item.getByID(userLogin.getId()).getUsername()) ||(id==userLogin.getId())) {
			User user=item.getByID(id);
			if(user!=null) {
				request.setAttribute("user",user);
				RequestDispatcher rd=request.getRequestDispatcher("/views/admin/user/edit.jsp");
				rd.forward(request, response);
				
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/user/index?err=2");
				return;
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=4");
			
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=2");
			return;
		}
		UserDao item = new UserDao();
		HttpSession session= request.getSession();
		User userLogin=(User)session.getAttribute("userLogin");
		if("admin".equals(item.getByID(userLogin.getId()).getUsername()) ||(id==userLogin.getId())) {
			String username = request.getParameter("username");
			String password = StringUtil.md5(request.getParameter("password"));
			
			String fullname = request.getParameter("fullname");
			User user = new User(id, username, password, fullname);
			UserDao userDao = new UserDao();
			int countRecordUpdated = userDao.update(user);
			if (countRecordUpdated > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=success");
				return;
			}
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=error");
			return;

			
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=4");
		}
		
}
		
}
