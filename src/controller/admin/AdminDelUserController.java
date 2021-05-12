package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import model.User;
import utils.AuthUtil;

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelUserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			return;
		}

		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if(userDao.getByID(id)==null) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			return;
		}
		if ("admin".equals(userDao.getByID(id).getUsername())) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=1");
		} else {
			if ("admin".equals(userLogin.getUsername())) {
				// duoc xoa
				if (!userDao.delAdmin(id)) {
					int countRecordDel = userDao.delItem(id);
					if (countRecordDel > 0) {
						response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=success");
						return;
					}
					
					response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=error");
					return;
				}

			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?err=6");
				return;
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
