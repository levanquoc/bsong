<%@page import="java.util.List"%>
<%@page import="model.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm bài hát</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<%
			if (request.getAttribute("cat") != null) {
				@SuppressWarnings("unchecked")
				List<Category> categories = (List<Category>) request.getAttribute("cat");
				String name = request.getParameter("name");
				String preview = request.getParameter("preview");
				String detail = request.getParameter("detail");
				String cat_id = request.getParameter("category");

				String err = request.getParameter("error");
				if ("1".equals(err)) {
					out.print("<h5 style='color:red'>Các trường không được để trống</h5>");
				}
				if ("2".equals(err)) {
					out.print("<span>Có lỗi khi thêm</span>");
				}
		%>

		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<form action="" role="form" method="post"
									enctype="multipart/form-data" id="form">
									<div class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											id="name" value="<%if(name!=null) out.print(name); %>" name="name" class="form-control" />
									</div>
									<div class="form-group">
										<label for="category">Danh mục bài hát</label> <select
											id="category" name="category" class="form-control">
											<% 
											   
												for (Category cat : categories) {
											%>
											<option <%if(cat_id !=null && cat_id.equals(String.valueOf(cat.getId())))out.print("selected"); %> value="<%=cat.getId()%>"><%=cat.getName()%></option>
											<%
												}
												
											%>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <input type="file"
											name="picture" />
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" class="form-control" rows="3"
											name="preview"><%if(preview!=null) out.print(preview); %></textarea>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea id="detail" class="form-control" id="detail"
											rows="5" name="detail"><% if(detail!=null) out.print(detail);%></textarea>
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Thêm</button>
								</form>
							</div>
						</div>
					</div>
					<%
			}
					%>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
<script>
	document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>