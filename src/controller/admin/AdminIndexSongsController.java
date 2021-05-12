package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongsDao;

import model.Songs;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexSongsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexSongsController() {
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
		String searchname="";
		if(request.getParameter("searchname")!=null) {
		 searchname=request.getParameter("searchname");
		 
		}
		SongsDao songsDAO = new SongsDao();
		List<Songs> listsearch = songsDAO.getSearchName(searchname);
		
		int numberOfItems=songsDAO.numberOfItems();
		int numberOfPages= (int)Math.ceil((float) numberOfItems/DefineUtil.NUMBER_PER_PAGE);
		int currentPage=1;
		try {
			currentPage =Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			
		}
		if(currentPage >numberOfPages || currentPage<1)	{
			currentPage=1;
		}
		int offset=(currentPage-1)*DefineUtil.NUMBER_PER_PAGE;
		List<Songs> songs = songsDAO.getItemPanigation(offset);
		if(!"".equals(searchname)) {
			songs=listsearch;
		}
		request.setAttribute("songs", songs);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfItems", numberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
