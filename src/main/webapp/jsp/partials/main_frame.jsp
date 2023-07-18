<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Main content -->
<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<%
				 String page_content = (String)request.getAttribute("page_content");
				 String pagePath = "partials/" + page_content + ".jsp";
				%>
				<jsp:include page="<%=pagePath%>" ></jsp:include>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div><!-- /.container-fluid -->
</div>
<!-- /.content -->
