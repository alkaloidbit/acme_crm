<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
		 import="acme.util.Utilitaire, acme.front.CommandeBean"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Détail commande</title>
<style type="text/css">
<!--
.style18 {font-family: Tahoma; font-size: 10pt; color: #336600; }
-->
</style>
<%	
	String msg = (String)request.getSession().getAttribute("erreur");
	CommandeBean cb = (CommandeBean)request.getSession().getAttribute("cbdetail");
%>
	
<script language="javascript" src="./jsp/mspr.js" type="text/javascript"></script>
<script language="javascript">
	function erreur() {	if ("<%=msg%>" != "null" && "<%=msg%>" != "") { alert("<%=msg%>"); }}
</script>

</head>
<body link="#336600" vlink="#336600" alink="#339900" onload="erreur()">
<div align="center">
  <p><font color="#006600" size="5" id="1" face="Tahoma"><b>Détail commande</b></font></p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p><font color="#006600" size="5" face="Tahoma"><b></br>
  </b></font></p>
</div>
<FORM method=post action="Commande" >
<table width="970" height="569" border="0" align="center">
  <tr>
    <td width="190" height="234">&nbsp;</td>
    <td width="600">
		 <table width="425" height="226" border="0" align="left">
	<tr>
      <td width="140" bgcolor="#FFEBBF"><div align="left" id="2" class="style4"><strong><font size="2" face="Tahoma">Id Commande : </font></strong></div></td>
      <td width="275"><div align="left" class="style4"><font size="2" face="Tahoma"><%=cb.getIdCommande()%></font>
	  </div></td>
    </tr>
	<tr>
      <td width="150" bgcolor="#FFEBBF"><div align="left" id="3" class="style4"><strong><font size="2" face="Tahoma">Nom/Raison sociale : </font></strong></div></td>
      <td width="275"><div align="left" class="style11"><font size="2" face="Tahoma"><%=cb.getNomClient()%></font>
	  </div></td>
    </tr>		 
    <tr>
      <td width="140" bgcolor="#FFEBBF"><div align="left" id="4" class="style4"><strong><font size="2" face="Tahoma">Date : </font></strong></div></td>
      <td width="275"><div align="left" class="style11"><font size="2" face="Tahoma"><%=Utilitaire.getDateEuropeenne(cb.getDateCommande())%></font>
	  </div></td>
    </tr>
	<tr>
      <td height="104"><div align="left">
        <p class="style18"><br>
            <br>
          <br></p>
        <p class="style18" id="7"><u>Produits</u></p>
      </div></td>
    </tr>
  </table>
	</td>
    <td width="166">&nbsp;</td>
  </tr>
  <tr>
    <td height="31">&nbsp;</td>
    <td><table width="602" border="1">
      <tr>
        <td width="149"><div align="center" id="8"><font color="#336600" face="Tahoma"><b>Code</b></a></font></div></td>
        <td width="349"><div align="center" id="9"><font color="#336600" face="Tahoma"><b>Libellé</b></a></font></div></td>
		<td width="82"><div align="center" id="10"><font color="#336600" face="Tahoma"><b>Quantité</b></a></font></div></td>
		<td width="82"><div align="center" id="10"><font color="#336600" face="Tahoma"><b>Montant</b></a></font></div></td>
      </tr>
    </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="202">&nbsp;</td>
    <td><iframe name="centre" width="600" height="200" src="./jsp/detailCommandeCentre.jsp" align="center" scrolling="auto"></iframe></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="90">&nbsp;</td>
    <td><div align="center">
      <p>
	    <input name="page" type="hidden" value="detailCommande" />
        <input name="retour" type="submit" id="2" value="Retour" />
      </p>
      </div></td>
  </tr>
</table>
</FORM>
</body>
</html>
