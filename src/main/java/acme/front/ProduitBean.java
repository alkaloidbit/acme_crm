package acme.front;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import acme.back.metier.Produit;

public class ProduitBean extends AbstractBean {
	
	private String codeProduit;
	private String libelleProduit;
	private String description;
	private float prix;
	private Timestamp stimestamp;
	private ArrayList<Produit> prods = new ArrayList<Produit>();
	
	public ProduitBean() {}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Timestamp getStimestamp() {
		return stimestamp;
	}

	public void setStimestamp(Timestamp stimestamp) {
		this.stimestamp = stimestamp;
	}

	public ArrayList<Produit> getProds() {
		return prods;
	}

	public void setProds(ArrayList<Produit> prods) {
		this.prods = prods;
	}
	@Override
	public String toString() {
		return "[\n" +
			"codeProduit : " + codeProduit + "\n" + 
			"libelleProduit : " + libelleProduit + "\n" + 
			"description : " + description + "\n" + 
			"prix : " + prix + "\n" + 
			"stimestamp : " + stimestamp + "\n" + 
			"prods : " + stimestamp + "]\n";
			 
	}
		
}
