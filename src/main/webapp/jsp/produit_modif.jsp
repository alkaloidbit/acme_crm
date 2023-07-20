<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer un produit</title>
</head>
<body>
    <% if (request.getAttribute("choix").equals("update")) { %>
        <h1>Modification Produit</h1>
    <% } else if (request.getAttribute("choix").equals("delete")) { %>
        <h1>Suppression Produit</h1>
    <% } %>

    <p>Code produit : <%= request.getAttribute("codeProduit") %></p>
    <p>Libellé produit : <%= request.getAttribute("libelleProduit") %></p>
    <p>Description : <%= request.getAttribute("description") %></p>
</body>
</html>