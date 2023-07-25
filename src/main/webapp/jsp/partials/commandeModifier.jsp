<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
	 	 import="java.util.ArrayList, java.util.HashMap, acme.front.CommandeBean, acme.front.DetailCommandeBean, acme.util.Utilitaire, java.util.Date, acme.front.ProduitBean"
		 errorPage="" 
%>

<% 	
	String msg = (String)request.getSession().getAttribute("erreur");
	ArrayList<ProduitBean> al = (ArrayList<ProduitBean>)request.getSession().getAttribute("produits");
	CommandeBean cb = (CommandeBean)request.getSession().getAttribute("cb");
	ArrayList<DetailCommandeBean> dcbs = cb.getAl();
	HashMap<String, DetailCommandeBean> hm = new HashMap<String, DetailCommandeBean>();
	for (int k=0; k<dcbs.size();k++) {
		hm.put(dcbs.get(k).getCodeProduit(), dcbs.get(k));
	}
	int taille = al.size();
	String unchecked = "";
	String checked = "checked";
%>
<script type="text/javascript" src="./jsp/mspr.js" type="text/javascript"></script>
<script type="text/javascript">
function erreur() {	if ("<%=msg%>" != "null" && "<%=msg%>" != "") { alert("<%=msg%>"); }}
function enregistrer() {
	let text = "Enregistrement ?";
		if (confirm(text) == true) {
	 	return true;
	} else {
		return false;
	}
}
function verification() {
	if (isNull(window.document.forms[0].codeClient.value)) {
		window.document.forms[0].codeClient.focus();
		alert("Champ vide");
		return false;
	}
	if (isNull(window.document.forms[0].dateCommande.value)) {
		window.document.forms[0].dateCommande.focus();
		alert("Champ vide");
		return false;
	} 
	if (enregistrer()) {
		return true;
	} else {
		return false;
	}
}
</script>
<body onload="erreur()">
<form methode=post action="Commande">
<div class="card">
	<div class="card-header">
		<h3 class="card-title">Créer commande</h3>
	</div>
	    <fieldset>
	      <div class="form-group">
	        <label for="nom">N° Commande : </label>
	        <input type="text" class="form-control" name="idCommande" value="<%=cb.getIdCommande()%>" disabled>
	      </div>
	      <div class="form-group">
	        <label for="nom">Code client (*) : </label>
	        <input type="text" class="form-control" name="codeClient" value="<%=cb.getCodeClient()%>">
	      </div>
	      <div class="form-group">
	        <label for="date">Date (*) : </label>
	        <input type="date" class="form-control" name="dateCommande" value="<%=Utilitaire.getDateAmericaineAvecTiret(cb.getDateCommande())%>">
	      </div>
	     <fieldset>	 
	<!-- /.card-header -->
	<div class="card-body">
		<table id="example1" class="table table-bordered table-striped">
			<thead>
			<tr>
				<th>Code produit</th>
				<th>libellé</th>
				<th>Prix</th>
				<th>Quantité</th>
			</tr>
			</thead>
			<tbody>
			<% for (int i = 0; i < taille; i++) {%>
				<tr>
					<td><%=((ProduitBean)al.get(i)).getCodeProduit()%></td>
					<td><%=((ProduitBean)al.get(i)).getLibelleProduit()%></td>
					<td><%=Math.round(((ProduitBean)al.get(i)).getPrix()*100.0)/100.0%></td>
					<td><div class="input-group mb-3">
  							<div class="input-group-prepend">
    							<div class="input-group-text">
      								<input name="box<%=i%>" value="" <%=(hm.get(al.get(i).getCodeProduit())!=null)?"checked":""%> type="checkbox"  aria-label="Checkbox for following text input">
    							</div>
  							</div>
  							<input name="qte<%=i%>" value="<%=(hm.get(al.get(i).getCodeProduit())!=null)?hm.get(al.get(i).getCodeProduit()).getQuantite():""%>" type="text" class="form-control" aria-label="Text input with checkbox">
						</div>
					</td>
				</tr>
			<%}%>
			</tbody>
		</table>
	</div>
	<!-- /.card-body -->
</div>
		<button name="parametre" value="enregistrer" type="submit" class="btn btn-primary" onclick="return verification()">Enregistrer</button>
		<a class="btn btn-primary" role="button" href="./Commande?parametre=annuler">Annuler</a>
		<input id="1" name="page" type="hidden" value="commandeModifier">
</form>
</body>
<!-- /.card -->

