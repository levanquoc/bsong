package controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constants.GlobalConstant;
import daos.CategoryDAO;
import daos.SongsDao;
import model.Category;
import model.Songs;
import utils.AuthUtil;
import utils.FileUtil;
@MultipartConfig
public class AdminSongsEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdminSongsEditController() {
		super();

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		// TODO Auto-generated method stub
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		 
			int id =0;
			try {
				id=Integer.parseInt(request.getParameter("id"));
				
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/404");
				return;
			}
			//System.out.println("ID : "+id);
			SongsDao songDao=new SongsDao();
			Songs song=songDao.getById(id);
			request.setAttribute("song",song);
			CategoryDAO categoryDAO=new CategoryDAO();
			List<Category> categories = categoryDAO.getCategories();
			request.setAttribute("categories", categories);
			
			
			
			
			//Category item=cat.getById(id);
			//request.setAttribute("cat", item);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/edit.jsp");
			rd.forward(request, response);

		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		SongsDao songDao= new SongsDao();
		int id = Integer.parseInt(request.getParameter("id"));
		
		Songs songdao=songDao.getById(id);
		String name = request.getParameter("name");
		int cat_id  =Integer.parseInt(request.getParameter("category"));
		Part filePart = request.getPart("picture");
		String fileName =FileUtil.getName(filePart);	
		String rootPath = request.getServletContext().getRealPath("");
		String DirUploadPath = rootPath + "uploads";
		File createDir = new File(DirUploadPath);
		if (!createDir.exists()) {
			createDir.mkdir();
		}
		String picture="";	
		if(fileName.isEmpty()) {
			picture=songdao.getPicture();
		}
		else {
			 picture=FileUtil.rename(fileName);
		}
		StringBuilder sb = new StringBuilder();
		String filePath = sb.append(DirUploadPath).append(File.separator).append(picture).toString();
		
		String preview_text = request.getParameter("preview");
		String detail_text = request.getParameter("detail");
		Songs song=new Songs(id,name,preview_text,detail_text,picture,new Category(cat_id));
		int countRecordUpdated = songDao.update(song);
		StringBuilder sbd = new StringBuilder();
		String url = "";
		if(countRecordUpdated > 0) {
			if(!fileName.isEmpty()) {
				String oldfilePath = DirUploadPath+File.separator +songdao.getPicture();
				File oldFile=new File(oldfilePath);
				
				if(oldFile.exists()) {
					oldFile.delete();
				}
				filePart.write(filePath);
			}
			url = sbd.append(request.getContextPath())
					.append("/admin/song/index?msg=")
					.append(GlobalConstant.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
		response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+GlobalConstant.ERROR);
	}

}
