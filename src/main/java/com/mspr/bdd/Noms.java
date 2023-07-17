package com.mspr.bdd;

import com.mspr.beans.Utilisateur;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Noms {
	private Connection connexion;

	public List<Utilisateur> getUtilisateurs() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();

			resultat = statement.executeQuery("SELECT LOGIN, PSW FROM `utilisateur`;");

			while (resultat.next()) {
				String nom = resultat.getString("LOGIN");
				String prenom = resultat.getString("PSW");

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				utilisateur.setActif(true);

				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
		} finally {
			// fermeture de la connexion
			try {
				if (resultat != null)
					resultat.close();
				if (statement != null)
					statement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}
		return utilisateurs;
	}

	private void loadDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mspr", "root", "RvTC6F8D");
			System.out.println(connexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
