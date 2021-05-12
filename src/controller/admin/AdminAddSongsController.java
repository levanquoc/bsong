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
public class AdminAddSongsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddSongsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getCategories();
		request.setAttribute("cat", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		SongsDao songDAO = new SongsDao();
		StringBuilder sbd = new StringBuilder();
		String name = request.getParameter("name");
		int catId = 0;
		try {
			catId = Integer.parseInt(request.getParameter("category"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?error=1");
			return;
		}
		String description = request.getParameter("preview");
		String detail = request.getParameter("detail");
		if("".equals(name) ||"".equals(description)||"".equals(detail)) {
			response.sendRedirect(request.getContextPath() + "/admin/song/add?error=1");
			return;
		}
		Part filePart = request.getPart("picture");
		String fileName = FileUtil.getName(filePart);
		String picture = FileUtil.rename(fileName);
		
		String rootPath = request.getServletContext().getRealPath("");
		String DirUploadPath = rootPath + "uploads";


		File createDir = new File(DirUploadPath);
		if (!createDir.exists()) {
			createDir.mkdir();
		}
		StringBuilder sb = new StringBuilder();
		String filePath = sb.append(DirUploadPath).append(File.separator).append(picture).toString();
		int countRecordInserted = 0;
		Songs song = new Songs(name, description, detail, picture, new Category(catId));
		countRecordInserted = songDAO.add(song);
		String url = "";
		if (countRecordInserted > 0) {
			if (!fileName.isEmpty()) {
				filePart.write(filePath);
			}
			url = sbd.append(request.getContextPath()).append("/admin/song/index?msg=").append(GlobalConstant.SUCCESS)
					.toString();
			response.sendRedirect(url);
			return;
		}
//fail
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getCategories();
		request.setAttribute("cat", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp?error=2");
		rd.forward(request, response);

	}

}
