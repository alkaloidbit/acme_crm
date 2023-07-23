<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Main content -->
<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<%
				 String page_content = (String)request.getAttribute("page_content");
				 String msg = (String)request.getAttribute("msg");
				%>
				<div class="alert alert-warning">${msg}</div>
				<jsp:include page='<%=page_content+".jsp"%>' />
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div><!-- /.container-fluid -->
</div>
<!-- /.content -->
