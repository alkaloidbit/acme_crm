<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.ArrayList, acme.front.ProduitBean, acme.util.Utilitaire, acme.back.metier.Produit, acme.front.AuthentificationBean"
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
				<% AuthentificationBean ab = (AuthentificationBean) session.getAttribute("authentification");%>
				<% ArrayList<ProduitBean> pbs = (ArrayList<ProduitBean>) request.getSession().getAttribute("produits");
				for (int i = 0; i < pbs.size(); i++) {%>
    			<tr>
    				<td><%= pbs.get(i).getCodeProduit() %></td>
					<td><%= pbs.get(i).getLibelleProduit() %></td>
					<td><%= pbs.get(i).getDescription() %></td>
					<td><%= Math.round(pbs.get(i).getPrix()*100.0)/100.0 %></td>
					<td><%= pbs.get(i).getStimestamp() %></td>
	    			<td>
	    				<% 	if(ab.hasPermissionToReadProduct()){%>
						<a class="btn btn-success me-2 detailsProduit" href="<%=request.getContextPath()%>/Produitinfo?index=<%=i%>">
							<i class="fas fa-search"></i>
						</a>
						<%}%>  
						<% 	if(ab.hasPermissionToUpdateProduct()){%>
						<a class="btn btn-info me-2 modifProduit"  href="<%=request.getContextPath()%>/Produitmodification?choice=update&index=<%=i%>">
							<i class="fas fa-pen"></i>
						</a>
						<%}%>  
						<% 	if(ab.hasPermissionToDeleteProduct()){%>
						<!-- <a class="btn btn-danger supprProduit" href="<%=request.getContextPath()%>/Produit/modification?choice=delete&index=<%=i%>"> -->
						<a class="btn btn-danger btn-delete supprProduit" href="<%=request.getContextPath()%>/Produits?index=<%=i%>">

							<i class="fas fa-trash"></i>
						</a>
						<%}%>  
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
    			<% 	if(ab.hasPermissionToCreateProduct()){%>
					<a class="btn btn-primary" role="button" href="<%=request.getContextPath()%>/Produitmodification?choice=create">Ajouter un produit</a>
				<%}%>  				
				</th>
			</tr>
			</tfoot>
		</table>
	</div>
	<!-- /.card-body -->
</div>
<div hidden="hidden" id="error_message">${errorMessage}</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script type="text/javascript">
  let errorMes = $("#error_message").text();
  console.log("errorMes : " + errorMes);

  if (errorMes != "") { 
    toastr["error"](errorMes, "Erreur");
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


<!-- /.card -->
 
