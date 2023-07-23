<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
	 	 import="java.util.ArrayList, acme.front.DetailCommandeBean, acme.util.Utilitaire"
		 errorPage="" %>

<% 	
	ArrayList<DetailCommandeBean> al = (ArrayList<DetailCommandeBean>)request.getSession().getAttribute("cdbs");
	int taille = al.size();
%>
<div class="card">
	<div class="card-header">
		<h3 class="card-title">Détail Commandes</h3>
	</div>
	<!-- /.card-header -->
	<div class="card-body">
		<table id="example1" class="table table-bordered table-striped">
			<thead>
			<tr>
				<th>Code</th>
				<th>Libelle</th>
				<th>Quantité</th>
				<th>Montant</th>
			</tr>
			</thead>
			<tbody>
			<% for (int i = 0; i < taille; i++) {%>
				<tr>
					<td><%=((DetailCommandeBean)al.get(i)).getCodeProduit()%></td>
					<td><%=((DetailCommandeBean)al.get(i)).getLibelleProduit()%></td>
					<td><%=((DetailCommandeBean)al.get(i)).getQuantite()%></td>
					<td><%=Math.round(((DetailCommandeBean)al.get(i)).getMontant()*100.0)/100.0%></td>
				</tr>
			<%}%>
			</tbody>
		</table>
	</div>
	<!-- /.card-body -->
</div>
<!-- /.card -->

