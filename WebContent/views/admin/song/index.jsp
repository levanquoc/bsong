<%@page import="java.util.List"%>
<%@page import="model.Songs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý bài hát</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<%
		String msg=request.getParameter("msg");
		if("success".equals(msg)){
			out.print("<h5 style='color:green'>Xử lí thành công</h5>");
		}
		
		%>
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<div class="col-sm-6">
									<a href="<%=request.getContextPath()%>/admin/song/add"
										class="btn btn-success btn-md">Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="get" action="<%=request.getContextPath()%>/admin/song/index">
										<input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search"value="<%if(request.getParameter("searchname")!=null) out.print(request.getParameter("searchname"));%>" name="searchname"class="form-control input-sm"
											placeholder="Nhập tên bài hát"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên bài hát</th>
										<th>Danh mục</th>
										<th>Lượt đọc</th>
										<th>Hình ảnh</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										@SuppressWarnings("unchecked")
										List<Songs> songs = (List<Songs>) request.getAttribute("songs");

										if (songs.size() > 0) {
											for (Songs song : songs) {
												int id = song.getId();
												String nameSong = song.getName();
												String nameCat = song.getCategory().getName();
												String picture = song.getPicture();
												int count = song.getCounter();
												String urlEdit = request.getContextPath() + "/admin/song/edit?id=" + id;
												String urlDel = request.getContextPath() + "/admin/song/del?id=" + id;
									%>
									<tr>
										<td><%=id%></td>
										<td class="center"><%=nameSong%></td>
										<td class="center"><%=nameCat%></td>
										<td class="center"><%=count%></td>
										<td class="center">
											<%
												if (!"".equals(picture)) {
											%> <img width="200px"
											height="200px"
											src="<%=request.getContextPath()%>/uploads/<%=picture%>"
											alt="<%=nameSong %>" /> <%
 	} else {
 %> không có hình <%
 	}
 %>

										</td>
										<td class="center"><a href="<%=urlEdit%>" title=""
											class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a> <a
											href="<%=urlDel%>" title="" class="btn btn-danger"
											onclick="return confirm('bạn có chắc muốn xóa?')"""><i
												class="fa fa-pencil"></i> Xóa</a></td>
									</tr>
									<%
										}
										}
									%>
								</tbody>
							</table>
							<%
								int numberOfPages = (Integer) request.getAttribute("numberOfPages");
								int currentPage = (Integer) request.getAttribute("currentPage");
								int numberOfItems = (Integer) request.getAttribute("numberOfItems");
								if (songs != null && songs.size() > 0) {
							%>
							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị từ 1 đến <%=numberOfPages %> của <%=numberOfItems %>
										bài hát</div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
										<li class="paginate_button previous disabled"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a href="<%=request.getContextPath() %>/admin/song/index?page=<%=currentPage-1%>">Trang
													trước</a></li>
											
											<%
												String active="";
												for(int i=1;i<=numberOfPages;i++){
													if(i==currentPage){
														active="active";
													}else{
														active="";
													}
											%>
											
											<li class="paginate_button <%=active %>"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath() %>/admin/song/index?page=<%=i%>"><%=i %></a></li>
												<%
													}
												%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a href="<%=request.getContextPath() %>/admin/song/index?page=<%=currentPage+1%>">Trang tiếp</a></li>
												
										</ul>
									</div>
								</div>
							</div>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>
<script>
	document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>