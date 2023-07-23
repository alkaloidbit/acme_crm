<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Main content -->
<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<%
				 String pagePath = "partials/" + (String)request.getAttribute("page_content") + ".jsp";
				%>
				<jsp:include page="<%=pagePath%>" ></jsp:include>
			</div>
			<!-- /.col -->
		</div>
		<div class="row">
			<div class="col-12">
				<%
					String pageButton = (String)request.getAttribute("page_button");
					String buttonPath = null;
					if (pageButton != null) {
						buttonPath = "partials/" + (String)request.getAttribute("page_button") + ".jsp";
					}
				%>
				<% 
					if (buttonPath != null) {%> 
						<jsp:include page="<%=buttonPath%>" ></jsp:include>
					<%}%>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div><!-- /.container-fluid -->
</div>
<!-- /.content -->
