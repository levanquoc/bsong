package controller.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import model.User;
import utils.StringUtil;


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LoginController() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String username=request.getParameter("username");
		System.out.println(username);
		String password=StringUtil.md5(request.getParameter("password"));
		UserDao userDao=new UserDao();
		User user =userDao.findByUserAndPassword(username,password);
		
		HttpSession session=request.getSession();
		if(user!=null) {
			session.setAttribute("userLogin", user);
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return;
			
		}else {
			response.sendRedirect(request.getContextPath()+"/auth/login?msg=err");
			return;
		}
		
		
	}

}
