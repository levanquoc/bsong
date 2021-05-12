package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daos.ContactDao;

import model.Contact;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexContactController() {
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
		String searchContact="";
		if(request.getParameter("searchcontact")!=null) {
			searchContact=request.getParameter("searchcontact");
		}
		ContactDao contactDao= new ContactDao();
		List<Contact> listcontact = contactDao.getSearchContact(searchContact);
		int numberOfItemContact=contactDao.numberOfItem();
		int numberOfPageContact= (int)Math.ceil((float)numberOfItemContact/DefineUtil.NUMBER_PER_PAGE);
		int currentPageContact=1;
		if(request.getParameter("page")!=null) {
			currentPageContact=Integer.parseInt(request.getParameter("page"));
		}
		if(currentPageContact>numberOfPageContact||currentPageContact<1) {
			currentPageContact=1;
		}
		int offset=(currentPageContact-1)*DefineUtil.NUMBER_PER_PAGE;
		List<Contact> contact = contactDao.getContactPanagition(offset);
		if(!"".equals(searchContact)) {
			contact=listcontact;
		}
		request.setAttribute("contact",contact);
		request.setAttribute("numberOfItemContact",numberOfItemContact);
		request.setAttribute("numberOfPageContact",numberOfPageContact);
		request.setAttribute("currentPageContact",currentPageContact);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
