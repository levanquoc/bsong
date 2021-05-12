<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	String err=request.getParameter("err");
        	if("1".equals(err)){
        		out.print("Có lỗi khi thêm");
        	}
        	if("2".equals(err)){
        		out.print("Lỗi trùng tên đăng nhập");
        	}
        	User user =(User)request.getAttribute("user");
			if(user!=null){
				String username=user.getUsername();
				String fullname=user.getFullname();
			
        	
        %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form action=""role="form" method="post"  id="form">
                                    <div class="form-group">
                                        <label for="username">Tên đăng nhập</label>
                                        <input type="text" id="username" value="<%if(username!=null) out.print(username);%>" name="username" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="password">Mật khẩu</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="fullname">Họ tên</label>
                                        <input type="text" id="fullname" value="<%if(fullname!=null) out.print(fullname);%>" name="fullname" class="form-control" />
                                    </div>
                                    <%
			}
                                    %>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>