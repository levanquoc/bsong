package controller.publics;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daos.SongsDao;
import model.Songs;
import utils.DefineUtil;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		SongsDao songsDao=new SongsDao();
		String songsearch="";
		if(request.getParameter("song_search")!=null) {
			songsearch=request.getParameter("song_search");
	
		}
		List<Songs>  listsearch=songsDao.getSearchName(songsearch);
		
		int numberOfItems=songsDao.numberOfItems();
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
		List<Songs> songs = songsDao.getItemPanigation(offset);
		if(!"".equals(songsearch)) {
			songs=listsearch;
		}
		request.setAttribute("songs", songs);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfItems", numberOfItems);
		//if(songs!=null && songs.size()>0) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/index.jsp");
			rd.forward(request, response);
			//return;
		//}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
