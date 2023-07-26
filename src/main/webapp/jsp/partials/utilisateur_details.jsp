<%@ page contentType="text/html; charset=utf-8"
		 language="java"
	 	 import="java.util.ArrayList, acme.front.UtilisateurBean, acme.util.Utilitaire"
		 errorPage="" %>

<%
	ArrayList<UtilisateurBean> al = (ArrayList<UtilisateurBean>)request.getSession().getAttribute("utilisateurs");
	int taille = al.size();
%>
<div class="card">
	<div class="card-header">
		<h3 class="card-title">DataTable with default features</h3>
	</div>
	<!-- /.card-header -->
	<div class="card-body">
		<table id="example1" class="table table-bordered table-striped">
			<thead>
			<tr>
				<th>Login</th>
				<th>Mot de passe</th>
				<th>Role</th>
				<th>Date</th>
				<th>Actions</th>
			</tr>
			</thead>
			<tbody>
			<% for (int i = 0; i < taille; i++) {%>
				<tr>
					<td><%=((UtilisateurBean)al.get(i)).getLOGIN() %></td>
					<td><%=((UtilisateurBean)al.get(i)).getPSW()%></td>
					<td><%=((UtilisateurBean)al.get(i)).getLIBELLE_ROLE() %></td>
					<td><%=((UtilisateurBean)al.get(i)).getSTIMESTAMP_UTILISATEUR() %></td>
					<td>
						<a class="btn btn-success me-2" href="./Utilisateur?action=detail&valeur=<%=i%>">
							<i class="fas fa-search"></i>
						</a>
						<a class="btn btn-info me-2" href="#">
							<i class="fas fa-pen"></i>
						</a>
						<a class="btn btn-danger btn-delete" href="./Utilisateur?action=suppression&valeur=<%=i%>">
							<i class="fas fa-trash"></i>
						</a>
					</td>
				</tr>
			<%}%>
			</tbody>
			<!--
			<tfoot>
			<tr>
				<th>Rendering engine</th>
				<th>Browser</th>
				<th>Platform(s)</th>
				<th>Engine version</th>
				<th>CSS grade</th>
			</tr>
			</tfoot>
			-->
		</table>
	</div>
	<!-- /.card-body -->
</div>
<!-- /.card -->

