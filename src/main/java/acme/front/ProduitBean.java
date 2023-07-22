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

	@Override
	public String toString() {
		return "ProduitBean [codeProduit=" + codeProduit + ", libelleProduit=" + libelleProduit + ", description="
				+ description + ", prix=" + prix + ", stimestamp=" + stimestamp + "]";
	}

		
}
