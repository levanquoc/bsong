<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Songs"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<div class="content_resize">
	<div class="mainbar">
		<%
			@SuppressWarnings("unchecked")
			ArrayList<Songs> songs = (ArrayList<Songs>) request.getAttribute("songs");
			if(songs!=null && songs.size()>0){				
			int i = 0;
			for (Songs item : songs) {
				i++;
				int id = item.getId();
				String name = item.getName();
				String preview_text = item.getPreview_text();
				String detail_text = item.getDetail_text();
				Timestamp date_craete = item.getDate_create();
				int cat_id = item.getCategory().getId();
				String picture = item.getPicture();
				int counter = item.getCounter();
		%>
		<div class="article">

			<h2>
				<a
					href="<%=request.getContextPath() + "/detail" + "/" + StringUtil.makeSlug(item.getCategory().getName())
						+ "/" + StringUtil.makeSlug(item.getName()) + "-" + item.getId() + ".html"%>"
					title="<%=name%>"><%=name%></a>
			</h2>
			<p class="infopost">
				Ngày đăng:
				<%=date_craete%>. Lượt xem:
				<%=counter%>
				<a href="#" class="com"><span><%=i%></span></a>
			</p>
			<div class="clr"></div>
			<div class="img">
				<img src="<%=request.getContextPath()%>/uploads/<%=picture%>"
					width="177" height="213" alt="<%=name%>" class="fl" />
			</div>
			<div class="post_content">
				<p><%=preview_text%><%=detail_text%></p>
				<p class="spec">
					<a
						href="<%=request.getContextPath() + "/detail" + "/" + StringUtil.makeSlug(item.getCategory().getName())
						+ "/" + StringUtil.makeSlug(item.getName()) + "-" + item.getId() + ".html"%>"
						class="rm">Chi tiết &raquo;</a>
				</p>
			</div>
			<div class="clr"></div>
		</div>

		<%
			}}else{
		%>
		<div class="article">
		<p> Chưa có bài hát nào</p>
		</div>
		<%
			}
		%>
		<%
			int numberOfPages = (Integer) request.getAttribute("numberOfPages");
			int currentPage = (Integer) request.getAttribute("currentPage");
			int numberOfItems = (Integer) request.getAttribute("numberOfItems");
		%>
		<p class="pages">
			<small>Trang 1 của <%=numberOfPages%></small>
			<%
				for (int j = 1; j <= numberOfPages; j++) {
					if (j == currentPage) {
			%>

			<span><%=j%></span>
			<%
				} else {
			%>
			<a href="<%=request.getContextPath()%>/home/page-<%=j%>.html"><%=j%></a>

			<%
				}
				}
			%>
			<a href="<%=request.getContextPath()%>/home/page-<%if (currentPage < numberOfPages)out.print(currentPage + 1);else out.print(1);%>.html">&raquo;</a>
		</p>
	</div>
	<div class="sidebar">
		<%@ include file="/templates/public/inc/leftbar.jsp"%>
	</div>
	<div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp"%>
