package acme.front;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class CommandeBean extends AbstractBean {
	
	private int idCommande;
	private String codeClient = "";
	private String nomClient="";
	private Date dateCommande;
	private ArrayList<DetailCommandeBean> al = new  ArrayList<DetailCommandeBean>();
	private Timestamp stimestamp;
	
	public CommandeBean() {}

	public void addDetailCommandeBean(DetailCommandeBean dcb) {
		al.add(dcb);
	}
	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public String getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}
	
	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	
	public double getMontant() {
		double somme = 0.0f;
		for (DetailCommandeBean dcb : al)
			somme = somme + dcb.getMontant();
		return somme;
	}
	public ArrayList<DetailCommandeBean> getAl() {
		return al;
	}

	public void setAl(ArrayList<DetailCommandeBean> al) {
		this.al = al;
	}

	public Timestamp getStimestamp() {
		return stimestamp;
	}

	public void setStimestamp(Timestamp stimestamp) {
		this.stimestamp = stimestamp;
	}

	@Override
	public String toString() {
		return " [idCommande=" + idCommande + "\n" +
				", codeClient=" + codeClient + "\n" +
				", nomClient=" + nomClient	+ "\n" +
				", dateCommande=" + dateCommande + "\n" +
				", montant=" + getMontant() + "\n" +
				", al=" + al + "\n" +
				", stimestamp="	+ stimestamp + "]\n";
	}
	
}
