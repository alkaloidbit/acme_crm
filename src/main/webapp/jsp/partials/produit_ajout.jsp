
<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.ArrayList, acme.front.ProduitBean, acme.util.Utilitaire"
	errorPage=""%>

<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">Ajout d'un Produit</h3>
	</div>
		<form action="<%=request.getContextPath()%>/Produit/modification" method="post">
			<div class="card-body">
				<div class="form-group">
					<label for="code_produit">Code produit :</label>
					<input type="text" id="code_produit" name="code_produit" class="form-control" />
				</div>
				<div class="form-group">
					<label for="libelle_produit">Nom du produit :</label>
					<input type="text" id="libelle_produit" name="libelle_produit" class="form-control"  />
				</div>
				<div>
					<label for="description">Description :</label>
					<textarea style="resize: none;" id="description" name="description" class="form-control"  ></textarea>
				</div>
				<div class="form-group">
					<label for="prix">Prix :</label>
					<input type="text" id="prix" name="prix" class="form-control" />
				</div>
				<div class="form-group">
					<input type="hidden" id="timestamp" name="timestamp" />
				</div>
				<div class="form-group">
					<% String choice = (String) request.getSession().getAttribute("choice"); %>
					<input type="hidden" id="request_type" name="request_type" value="<%= choice %>" />
				</div>
				<div class="card-footer">
					<input type="submit"  class="btn btn-primary" value="Ajouter" />	
				</div>		
			</div>
		</form>
</div>
<div hidden="hidden" id="error_message">${errorMessage}</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script type="text/javascript">
  let errorMes = $("#error_message").text();
  console.log("errorMes : " + errorMes);

  if (errorMes != "") { 
    toastr["error"](errorMes, "Error");
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
    };
  }
</script>



