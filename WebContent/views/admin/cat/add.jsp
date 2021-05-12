<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm danh mục</h2>
				<%
					if (request.getParameter("msg") != null) {
						if ("empty".equals(request.getParameter("msg"))) {
							out.print("<h5>Tên danh mục không được để trống<h5>");
						}
						if ("1".equals(request.getParameter("msg"))) {
							out.print("<h5>Tên danh mục đã tồn tại<h5>");
						}
					}
				%>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<form role="form" method="post" id="form" action="">
									<div class="form-group">
										<label for="name">Tên danh mục</label> <input type="text"
											id="name" value="" name="name" class="form-control" />
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Thêm</button>
								</form>
								<script type="text/javascript">
									$(document).ready(function() {
										$('#form').validate({
																			rules : {
																				"name" : {
																					required : true,
																					minlength : 6,
																					maxlength : 32,
																				},
																				

																			},

																			messages : {
																				"name" : {
																					required : "Vui lòng nhập tên danh mục",
																					minlength : "Nhập tối thiểu 6 kí tự",
																					maxlength : "nhập tối đa 32 kí tự",
																				},
																				
																			},
																		});
													});
								</script>
							</div>
						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
<script>
	document.getElementById("cat").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>