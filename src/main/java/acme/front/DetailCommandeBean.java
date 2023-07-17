package acme.front;

import java.sql.Timestamp;

public class DetailCommandeBean extends AbstractBean {

	private String codeProduit;
	private String libelleProduit;
	private int quantite;
	private double montant;
	private Timestamp stimestamp;
	
	public DetailCommandeBean() {}

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

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Timestamp getStimestamp() {
		return stimestamp;
	}

	public void setStimestamp(Timestamp stimestamp) {
		this.stimestamp = stimestamp;
	}

	@Override
	public String toString() {
		return "DetailCommandeBean " + "\n" 
				+ "[codeProduit=" + codeProduit + "\n" + 
				", libelleProduit=" + libelleProduit + "\n" + 
				", quantite=" + quantite + "\n" + 
				", montant=" + montant + "\n" +
				", stimestamp=" + stimestamp + "]\n";
	}

}
