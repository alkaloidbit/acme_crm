<%@ page language="java" contentType="text/html; charset=UTF-8" import="acme.front.ProduitBean"	pageEncoding="UTF-8"%>


	<% String choice = (String) request.getSession().getAttribute("choice"); %>
	<% ProduitBean prodB = (ProduitBean) request.getSession().getAttribute("produitBean"); %>
<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">Modification d'un produit</h3>
	</div>

	<form action="<%=request.getContextPath()%>/Produit/modification" method="post">
		<div class="card-body">
			<div class="form-group">
				<label for="code_produit">Code produit :</label>
				<p class="form-control" ><%= prodB.getCodeProduit() %></p>
				<input type="hidden" id="code_produit" name="code_produit" value="<%= prodB.getCodeProduit() %>" class="form-control"  />
			</div>
			<div class="form-group">
				<label for="libelle_produit">Nom du produit :</label>
				<input type="text" id="libelle_produit" name="libelle_produit" value="<%= prodB.getLibelleProduit() %>" class="form-control"  />
			</div>
			<div class="form-group">
				<label for="description">Description :</label>
				<textarea id="description" name="description" class="form-control"  ><%= prodB.getDescription() %></textarea>
			</div>
			<div class="form-group">
				<label for="prix">Prix :</label>
				<input type="text" id="prix" name="prix" value="<%= prodB.getPrix() %>" />
			</div>
			<div class="form-group">
				<input type="hidden" id="timestamp" name="timestamp" value="<%= prodB.getStimestamp() %>" class="form-control"  />
			</div>
			<div class="form-group">
				<input type="hidden" id="request_type" name="request_type" value="<%= choice %>" />
			</div>
			<div class="form-group">
				<input type="submit" value="Modifier" class="btn btn-primary"  />		
			</div>
		</div>				
	</form>
</div> 
<p hidden id="error_message" >"${errorMessage}"</p>

<script type="text/javascript">
let errorMes = $("#error_message").innerText;
console.log("errorMes : " + errorMes)
if(errorMes != null){
	Command: toastr["error"](errorMes, "Error")
	toastr.options = {
	  "closeButton": false,
	  "debug": false,
	  "newestOnTop": false,
	  "progressBar": false,
	  "positionClass": "toast-top-center",
	  "preventDuplicates": false,
	  "onclick": null,
	  "showDuration": "300",
	  "hideDuration": "1000",
	  "timeOut": "5000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	}
}
</script>

