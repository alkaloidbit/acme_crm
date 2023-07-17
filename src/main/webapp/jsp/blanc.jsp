<%@ page contentType="text/html; charset=iso-8859-1"
	language="java"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Accueil</title>
<script language="javascript">
function loadGauche() {
	parent.frames['leftFrame'].window.location='jsp/gauche.jsp';
	return true;
}
</script>
</head>
<body onload="loadGauche()">
</body>
</html>
