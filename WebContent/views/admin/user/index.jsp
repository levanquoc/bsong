
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý người dùng</h2>
				<%
					if (request.getParameter("msg") != null) {
						if ("admin".equals(request.getParameter("msg"))) {
							out.print("<h5>Bạn không thể xóa admin<h5>");

						}
						if ("success".equals(request.getParameter("msg"))) {
							out.print("<h5 style='color:green'>Xử lí thành công</h5>");

						}
					}
					if (request.getParameter("err") != null) {
						String err = request.getParameter("err");
						if ("6".equals(err)) {
							out.print("Bạn không có quyền xóa");
						}
						if ("3".equals(err)) {
							out.print("Không có quyền thêm người dùng");
						}
						if ("4".equals(err)) {
							out.print("Không có quyền sửa người dùng");
						}
						if ("2".equals(err)) {
							out.print("ID không tồn tại");
						}
						if ("1".equals(err)) {
							out.print("Bạn không thể xóa admin");
						}
					}
				%>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<div class="col-sm-6">
									<a href="<%=request.getContextPath()%>/admin/user/add"
										class="btn btn-success btn-md">Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="get"
										action="<%=request.getContextPath()%>/admin/user/index">
										<input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" class="form-control input-sm"
											placeholder="Nhập tên người dùng" name="searchuser"
											value="<%if (request.getParameter("searchuser") != null)
				out.print(request.getParameter("searchuser"));%>"
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
										<th>Tên đăng nhập</th>
										<th>Họ tên</th>

										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<% 
									@SuppressWarnings("unchecked")
										ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
										if (users != null && users.size() > 0) {
											for (User user : users) {

												int id = user.getId();
												String username = user.getUsername();
												String fullname = user.getFullname();
												String urlEdit = request.getContextPath() + "/admin/user/edit?id=" + id;
												String urlDel = request.getContextPath() + "/admin/user/del?id=" + id;
									%>
									<tr>
										<td><%=id%></td>
										<td class="center"><%=username%></td>
										<td class="center"><%=fullname%></td>
										<%
											if (session.getAttribute("userLogin") != null) {

														User userLogin = (User) session.getAttribute("userLogin");

														if ("admin".equals(userLogin.getUsername())) {
															if(!"admin".equals(username)){
										%>
										<td class="center"><a href="<%=urlEdit%>" title=""
											class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
							 <a href="<%=urlDel%>" title="" class="btn btn-danger"
											onclick="return confirm('Bạn có chắc chắn xóa không ?');"><i
												class="fa fa-pencil"></i> Xóa</a></td>
									</tr>
									<%
										}else{%>
										<td class="center"><a href="<%=urlEdit%>" title=""
											class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
															
										<%	}}else if(userLogin.getUsername().equals(user.getUsername())){%>
											<td class="center"><a href="<%=urlEdit%>" title=""
											class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
											
										<%}
														
												}
											}
										}
									%>

								</tbody>
							</table>
							<div class="row">

								<%
									int numberOfPageUser = (Integer) request.getAttribute("numberOfPageUser");
									int currentPageUser = (Integer) request.getAttribute("currentPageUser");
									int numberOfItemUser = (Integer) request.getAttribute("numberOfItemUser");
								%>
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">
										Hiển thị từ 1 đến
										<%=numberOfPageUser%>
										của
										<%=numberOfItemUser%>
										truyện
									</div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
											<li class="paginate_button previous disabled"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a
												href="<%=request.getContextPath()%>/admin/user/index?page=<%=currentPageUser - 1%>">Trang
													trước</a></li>
											<%
												String active = "";
												for (int j = 1; j <= numberOfPageUser; j++) {
													if (currentPageUser == j) {
														active = "active";
													} else {
														active = "";
													}
											%>
											<li class="paginate_button <%=active%>"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/user/index?page=<%=j%>"><%=j%></a></li>
											<%
												}
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a
												href="<%=request.getContextPath()%>/admin/user/index?page=<%if (currentPageUser == numberOfPageUser)
				out.print(1);
			else
				out.print(currentPageUser + 1);%>">Trang
													tiếp</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>
<script>
	document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>