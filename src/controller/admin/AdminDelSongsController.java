package controller.admin;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongsDao;
import model.Songs;
import utils.AuthUtil;

public class AdminDelSongsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelSongsController() {
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
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/404");
			return;
		}
		String rootPath = request.getServletContext().getRealPath("");
		String DirUploadPath = rootPath + "uploads";
		SongsDao songDao=new SongsDao();
		Songs songdao=songDao.getById(id);
		int countRecordDel =songDao.del(id);
		if(countRecordDel>0) {
			String oldfilePath = DirUploadPath+File.separator +songdao.getPicture();
			File oldFile=new File(oldfilePath);
			
			if(oldFile.exists()) {
				oldFile.delete();
			}
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg=success");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/admin/song/index?msg=error");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
