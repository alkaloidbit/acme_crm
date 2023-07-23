<%@ page contentType="text/html; charset=utf-8"
		 language="java"
	 	 import="java.util.ArrayList, acme.front.ClientBean, acme.util.Utilitaire"
		 errorPage="" %>

<%
	ArrayList<ClientBean> al = (ArrayList<ClientBean>)request.getSession().getAttribute("clients");
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
				<th>Code Client</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Adresse</th>
				<th>Code Postal</th>
				<th>Ville</th>
				<th>Actions</th>
			</tr>
			</thead>
			<tbody>
			<% for (int i = 0; i < taille; i++) {%>
				<tr>
					<td><%=((ClientBean)al.get(i)).getCodeClient()%></td>
					<td><%=((ClientBean)al.get(i)).getNom()%></td>
					<td><%=((ClientBean)al.get(i)).getPrenom()%></td>
					<td><%=((ClientBean)al.get(i)).getAdresse()%></td>
					<td><%=((ClientBean)al.get(i)).getCodePostal()%></td>
					<td><%=((ClientBean)al.get(i)).getVille()%></td>
					<td>
						<a class="btn btn-success me-2" href="./Client?action=detail&valeur=<%=i%>">
							<i class="fas fa-search"></i>
						</a>

						<a class="btn btn-info me-2" href="./Client?action=update&valeur=<%=i%>">
							<i class="fas fa-pen"></i>
						</a>
						<a class="btn btn-danger btn-delete" href="./Client?action=suppression&valeur=<%=i%>">
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

