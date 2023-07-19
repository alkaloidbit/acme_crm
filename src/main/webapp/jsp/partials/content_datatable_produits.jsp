<%@ page language="java" contentType="text/html; charset=UTF-8"
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
			</tr>
			</thead>
			<tbody>
			<c:forEach var="i" begin="0" end="30" step="1">
    			<tr><td>Code ${i}</td><td>Libéllé  ${i}</td><td>Description ${i}</td><td>Prix ${i}</td><td>Date ${i}</td></tr>
			</c:forEach>
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

