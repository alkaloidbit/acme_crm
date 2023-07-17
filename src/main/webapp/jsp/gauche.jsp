<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
		 import="java.util.HashMap"
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Menu</title>
<style type="text/css">
<!--
body,td,th {
	font-family: Tahoma;
	font-size: 11pt;
	color: #336600;
}
body {
	background-image: url(image/menu.gif);
	background-repeat: no-repeat;
}
/**
 * Version
 */
div#version {
	font-family: Tahoma;
	font-size: 7pt;
	color: #336600;
	height:17px;
	position:absolute;
	left: 15px;
	top: 743px;
	width: 143px;
}

a{text-decoration: none;}
a.active{color: #339900;}
a.order {color: #339900;}

/**
 *NAVIGATION
 */ 
#navi{
  float: left;
  clear: left;
  border-right: none;
  width: 146px;
  padding-top: 0px;
  height: 356px;
}
#navi li a{
  display: block;
  height: 10px;
  padding-top: 10px;
  text-transform: none;
}
#navi li a.active{ background: none; }
#navi li a.down{
  background: none;
  padding-left: 0px;
  height: 5px
}
#navi li a:hover{
  color: #339900;
}
#navi li:hover ul{
  display: block;
}

/* 2te Ebene */
#navi li ul{
  margin-left: -20px;
  margin-bottom: 5px;
  margin-top: 10px;
  display: none;
}
#navi li.active ul{
  display: block;
  margin-top: 10px;
}
#navi li ul a{
  padding-top: 1px;
  height: auto;
}
#navi li ul a.active{background-position: 0px 6px;}
-->
</style>
<%
	String msg = (String)request.getSession().getAttribute("erreur");
	HashMap mb = (HashMap)request.getSession().getAttribute("mb");
%>

<script language="javascript">
	function erreur() {	if ("<%=msg%>" != "null" && "<%=msg%>" != "") { alert("<%=msg%>"); }}
</script>
</head>
<body link="#336600" vlink="#336600" alink="#339900">
<table width="200" border="0">
  <tr><td>&nbsp;</td></tr>
  <tr><td>&nbsp;</td></tr>
  <tr><td align="center"><img src="image/logo.gif"/></td></tr>
</table>  
	<ul id="navi"> 
		<li class=""><a href="../Commande?parametre=menuCommande&parent=null" title="Commande" target="mainFrame" class="">Commande</a>
			<ul>
				<li><a href="../Commande?parametre=menuCommande&parent=menuCommande" title="Commande" target="mainFrame" class="">Commande</a></li>
			</ul>
		</li>
		<li class=""><a href="../Client?parametre=menuClient&parent=null" title="Client" target="mainFrame" class="">Client</a>
			<ul>
				<li><a href="../Client?parametre=menuClient&parent=menuClient" title="Client" target="mainFrame" class="">Client</a></li>
			</ul>
		</li>
		<li class=""><a href="../Produit?parametre=menuProduit&parent=null" title="Produit" target="mainFrame" class="">Produit</a>
			<ul>
				<li><a href="../Produit?parametre=menuProduit&parent=menuProduit" title="Produit" target="mainFrame" class="">Produit</a></li>
			</ul>
		</li>
		<li class=""><a href="../Utilisateur?parametre=menuUtilisateur&parent=null" title="Utilisateur" target="mainFrame" class="">Utilisateur</a>
			<ul>
				<li><a href="../Utilisateur?parametre=menuUtilisateur&parent=menuUtilisateur" title="Utilisateur" target="mainFrame" class="">Utilisateur</a></li>
			</ul>
		</li>
		<li class=""><a href="../Statistique?parametre=menuStatistique&parent=null" title="Statistique" target="mainFrame" class="">Statistique</a>
			<ul>
				<li><a href="../Statistique?parametre=menuStatistique&parent=menuStatistique" title="Statistique" target="mainFrame" class="">Statistique</a></li>
			</ul>
		</li>
		<li class=""><a href="../Authentification?parametre=menuDeconnexion&parent=null" title="Deconnexion" target="mainFrame" class="Deconnexion">Déconnexion</a>
			<ul>
				<li><a href="../Authentification?parametre=menuDeconnexion&parent=menuDeconnexion" title="Deconnexion" target="mainFrame" class="">Déconnexion</a></li>
	  		</ul> 
		</li>
	</ul>
</body>
</html>
