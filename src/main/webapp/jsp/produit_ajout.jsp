<%@ page language="java" contentType="text/html; charset=UTF-8" import="acme.front.ProduitBean"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Produit</title>
</head>
<body>
<div>
	<h1>Ajout d'un Produit</h1>
	
	<form action="/MSPR/Produit/modification" method="post">
		<div>
			<label for="code_produit">Code produit :</label>
			<input type="text" id="code_produit" name="code_produit" />
		</div>
		<div>
			<label for="libelle_produit">Nom du produit :</label>
			<input type="text" id="libelle_produit" name="libelle_produit" />
		</div>
		<div>
			<label for="description">Description :</label>
			<textarea id="description" name="description" ></textarea>
		</div>
		<div>
			<label for="prix">Prix :</label>
			<input type="text" id="prix" name="prix" />
		</div>
		<div>
			<input type="hidden" id="timestamp" name="timestamp" />
		</div>
		<div>
			<% String choice = (String) request.getSession().getAttribute("choice"); %>
			<input type="hidden" id="request_type" name="request_type" value="<%= choice %>" />
		</div>
		<div>
			<input type="submit" value="Ajouter" />	
		</div>			
	</form>

</div> 
</body>
</html>