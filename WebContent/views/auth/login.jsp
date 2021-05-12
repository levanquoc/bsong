<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%
	if(session.getAttribute("userLogin")!=null){ %>
		<%@ include file="/templates/admin/inc/leftbar.jsp" %>
	
  
<%
	}
%>

<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
             
        
            <div class="col-md-12">
                <h2>Đăng nhập</h2>
                
                
        <% 
          String msg=request.getParameter("msg");
          if("err".equals(msg)){
        	  out.print("Tên đăng nhập hoặc mật khẩu không đúng");
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
                                        <label for="name">Tên đăng nhập</label>
                                        <input type="text" id="username" value="" name="username" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="name">Mật khẩu</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Đăng nhập</button>
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
    document.getElementById("cat").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>