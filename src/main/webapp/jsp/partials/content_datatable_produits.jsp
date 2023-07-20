<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.ArrayList, acme.front.ProduitBean, acme.util.Utilitaire, acme.back.metier.Produit"
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
			<% 	ProduitBean pb = (ProduitBean) request.getSession().getAttribute("produits");
				ArrayList<Produit> al = pb.getProds();
				for (int i = 0; i < al.size(); i++) {%>
    			<tr>
    				<td><%=al.get(i).getCodeProduit()%></td>
					<td><%=al.get(i).getLibelleProduit()%></td>
					<td><%=al.get(i).getDescription()%></td>
					<td><%=Math.round(al.get(i).getPrix()*100.0)/100.0%></td>
					<td><%=al.get(i).getStimestamp()%></td>
	    			<td>
						<a class="btn btn-success me-2 detailsProduit" >
							<i class="fas fa-search"></i>
						</a>
						<a class="btn btn-info me-2 modifProduit" >
							<i class="fas fa-pen"></i>
						</a>
						<a class="btn btn-danger supprProduit" >
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
<script>

	  // Envoyer les valeurs de l'objet sélectionné à la servlet de modif via AJAX 
	function sendSelectedProductToServletModif(event, choice) {
		var parentRow = event.target.closest('tr');
		var codeProduit = parentRow.cells[0].innerText;
		var libelleProduit = parentRow.cells[1].innerText;
		var description = parentRow.cells[2].innerText;
		console.log(description);
		$.ajax({
		   type: "POST",
		   url: "/MSPR/ProduitModif",
		   data: {
		   	codeProduit: codeProduit,
		   	libelleProduit: libelleProduit,
		   	description: description,
		   	choix:choice
		   },
		   success: function(response) {
		     // Traitement en cas de succès de la requête
		     console.log("Données envoyées avec succès !");
		   },
		   error: function(xhr, status, error) {
		     // Traitement en cas d'erreur
		     console.error("Erreur lors de l'envoi des données : " + status);
		   }
		  });
		}
	  // Envoyer les valeurs de l'objet sélectionné à la servlet de détails via AJAX 
	function sendSelectedProductToServletDetail(event) {
		var codeProduit = event.target.closest('tr').cells[0].innerText;
		$.ajax({
		   type: "POST",
		   url: "/MSPR/ProduitInfo",
		   data: {
		   	codeProduit: codeProduit,
		   },
		   success: function(response) {
		     // Traitement en cas de succès de la requête
		     console.log("Données envoyées avec succès !");
		   },
		   error: function(xhr, status, error) {
		     // Traitement en cas d'erreur
		     console.error("Erreur lors de l'envoi des données : " + status);
		   }
		  });
		}

	// Sélectionner tous les boutons de modification
	var modifButtons = document.querySelectorAll('.modifProduit');
	
	// Ajout du gestionnaire d'évenement associé
	modifButtons.forEach(function(modifButton) {
		modifButton.addEventListener('click', function(event) {
			sendSelectedProductToServletModif(event, "update");
			//window.location.href = "/MSPR/Produit/modification";
	  });
	});
	
	// Sélectionner tous les boutons de suppression
	var supprButtons = document.querySelectorAll('.supprProduit');
	
	// Ajout du gestionnaire d'évenement associé
	supprButtons.forEach(function(supprButton) {
		supprButton.addEventListener('click', function(event) {
			sendSelectedProductToServletModif(event, "delete");
			//window.location.href = "/MSPR/Produit/modification";
	  });
	});
	
	// Sélectionner tous les boutons infos
	var infoButtons = document.querySelectorAll('.detailsProduit');
	
	// Ajout du gestionnaire d'évenement associé
	infoButtons.forEach(function(infoButton) {
		infoButton.addEventListener('click', function(event) {
			sendSelectedProductToServletDetail(event);
			//window.location.href = "/MSPR/Produit/info";
	  });
	});

</script>
<!-- /.card -->

