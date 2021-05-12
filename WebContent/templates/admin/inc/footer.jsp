<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
</div>
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY -->
<script
	src="<%=request.getContextPath()%>/resources/admin/assets/js/jquery-3.2.1.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script
	src="<%=request.getContextPath()%>/resources/admin/assets/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script
	src="<%=request.getContextPath()%>/resources/admin/assets/js/jquery.metisMenu.js"></script>
<!-- CUSTOM SCRIPTS -->
<script
	src="<%=request.getContextPath()%>/resources/admin/assets/js/custom.js"></script>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#form_user').validate({

			rules : {
				"username" : {
					required : true,
					minlength : 6,
					maxlength : 32,
				},
				"password" : {
					required : true,

				},
				"fullname" : {
					required : true,
				},

			},

			messages : {
				"username" : {
					required : "Vui lòng nhập tên đăng nhập",
					minlength : "Nhập tối thiểu 6 kí tự",
					maxlength : "nhập tối đa 32 kí tự",
				},
				"password" : {
					required : "Vui lòng nhập mật khẩu",

				},
				"fullname" : {
					required : "Vui lòng nhập họ tên",

				},
			},
		});
	});
</script>
</html>