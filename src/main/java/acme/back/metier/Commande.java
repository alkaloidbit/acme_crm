package acme.back.metier;

import java.sql.Timestamp;
import java.util.Date;

import acme.back.db.CommandeDb;
import acme.util.BizException;
import acme.util.Connexion;


public class Commande {

	private int idCommande;
	private String codeClient;
	private Date date;
	private Timestamp stimestamp;
	
	private String nomClient;
	private String codeProduit;
	private String libelleProduit;
	private double prix;
	private int quantite;

	public Commande(){}

	public int getIdCommande() { return idCommande; }
	public String getCodeClient() { return codeClient; }
	public Date getDate() { return date; }
	public Timestamp getStimestamp() { return stimestamp; }

	public void setIdCommande(int idCommande){ this.idCommande = idCommande; }
	public void setCodeClient(String codeClient){ this.codeClient = codeClient; }
	public void setDate(Date date){ this.date = date; }
	public void setStimestamp(Timestamp stimestamp){ this.stimestamp = stimestamp; }

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	
	public String getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}
	
	public String getLibelleProduit() {
		return libelleProduit;
	}

	public void setLibelleProduit(String libelleProduit) {
		this.libelleProduit = libelleProduit;
	}
	
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String toString() {
		String message = "";
		message = message + "\n" +
			"idCommande : " + idCommande + "\n" + 
			"codeClient : " + codeClient + "\n" + 
			"date : " + date + "\n" + 
			"stimestamp : " + stimestamp + "\n" + 
			 super.toString();
		return message;
	}
	public Commande select(Connexion c) throws BizException {
		return CommandeDb.getByKey(c, this);
	}
	public int delete(Connexion c) throws BizException {
		int result = CommandeDb.deleteByKey(c, this);
		return result;
	}
	public int update(Connexion c) throws BizException {
		int result = CommandeDb.updateByKey(c, this);
		return result;
	}
	public int insert(Connexion c) throws BizException {
		int result = CommandeDb.insert(c, this);
		return result;
	}
	public String getKey() {
		return String.valueOf(idCommande);
	}
}

