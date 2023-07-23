<%@ page language="java" contentType="text/html; charset=UTF-8" import="acme.front.ProduitBean"	pageEncoding="UTF-8"%>

<div>
	<% String choice = (String) request.getSession().getAttribute("choice"); %>
	<% ProduitBean prodB = (ProduitBean) request.getSession().getAttribute("produitBean"); %>
	<h1>Modification d'un produit</h1>	
	<form action="/MSPR/Produit/modification" method="post">
		<div>
			<label for="code_produit">Code produit :</label>
			<p><%= prodB.getCodeProduit() %></p>
			<input type="hidden" id="code_produit" name="code_produit" value="<%= prodB.getCodeProduit() %>" />
		</div>
		<div>
			<label for="libelle_produit">Nom du produit :</label>
			<input type="text" id="libelle_produit" name="libelle_produit" value="<%= prodB.getLibelleProduit() %>" />
		</div>
		<div>
			<label for="description">Description :</label>
			<textarea id="description" name="description" ><%= prodB.getDescription() %></textarea>
		</div>
		<div>
			<label for="prix">Prix :</label>
			<input type="text" id="prix" name="prix" value="<%= prodB.getPrix() %>" />
		</div>
		<div>
			<input type="hidden" id="timestamp" name="timestamp" value="<%= prodB.getStimestamp() %>" />
		</div>
		<div>
			<input type="hidden" id="request_type" name="request_type" value="<%= choice %>" />
		</div>
		<div>
			<input type="submit" value="Modifier" />		
		</div>			
	</form>
</div> 
<p>${errorMessage}</p>
