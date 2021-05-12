﻿<%@page import="model.Category"%>
<%@page import="daos.CategoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daos.UserDao"%>
<%@page import="java.util.List"%>
<%@page import="model.Songs"%>
<%@page import="daos.SongsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>TRANG QUẢN TRỊ VIÊN</h2>
			</div>
		</div>
		<!-- /. ROW  -->



		<%
			SongsDao songDao = new SongsDao();
			UserDao userDao = new UserDao();
			CategoryDAO catDao=new CategoryDAO();
			List<Songs> songs = songDao.getAll();
			ArrayList<User> users = userDao.getItems();
		    List<Category>    cat=   catDao.getCategories();
		%>
		<hr />
		<div class="row">
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div class="panel panel-back noti-box">
					<span class="icon-box bg-color-green set-icon"> <i
						class="fa fa-bars"></i>
					</span>
					<div class="text-box">
						<p class="main-text">
							<a href="<%=request.getContextPath()%>/admin/cat/index" title="">Quản lý danh mục</a>
						</p>
						<p class="text-muted">Có <%=cat.size() %> danh mục</p>
					</div>
				</div>
			</div>
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div class="panel panel-back noti-box">
					<span class="icon-box bg-color-blue set-icon"> <i
						class="fa fa-bell-o"></i>
					</span>
					<div class="text-box">
						<p class="main-text">
							<a href="<%=request.getContextPath()%>/admin/song/index" title="">Quản lý bài hát</a>
						</p>
						<p class="text-muted">
							Có
							<%=songs.size()%>
							bài hát
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div class="panel panel-back noti-box">
					<span class="icon-box bg-color-brown set-icon"> <i
						class="fa fa-rocket"></i>
					</span>
					<div class="text-box">
						<p class="main-text">
							<a href="<%=request.getContextPath()%>/admin/user/index" title="">Quản lý người dùng</a>
						</p>
						<p class="text-muted">
							Có
							<%=users.size()%>
							người dùng
						</p>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<script>
	document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>