<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
		 import="java.util.ArrayList, seifried.back.metier.ListeComposant, seifried.back.metier.Historique"
		 errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Listes de composants</title>
<style type="text/css">
<!--
body,td,th {
	font-family: Tahoma;
	font-size: 10pt;
}
-->
</style>
<% ArrayList al = ((Historique)(request.getSession().getAttribute("h"))).getListeComposant();
	int taille = 0;
	if (al != null) taille = al.size();
	int i = 0;
%>
</head>
<body>
<div align="center">
   <table width="560" border="1">
   <% for (i = 0; i < taille; i++) {%>
     <tr bgcolor="<%=(i%2==0)?"#FFEBBF":"#FFFFFF"%>">
      <th width="142" scope="col"><div align="left"><%=((ListeComposant)al.get(i)).getCodeArticle()%></div></th>
      <th width="341" scope="col"><div align="left"><%=((ListeComposant)al.get(i)).getLibelleArticle()%></div></th>
	  <th width="55" scope="col"><div align="left"><%=String.valueOf(((ListeComposant)al.get(i)).getQuantite())%></div></th>
    </tr>
	<%}%>
  </table>
</div>
</body>
</html>
