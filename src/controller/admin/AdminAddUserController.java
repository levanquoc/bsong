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


public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserDao userDao;
     
    public AdminAddUserController() {
        super();
        userDao=new UserDao();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		HttpSession session= request.getSession();
		User userLogin=(User)session.getAttribute("userLogin");
		if(!"admin".equals(userLogin.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=3");
			return ;
		}
		RequestDispatcher rd=request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		HttpSession session= request.getSession();
		User userLogin=(User)session.getAttribute("userLogin");
		if(!"admin".equals(userLogin.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=3");
			return ;
		}
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		password=StringUtil.md5(password);
		String fullname=request.getParameter("fullname");
		if("".equals(username)||"".equals(password)||"".equals(fullname)) {
			response.sendRedirect(request.getContextPath()+"/admin/user/add?err=4");
			return;
		}
		User user=new User(0, username, password, fullname);
		if(userDao.hasUser(user.getUsername())) {
			RequestDispatcher rd=request.getRequestDispatcher("/views/admin/user/add.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		if(userDao.addItem(user)>0){
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=success");
			return;
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("/views/admin/user/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
