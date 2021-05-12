<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<div class="content_resize">
	<div class="mainbar">
		<div class="article">
			<%
				Category category = (Category) request.getAttribute("category");
				if (category != null) {
			%>
			<h1><%=category.getName()%></h1>
			<%
				}
			%>
		</div>
		<%
				@SuppressWarnings("unchecked")
		
				ArrayList<Songs> songnew = (ArrayList<Songs>) request.getAttribute("song");
				int i=0;
				if (songnew != null && songnew.size() > 0) {
					for (Songs songItem : songnew) {
						i++;
						String urldetail = request.getContextPath() +"/detail"+ "/" + StringUtil.makeSlug(songItem.getCategory().getName()) + "/"
								+StringUtil.makeSlug(songItem.getName()) + "-" + songItem.getId() + ".html";
			%>
		<div class="article">


			
			<h2>
				<a href="<%=urldetail%>" title="<%=songItem.getName() %>"><%=songItem.getName() %></a>
			</h2>
			<p class="infopost">
				Ngày đăng: <%=songItem.getDate_create() %>. Lượt xem: <%=songItem.getCounter() %> <a href="#"
					class="com"><span><%=i %></span></a>
			</p>
			<div class="clr"></div>
			<div class="img">
				<img
					src="<%=request.getContextPath()%>/uploads/<%=songItem.getPicture() %>"
					width="177" height="213" alt="Đổi thay" class="fl" />
			</div>
			<div class="post_content">
				<p><%=songItem.getPreview_text() %></p>
				<p class="spec">
					<a href="<%=urldetail %>" class="rm">Chi tiết &raquo;</a>
				</p>
			</div>
			<div class="clr"></div>
		</div>
		<%
			}
			}else{
				
			
		%>
		Chưa có bài hát nào
		
		<%
			}
		%>
		<%
		  int numberOfItemDetail= (Integer)request.getAttribute("numberOfItemDetail" );
		 int numberOfPageDetail= (Integer)request.getAttribute("numberOfPageDetail" );
		 int currentPageDetail= (Integer)request.getAttribute("currentPageDetail" );
		 int idDetail= (Integer)request.getAttribute("iddetail" );
		%>
		<p class="pages">
		
			<small>Trang 1 của <%=numberOfPageDetail %></small> 
			<%
				for(int k=1;k<=numberOfPageDetail;k++){
					if(k==currentPageDetail){							
			%>
			<span><%=k %></span> 
			<% 
				}else{
			%>
			<a href="<%=request.getContextPath()%>/category/<%=StringUtil.makeSlug(category.getName())%>-<%=category.getId()%>.<%=k%>.html"><%=k %></a>
			<%
				}
				}
			%>
			 <a href="<%=request.getContextPath()%>/category/<%=StringUtil.makeSlug(category.getName())%>-<%=category.getId()%>.<%if(currentPageDetail==numberOfPageDetail) out.print(1);else out.print(currentPageDetail+1); %>.html">&raquo;</a>
		</p>
	</div>
	<div class="sidebar">
		<%@ include file="/templates/public/inc/leftbar.jsp"%>
	</div>
	<div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp"%>