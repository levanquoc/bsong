package controller.publics;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daos.CategoryDAO;
import daos.SongsDao;
import model.Category;
import model.Songs;
import utils.DefineUtil;

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {			
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		CategoryDAO categories = new CategoryDAO();
		Category category = categories.getById(id);
		if (category == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		SongsDao songs = new SongsDao();
		int numberOfItemDetail = songs.getNumberofItemDetail(id);
		int numberOfPageDetail = (int) Math.ceil((float) numberOfItemDetail / DefineUtil.NUMBER_PER_PAGE);
		int currentPageDetail = 1;
		try {
			currentPageDetail = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			
		}

		if (currentPageDetail > numberOfPageDetail || currentPageDetail < 0) {
			currentPageDetail = 1;
		}
		int offset = (currentPageDetail - 1) * DefineUtil.NUMBER_PER_PAGE;
		ArrayList<Songs> song = songs.getByCategoryofPageDetail(offset, id);
		request.setAttribute("category", category);
		request.setAttribute("song", song);
		request.setAttribute("numberOfItemDetail", numberOfItemDetail);
		request.setAttribute("numberOfPageDetail", numberOfPageDetail);
		request.setAttribute("currentPageDetail", currentPageDetail);
		request.setAttribute("iddetail", id);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/cat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
