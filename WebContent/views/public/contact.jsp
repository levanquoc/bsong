<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<div class="content_resize">
	<div class="mainbar">
		<div class="article">
			<h2>
				<span>Liên hệ</span>
			</h2>
			<div class="clr"></div>
			<p>Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản
				hồi trong thời gian sớm nhất.</p>
		</div>
		<div class="article">
			<h2>Gửi liên hệ đến chúng tôi</h2>
			<%
				String msg = request.getParameter("msg");
				if ("succsess".equals(msg)) {
			%>
			<h4 class="SUCCESS">Gửi liên hệ thành công!</h4>
			<%
				}
			%>
			<div class="clr"></div>
			<form action="" method="post" id="sendemail">
				<ol>
					<li><label for="name" class="">Họ tên (required)</label> <input
						id="name" value="" name="name" class="text" /></li>
					<li><label for="email" >Email (required)</label> <input
						id="email" value="" name="email" class="text" /></li>
					<li><label for="website" >Website</label> <input id="website"
						value="" name="website" class="text" /></li>
					<li><label for="message" >Nội dung</label> <textarea
							id="message" name="message" rows="8" cols="50"></textarea></li>
					<li><input type="image" name="imageField" id="imageField"
						src="<%=request.getContextPath()%>/resources/public/images/submit.gif"
						class="send" />
						<div class="clr"></div></li>
				</ol>
			</form>
			<script type="text/javascript">
				$(document).ready(function() {
					$('#sendemail').validate({

						rules : {
							"name" : {
								required : true,
								minlength : 6,
								maxlength : 32,
							},
							"email" : {
								required : true,
								email : true,
							},
							"website" : {
								required : true,
								
							},
							"message" : {
								required : true,
								
							},
							
						},

						messages : {
							"name" : {
								required :"Vui lòng nhập Tên đăng nhập",
								minlength : "Nhập tối thiểu 6 kí tự",
								maxlength : "nhập tối đa 32 kí tự",
							},
							"email" : {
								required : "Vui lòng nhập email",
								email: "Vui lòng nhập đúng định dạng"
							},
							"website" : {
								required : "Vui lòng nhập website",
								
							},
							"message" : {
								required : "Vui lòng nhập message",
							
							},
						},
					});
				});
			</script>


		</div>
	</div>
	<div class="sidebar">
		<%@ include file="/templates/public/inc/leftbar.jsp"%>
	</div>
	<div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp"%>
