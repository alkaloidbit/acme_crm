<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
	 	 import="java.util.ArrayList, acme.front.CommandeBean, acme.util.Utilitaire"
		 errorPage="" %>

<% 	
	ArrayList<CommandeBean> al = (ArrayList<CommandeBean>)request.getSession().getAttribute("commandes");
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
				<th>N° Commande</th>
				<th>Date</th>
				<th>Client</th>
				<th>Montant</th>
			</tr>
			</thead>
			<tbody>
			<% for (int i = 0; i < taille; i++) {%>
				<tr>
					<td><%=((CommandeBean)al.get(i)).getIdCommande()%></td>
					<td><%=Utilitaire.getDateEuropeenne(((CommandeBean)al.get(i)).getDateCommande())%></td>
					<td><%=((CommandeBean)al.get(i)).getNomClient()%></td>
					<td><%=Math.round(((CommandeBean)al.get(i)).getMontant()*100.0)/100.0%></td>
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

