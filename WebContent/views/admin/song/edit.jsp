<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="model.Songs"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Sửa bài hát</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<%   
		
			if (request.getAttribute("song") != null) {
				Songs song = (Songs) request.getAttribute("song");
				String nameSong = song.getName();
				String catName = song.getCategory().getName();
				String preview_text = song.getPreview_text();
				String detail_text = song.getDetail_text();
				String picture = song.getPicture();
		%>
		<hr />
		
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<form role="form" method="post" enctype="multipart/form-data"
									id="form">
									<div class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											id="name" value="<%=nameSong%>" name="name"
											class="form-control" />
									</div>
									<div class="form-group">
										<label for="category">Danh mục bài hát</label> <select
											id="category" name="category" class="form-control">
											<%	
												if (request.getAttribute("categories") != null) {
													 @SuppressWarnings("unchecked")
														List<Category> songs = (List<Category>) request.getAttribute("categories");
														
														for (Category songitem : songs) {
														
															String name = songitem.getName();
											%>

											<option value="<%=songitem.getId()%>" <%if(song.getCategory().getId()==songitem.getId()) out.print("selected");%>><%=songitem.getName()%></option>
											<%
												}
													}
											%>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <input type="file"
											name="picture" />
										<%
											if (!picture.isEmpty()) {
										%>
										<br/>
										<img width="200px" height="200px" src="<%=request.getContextPath()%>/uploads/<%=picture%>"/>

										<%
											}
										%>

									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" class="form-control" rows="3"
											name="preview"><%=preview_text%></textarea>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea id="detail" class="form-control" id="detail"
											rows="5" name="detail"><%=detail_text%></textarea>
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Sửa</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<%
			}
		%>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
<script>
	document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>