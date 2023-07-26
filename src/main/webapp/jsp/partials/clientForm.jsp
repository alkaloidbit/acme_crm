<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>

<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">Formulaire Client</h3>
	</div>
		<form method="POST" action="Client">
			<div class="card-body">
				<div class="form-group">
					<label for="inputCodeClient">Code Client</label>
					<input type="text" name="code_client" class="form-control" id="inputCodeClient" placeholder="Code Client">
				</div>
				<div class="form-group">
					<label for="inputNom">Nom</label>
					<input type="text" name="nom" class="form-control" id="inputNom" placeholder="Nom">
				</div>
				<div class="form-group">
					<label for="inputPrenom">Prenom</label>
					<input type="text" name="prenom" class="form-control" id="inputPrenom" placeholder="Prenom">
				</div>
				<div class="form-group">
					<label for="inputAdresse">Adresse</label>
					<input type="text" name="adresse" class="form-control" id="inputAdresse" placeholder="Adresse">
				</div>
				<div class="form-group">
					<label for="inputCodePostal">Code Postal</label>
					<input type="text" name="code_postal" class="form-control" id="inputCodePostal" placeholder="Code Postal">
				</div>
				<div class="form-group">
					<label for="inputCity">Ville</label>
					<input type="text" name="ville" class="form-control" id="inputCity" placeholder="Ville">
				</div>
			</div>
			<div class="card-footer">
				<button name="submit" type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
</div>
