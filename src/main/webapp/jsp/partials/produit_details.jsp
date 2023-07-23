<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.ArrayList, acme.front.ProduitBean, acme.util.Utilitaire"
	errorPage=""%>
    
<%
ProduitBean pb = (ProduitBean) request.getSession().getAttribute("produitBean");
%>
<div class="card">
	<div class="card-header">
		<h3 class="card-title">Détails du produit</h3>
	</div>
	<!-- /.card-header -->
	<div class="card-body">
		<div class="form-horizontal">
			<div class="row">
				<label class="control-label col-lg-3">Code produit</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%= pb.getCodeProduit() %></p>
				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Libellé</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%= pb.getLibelleProduit() %></p>
				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Prix</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%= pb.getPrix() %></p>
				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Description</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%= pb.getDescription() %></p>
				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Date de la dernière modification</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%= pb.getStimestamp() %></p>
				</div>
			</div>
		</div>
	</div>
</div>