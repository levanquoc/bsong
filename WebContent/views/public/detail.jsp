<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<div class="content_resize">
	<div class="mainbar">
		<div class="article">
			<%
				Songs songdetail = (Songs) request.getAttribute("songdetail");
			%>

			<h1><%=songdetail.getName()%></h1>
			<div class="clr"></div>
			<p>
				Ngày đăng:
				<%=songdetail.getDate_create()%>. Lượt xem:
				<%=songdetail.getCounter()%></p>
			<div class="vnecontent"><%=songdetail.getDetail_text()%></div>
		</div>
		<div class="article">

			<h2>Bài viết liên quan</h2>
			<div class="clr"></div>
			<%
				@SuppressWarnings("unchecked")
				ArrayList<Songs> songRelation = (ArrayList<Songs>) request.getAttribute("songRelation");
				if (songRelation !=null && songRelation.size()>0){
				for (Songs song : songRelation) {
			%>
			<div class="comment">
				<a href=""><img
					src="<%=request.getContextPath()%>/uploads/<%=song.getPicture() %>"
					width="40" height="40" alt="" class="userpic" /></a>
				<h2>
					<a href="<%=request.getContextPath() +"/detail"+ "/" + StringUtil.makeSlug(song.getCategory().getName()) + "/"
								+StringUtil.makeSlug(song.getName()) + "-" + song.getId() + ".html"%>"><%=song.getName() %></a>
				</h2>
				<p><%=song.getPreview_text() %></p>
			</div>
			<%
				}
				}else{
				%>
					không có bài hát liên quan
			<%	}
			%>
		</div>
	</div>
	<div class="sidebar">
		<%@ include file="/templates/public/inc/leftbar.jsp"%>
	</div>
	<div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp"%>
