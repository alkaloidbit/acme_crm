<%@ page language="java" contentType="text/html; charset=UTF-8" import="acme.front.ProduitBean"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer un produit</title>
</head>
<body>
    <% if (request.getSession().getAttribute("choice").equals("update")) { %>
        <h1>Modification Produit</h1>
    <% } else if (request.getSession().getAttribute("choice").equals("delete")) { %>
        <h1>Suppression Produit</h1>
    <% } %>
	<% ProduitBean prodB = (ProduitBean) request.getSession().getAttribute("produitBean"); %>
    <p>Code produit : <%= prodB.getCodeProduit() %></p>
    <p>Libellé produit : <%= prodB.getLibelleProduit() %></p>
    <p>Description : <%= prodB.getDescription() %></p>
</body>
</html>