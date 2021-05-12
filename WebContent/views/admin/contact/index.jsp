<%@page import="model.Contact"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý liên hệ</h2>
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
            
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath()%>/admin/contact/index">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" name="searchcontact" placeholder="Nhập tên liên hệ" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                              
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên liên hệ</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Message</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                  <%
                                if(request.getAttribute("contact")!=null){
                                	@SuppressWarnings("unchecked")
                                	List<Contact> contact=(List<Contact>)(request.getAttribute("contact"));
                                	for(Contact contactItem:contact){
                                		int id=contactItem.getId();
                                		String name=contactItem.getName();
                                		String email=contactItem.getEmail();
                                		String website=contactItem.getWebsite();
                                		String message=contactItem.getMessage();
                                		String urlDel=request.getContextPath()+"/admin/contact/del?id="+id;
                                	
                                
                                %>
                                <tbody>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center"><%=email %></td>
                                        <td class="center"><%=website %></td>
                                        <td class="center"><%=message %></td>
                                        <td class="center">
                                         
                                            <a href="<%=urlDel %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn xóa không ?');"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
 
                                </tbody>
                                <% 
                                 	}
                                }
                                %>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                           			 int  numberOfItemContact=(Integer)request.getAttribute("numberOfItemContact");
                                	 int  numberOfPageContact=(Integer)request.getAttribute("numberOfPageContact");
                                	 int  currentPageContact=(Integer)request.getAttribute("currentPageContact");                        		                              
                                %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=numberOfPageContact %> của <%=numberOfItemContact %> truyện</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=currentPageContact-1%>">Trang trước</a></li>
                                            <%  
                                                String active="";
                                            	for(int i=1;i<=numberOfPageContact;i++){
                                            		if(i==currentPageContact){
                                            			active="active";
                                            		}else{
                                            			active="";
                                            		}
                                            	
                                            %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=i%>"><%=i%></a></li>
											<%
                                            	}
											%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%if(currentPageContact==numberOfPageContact) out.print(1);else out.print(currentPageContact+1);%>">Trang tiếp</a></li>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>