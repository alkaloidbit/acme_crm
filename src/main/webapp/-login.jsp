<%@ page contentType="text/html; charset=iso-8859-1"
    language="java"
    import="acme.util.Utilitaire"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<META http-equiv="Pragma" CONTENT="no-cache" />
<title>Login</title>
<style type="text/css">
<!--
.style18 {font-family: Tahoma; font-size: 10pt; color: #336600; }
-->
</style>
<%	
	String msg = (String)request.getSession().getAttribute("erreur");
	System.out.println ("msg="+msg);
%>
<script language="javascript" src="./jsp/mspr.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
function erreur() { if ("<%=msg%>" != "null" && "<%=msg%>" != "") { alert("<%=msg%>"); }}
function loadMenu() {
	parent.frames['leftFrame'].window.location='menu.html';
	return true;
}
function soumet() {
	if (!isNull(window.document.forms[0].password.value)) {
		window.document.forms[0].password.value=<%=Utilitaire.getStringMD5(request.getParameter("password"))%>
		alert(window.document.forms[0].password.value);
		alert("true");
		return true;
	}
	alert("false");
	return false;
}
</script>
</head>
<body onload="erreur();loadMenu()">
	<table width="200" border="0">
  		<tr><td>&nbsp;</td></tr>
  		<tr><td>&nbsp;</td></tr>
	</table>
	<div align="center">
  		<p><font color="#006600" size="5" id="1" face="Tahoma"><b>Login</b></font></p>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
	</div>
	<FORM method=post action="Authentification">
	<table align="center" width="300" border="0">
  		<tr><td align="center"><input type="text" name="login" maxlength="30" value=""/></td></tr>
  		<tr><td align="center"><input type="password" name="password" maxlength="30" value=""/></td></tr>
  		<tr><td align="center">
			<input name="connexion" type="submit" value="connexion"/>
		</td></tr>
	</table>
	</FORM>
</body>
</html>

