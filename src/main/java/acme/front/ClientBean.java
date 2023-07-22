package acme.front;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ClientBean extends AbstractBean {
	
	private String codeClient = "";
	private String nom="";
	private String prenom="";
	private String adresse="";
	private String codePostal="";
	private String ville="";
	private Timestamp stimestamp;
	
	public String getCodeClient() {
		return codeClient;
	}


	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public Timestamp getStimestamp() {
		return stimestamp;
	}


	public void setStimestamp(Timestamp stimestamp) {
		this.stimestamp = stimestamp;
	}


	public ClientBean() {}


	@Override
	public String toString() {
		return " [codeClient=" + codeClient + "\n" +
				", nom=" +nom	+ "\n" +
				", prenom=" + prenom + "\n" +
				", adresse=" + adresse + "\n" +
				", codePostal=" + codePostal + "\n" +
				", ville=" + ville + "\n" +
				", stimestamp="	+ stimestamp + "]\n";
	}
	
}
