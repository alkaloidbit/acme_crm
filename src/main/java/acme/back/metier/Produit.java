package acme.back.metier;
import java.sql.Timestamp;
import java.util.Date;

import acme.back.db.ProduitDb;
import acme.front.AuthentificationBean;
import acme.util.BizException;
import acme.util.Connexion;

public class Produit {

	private String codeProduit;
	private String libelleProduit;
	private String description;
	private float prix;
	private Timestamp stimestamp;

	public Produit(){}

	public String getCodeProduit() { return codeProduit; }
	public String getLibelleProduit() { return libelleProduit; }
	public String getDescription() { return description; }
	public float getPrix() { return prix; }
	public Timestamp getStimestamp() { return stimestamp; }

	public void setCodeProduit(String codeProduit){ this.codeProduit = codeProduit; }
	public void setLibelleProduit(String libelleProduit){ this.libelleProduit = libelleProduit; }
	public void setDescription(String description){ this.description = description; }
	public void setPrix(float prix){ this.prix = prix; }
	public void setStimestamp(Timestamp stimestamp){ this.stimestamp = stimestamp; }

	public String toString() {
		String message = "";
		message = message + "\n" +
			"codeProduit : " + codeProduit + "\n" + 
			"libelleProduit : " + libelleProduit + "\n" + 
			"description : " + description + "\n" + 
			"prix : " + prix + "\n" + 
			"stimestamp : " + stimestamp + "\n" + 
			 super.toString();
		return message;
	}
	public Produit select(AuthentificationBean user, Connexion c) throws BizException {
		return ProduitDb.getByKey(user, c, this);
	}
	public int delete(AuthentificationBean user, Connexion c) throws BizException {
		int result = ProduitDb.deleteByKey(user, c, this);
		return result;
	}
	public int update(AuthentificationBean user, Connexion c) throws BizException {
		int result = ProduitDb.updateByKey(user, c, this);
		return result;
	}
	public int insert(AuthentificationBean user, Connexion c) throws BizException {
		int result = ProduitDb.insert(user, c, this);
		return result;
	}
	public String getKey() {
		return codeProduit;
	}
}

