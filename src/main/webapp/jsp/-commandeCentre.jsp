<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
		 import="java.util.ArrayList, acme.front.CommandeBean, acme.util.Utilitaire"
		 errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Commande</title>
<style type="text/css">
<!--
body,td,th {
	font-family: Tahoma;
	font-size: 10pt;
}
-->
</style>
<% 	
	ArrayList<CommandeBean> al = (ArrayList<CommandeBean>)request.getSession().getAttribute("commandes");
	int taille = al.size();
%>
</head>
<body>
<div align="center">
   <table width="590" border="1">
   <% for (int i = 0; i < taille; i++) {%>
     <tr bgcolor="<%=(i%2==0)?"#FFEBBF":"#FFFFFF"%>">
       <th width="23" scope="col">
	   	<input type=radio name="choix" onclick="parent.document.forms[0].simu.value = <%=String.valueOf(i)%>"/>
	   </th>
      <th width="225" scope="col"><div align="left"><%=((CommandeBean)al.get(i)).getIdCommande()%></div></th>
      <th width="130" scope="col"><div align="left"><%=Utilitaire.getDateEuropeenne(((CommandeBean)al.get(i)).getDateCommande())%></div></th>
	  <th width="305" scope="col"><div align="left"><%=((CommandeBean)al.get(i)).getNomClient()%></div></th>
	  <th width="185" scope="col"><div align="left"><%=Math.round(((CommandeBean)al.get(i)).getMontant()*100.0)/100.0%></div></th>
    </tr>
	<%}%>
  </table>
</div>
</body>
</html>
