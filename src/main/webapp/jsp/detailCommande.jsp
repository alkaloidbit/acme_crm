<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
		 import="java.util.ArrayList, java.util.HashMap, acme.util.Utilitaire, acme.front.CommandeBean"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Commande</title>

<%
	String msg = (String)request.getSession().getAttribute("erreur");
	CommandeBean cb = (CommandeBean)request.getSession().getAttribute("cb");
%>
	
<script language="javascript" src="./jsp/mspr.js" type="text/javascript"></script>
<script language="javascript">

function erreur() {	if ("<%=msg%>" != "null" && "<%=msg%>" != "") { alert("<%=msg%>"); }}
function onloadFocus() { window.document.forms[0].codeCommande.focus(); }
function loadMenu() { 
	parent.frames['leftFrame'].location.reload();
	return true;
}
function verifRecherche() {
	if (isNull(window.document.forms[0].codeArticle.value) && isNull(window.document.forms[0].codeCommande.value) && isNull(window.document.forms[0].codeClient.value) && isNull(window.document.forms[0].dateCommande.value)) {
		window.document.forms[0].codeCommande.focus();
		return false;
	}
	if (isNull(window.document.forms[0].dateCommande.value)) return true;
	if (isDateFormat(window.document.forms[0].dateCommande.value) == false) {
		window.document.forms[0].dateCommande.focus();
		return false;
	}
	return true;
}

function verification() {
if (window.document.forms[0].simu.value == "") {
	return false;
} else { return true; }}
</script>

</head>
<body link="#336600" vlink="#336600" alink="#339900" onload="erreur();onloadFocus();loadMenu()">
<div align="center">
  <p><font color="#006600" id="1" size="5" face="Tahoma"><b>Commande<br>
  </b></font></p>
  <p>&nbsp;</p>
</div>
<FORM method=post action="Commande" >
<table width="970" height="577" border="0" align="center">
  <tr>
    <td width="190" height="240">&nbsp;</td>
    <td width="600">
		 <table width="425" border="0" align="left">
	<tr>
      <td width="140" bgcolor="#FFEBBF"><div align="left" id="2" class="style4"><strong><font size="2" face="Tahoma">N° Commande : </font></strong></div></td>
      <td width="275"><div align="left" class="style11">
	  	<input name="idCommande" type="text" value="<%=(cb.getIdCommande()!=0)?cb.getIdCommande():""%>" size="21" maxlength="20"/>
	  </div></td>
    </tr>		 
    <tr>
      <td width="140" bgcolor="#FFEBBF"><div align="left" id="3" class="style4"><strong><font size="2" face="Tahoma">Date : </font></strong></div></td>
      <td width="275"><div align="left" class="style11">
	  	<input name="dateCommande" type="text" value="<%=(cb.getDateCommande()!=null)?Utilitaire.getDateEuropeenne(cb.getDateCommande()):""%>" size="11" maxlength="10"/>
	  </div></td>
    </tr>
	<tr>
      <td width="140" bgcolor="#FFEBBF"><div align="left" id="4" class="style4"><strong><font size="2" face="Tahoma">Client : </font></strong></div></td>
      <td width="275"><div align="left" class="style11">
	  	<input name="nom" type="text" value="<%=(cb.getNomClient()!=null)?cb.getNomClient():""%>" size="11" maxlength="10"/>
	 </div></td>
    </tr>		 
  </table>
	</td>
    <td width="166"><input name="rechercher" type="submit" id="8" value="Rechercher" onclick="return verifRecherche()"/></td>
  </tr>
  <tr>
    <td height="33">&nbsp;</td>
    <td><table width="602" border="1">
      <tr>
        <td width="200"><div align="center"><font face="Tahoma"><a href="Commande?critere=idCommande" id="8" target="mainFrame" class="style1"><b>N° Commande</b></a></font></div></td>
        <td width="140"><div align="center"><font face="Tahoma"><a href="Commande?critere=date" id="9" target="mainFrame" class="style1"><b>Date</b></a></font></div></td>
		<td width="236"><div align="center"><font face="Tahoma"><a href="Commande?critere=nom" id="10" target="mainFrame" class="style1"><b>Nom/Raison sociale</b></a></font></div></td>
		<td width="140"><div align="center"><font face="Tahoma"><a href="Commande?critere=montant" id="11" target="mainFrame" class="style1"><b>Montant</b></a></font></div></td>		
		</tr>
    </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="202">&nbsp;</td>
    <td><iframe name="centre" width="600" height="200" src="./jsp/commandeCentre.jsp" align="center" scrolling="auto"></iframe></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="90">&nbsp;</td>
    <td><div align="center">
      <p>
	  	<input name="page" type="hidden" value="commande" />
		<input name="simu" type="hidden"/>
        <input name="detail" type="submit" id="6" value="Détail" onclick="return verification()"/>
        <input name="creer" type="submit" id="11" value="Créer"/>
        <input name="modifier" type="submit" id="9" value="Modifier" onclick="return verification()"/>
        <input name="supprimer" type="submit" id="10" value="Supprimer" onclick="return verification()"/>
      </p>
      </div></td>
  </tr>
</table>
</FORM>
</body>
</html>
