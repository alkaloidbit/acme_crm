<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.ArrayList, acme.front.ProduitBean, acme.util.Utilitaire, acme.back.metier.Produit"
	pageEncoding="UTF-8"%>

<div class="card">
	<div class="card-header">
		<h3 class="card-title">Table Produits <span class="right badge badge-danger">${ errorMessage }</span></h3>
	</div>
	<!-- /.card-header -->
	<div class="card-body">
		<table id="example1" class="table table-bordered table-striped">
			<thead>
			<tr>
				<th>Code produit</th>
				<th>Libéllé produit</th>
				<th>Description</th>
				<th>Prix</th>
				<th>Date de création</th>
				<th>Actions</th>
			</tr>
			</thead>
			<tbody>
			<% 	ArrayList<ProduitBean> pbs = (ArrayList<ProduitBean>) request.getSession().getAttribute("produits");
				for (int i = 0; i < pbs.size(); i++) {%>
    			<tr>
    				<td><%= pbs.get(i).getCodeProduit() %></td>
					<td><%= pbs.get(i).getLibelleProduit() %></td>
					<td><%= pbs.get(i).getDescription() %></td>
					<td><%= Math.round(pbs.get(i).getPrix()*100.0)/100.0 %></td>
					<td><%= pbs.get(i).getStimestamp() %></td>
	    			<td>
						<a class="btn btn-success me-2 detailsProduit" href="/MSPR/Produit/info?index=<%=i%>">
							<i class="fas fa-search"></i>
						</a>
						<a class="btn btn-info me-2 modifProduit"  href="/MSPR/Produit/modification?choice=update&index=<%=i%>">
							<i class="fas fa-pen"></i>
						</a>
						<a class="btn btn-danger supprProduit" href="/MSPR/Produit/modification?choice=delete&index=<%=i%>">
							<i class="fas fa-trash"></i>
						</a>
					</td>
    			</tr>
			<%}%>
			</tbody>
			<tfoot>
			<tr>
				<th>Code produit</th>
				<th>Libéllé produit</th>
				<th>Description</th>
				<th>Prix</th>
				<th>Date de création</th>
    			<th>
					<a class="btn btn-info me-2 modifProduit"  href="/MSPR/Produit/modification?choice=create">
						<i class="fas fa-trash"></i>
					</a>
				</th>
			</tr>
			</tfoot>
		</table>
	</div>
	<!-- /.card-body -->
</div>

<!-- /.card -->
 
