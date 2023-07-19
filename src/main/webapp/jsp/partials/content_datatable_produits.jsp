<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.ArrayList, acme.front.ProduitBean, acme.util.Utilitaire"
	pageEncoding="UTF-8"%>
<div class="card">
	<div class="card-header">
		<h3 class="card-title">Table Produits</h3>
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
			<% 	
				ArrayList<ProduitBean> al = (ArrayList<ProduitBean>)request.getSession().getAttribute("produits");
				for (int i = 0; i < al.size(); i++) {%>
    			<tr>
    				<td><%=((ProduitBean)al.get(i)).getCodeProduit()%></td>
					<td><%=((ProduitBean)al.get(i)).getLibelleProduit()%></td>
					<td><%=((ProduitBean)al.get(i)).getDescription()%></td>
					<td><%=Math.round(((ProduitBean)al.get(i)).getPrix()*100.0)/100.0%></td>
					<td><%=((ProduitBean)al.get(i)).getStimestamp()%></td>
	    			<td>
						<a class="btn btn-success me-2" href="#">
							<i class="fas fa-search"></i>
						</a>
						<a class="btn btn-info me-2" href="#">
							<i class="fas fa-pen"></i>
						</a>
						<a class="btn btn-danger" href="#">
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
			</tr>
			</tfoot>
		</table>
	</div>
	<!-- /.card-body -->
</div>
<!-- /.card -->

