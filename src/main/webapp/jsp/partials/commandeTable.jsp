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
		<h3 class="card-title">Commandes</h3>
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
				<th></th>
			</tr>
			</thead>
			<tbody>
			<% for (int i = 0; i < taille; i++) {%>
				<tr>
					<td><%=((CommandeBean)al.get(i)).getIdCommande()%></td>
					<td><%=Utilitaire.getDateEuropeenne(((CommandeBean)al.get(i)).getDateCommande())%></td>
					<td><%=((CommandeBean)al.get(i)).getNomClient()%></td>
					<td><%=Math.round(((CommandeBean)al.get(i)).getMontant()*100.0)/100.0%></td>
					<td>
						<a class="btn btn-success me-2" href="#">
							<i class="fas fa-search"></i>
						</a>
						<a class="btn btn-info me-2" href="#">
							<i class="fas fa-pen"></i>
						</a>
						<a class="btn btn-danger" href="/MSPR/Commande?parametre=supprimer&valeur=<%=i%>">
							<i class="fas fa-trash"></i>
						</a>
					</td>
				</tr>
			<%}%>
			</tbody>
		</table>
	</div>
	<!-- /.card-body -->
</div>
<!-- /.card -->

