<%@page import="utils.StringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Songs"%>
<%@page import="daos.SongsDao"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="daos.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
	<form id="formsearch" name="formsearch" method="get" action="<%=request.getContextPath()%>/home">
		<span> <input name="song_search" class="editbox_search"
			id="editbox_search" maxlength="80" value="<%if(request.getParameter("song_search")!=null) out.print(request.getParameter("song_search"));%>"placeholder="Tìm kiếm bài hát"
			type="text" />
		</span> <input name="button_search"
			src="<%=request.getContextPath()%>/resources/public/images/search.jpg"
			class="button_search" type="image" />
	</form>
</div>
<div class="clr"></div>
<div class="gadget">
	<h2 class="star">Danh mục bài hát</h2>

	<div class="clr"></div>
	<ul class="sb_menu">
		<%
			CategoryDAO categories = new CategoryDAO();
			List<Category> cats = categories.getCategories();
			if (cats != null && cats.size() > 0) {
				for (Category cat : cats) {
					int id = cat.getId();
					String name = cat.getName();
					String url=request.getContextPath()+"/category/" +StringUtil.makeSlug(name)+"-"+id+".html";
		%>
		<li><a href="<%=url%>"><%=name%></a></li>
		<%
			}

			}
		%>
	</ul>
</div>

<div class="gadget">
	<h2 class="star">
		<span>Bài hát mới</span>
	</h2>
	<div class="clr"></div>
	<ul class="ex_menu">
		<%
			SongsDao songDao = new SongsDao();
			List<Songs> songList = songDao.getAll(6);

			if (songList != null && songList.size() > 0) {
				for (Songs songItem : songList) {
					String name = songItem.getName();
					
		%>
		<li><a href="<%=request.getContextPath() +"/detail"+ "/" + StringUtil.makeSlug(songItem.getCategory().getName()) + "/"
								+StringUtil.makeSlug(songItem.getName()) + "-" + songItem.getId() + ".html"%>"><%=name%></a><br /><%if(songItem.getPreview_text().length()>50) out.print(songItem.getPreview_text().substring(0,50)+"....");else out.print(songItem.getPreview_text()); %> </li>
		<%
			}
			}
		%>

	</ul>
</div>