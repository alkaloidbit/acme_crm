<%@ page contentType="text/html; charset=utf-8"
		 language="java"
	 	 import="java.util.ArrayList, acme.front.ClientBean, acme.util.Utilitaire"
		 errorPage="" %>
<%
	ClientBean cl = request.getSession().getAttribute("cbdetail")
%>
<div class="card">
	<div class="card-header">
		<h3 class="card-title">DataTable with default features</h3>
	</div>
	<!-- /.card-header -->
	<div class="card-body">
		<h1>Salut</h1>
		<h2><%=cl.getNom()%></h2>
	</div>
</div>
