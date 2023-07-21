<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.ArrayList, acme.front.ClientBean, acme.util.Utilitaire"
	errorPage=""%>
<%
ClientBean cl = (ClientBean) request.getSession().getAttribute("cbdetail");
%>
<div class="card">
	<div class="card-header">
		<h3 class="card-title">Clients</h3>
	</div>
	<!-- /.card-header -->
	<div class="card-body">
		<div class="form-horizontal">
			<div class="row">
				<label class="control-label col-lg-3">Titre de civilité</label>
				<div class="col-lg-9">
					<p class="form-control-static">Inconnu</p>
				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Nom</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%=cl.getNom()%></p>
				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Prenom</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%=cl.getPrenom()%></p>
				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Adresse</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%=cl.getAdresse()%></p>
				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Code Postal</label>
				<div class="col-lg-9">

					<p class="form-control-static"><%=cl.getCodePostal()%></p>

				</div>
			</div>
			<div class="row">
				<label class="control-label col-lg-3">Ville</label>
				<div class="col-lg-9">
					<p class="form-control-static"><%=cl.getVille()%></p>
				</div>
			</div>
		</div>
	</div>
</div>
