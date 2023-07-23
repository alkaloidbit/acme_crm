<%@ page contentType="text/html; charset=iso-8859-1"
		 language="java"
	 	 import="java.util.ArrayList, acme.front.CommandeBean, acme.util.Utilitaire, java.util.Date, acme.front.ProduitBean"
		 errorPage="" %>

<% 	
	ArrayList<ProduitBean> al = (ArrayList<ProduitBean>)request.getSession().getAttribute("produits");
	int taille = al.size();
%>
<form methode=post action="Commande">
<div class="card">
	<div class="card-header">
		<h3 class="card-title">Créer commande</h3>
	</div>
	    <fieldset>
	      <div class="form-group">
	        <label for="nom">Code client (*) : </label>
	        <input type="text" class="form-control" name="codeClient">
	      </div>
	      <div class="form-group">
	        <label for="date">Date (*) : </label>
	        <input type="date" class="form-control" name="dateCommande">
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
      								<input name="box<%=i%>" type="checkbox" aria-label="Checkbox for following text input">
    							</div>
  							</div>
  							<input name="qte<%=i%>" type="text" class="form-control" aria-label="Text input with checkbox">
						</div>
					</td>
				</tr>
			<%}%>
			</tbody>
		</table>
	</div>
	<!-- /.card-body -->
</div>
		<button name="parametre" value="enregistrer" type="submit" class="btn btn-primary">Enregistrer</button>
		<!-- <a class="btn btn-primary" role="button" href="./Commande?parametre=enregistrer">Enregistrer</a> -->
		<a class="btn btn-primary" role="button" href="./Commande?parametre=annuler">Annuler</a>
</form>
<!-- /.card -->

