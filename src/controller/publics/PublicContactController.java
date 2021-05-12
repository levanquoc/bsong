package controller.publics;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daos.ContactDao;
import model.Contact;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicContactController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String website=request.getParameter("website");
		String message=request.getParameter("message");
		Contact contact=new Contact(0, name, email, website, message);
		ContactDao item=new ContactDao();
		int countRecordAdd =item.addItem(contact);
		if(countRecordAdd>0) {
			response.sendRedirect(request.getContextPath()+"/contact?msg=succsess");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/contact?msg=error");
		
	}

}
