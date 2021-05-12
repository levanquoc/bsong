package controller.publics;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daos.SongsDao;
import model.Songs;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		try {
			id=Integer.parseInt(request.getParameter("id"));
			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/404");
			return;
		}
		SongsDao  songDao=new SongsDao();
		songDao.increaseView(id);
		Songs  songdetail=songDao.getSongdetailById(id);
		if(songdetail==null) {
			response.sendRedirect(request.getContextPath()+"/404");
			return;
		}else {
			
			request.setAttribute("songdetail", songdetail);
		}
		
		ArrayList<Songs> songRelation=songDao.getSongRelation(songdetail,5);
		
		request.setAttribute("songRelation",songRelation);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/detail.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
