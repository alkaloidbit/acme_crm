package acme.back.servlet;

import java.util.Comparator;

import acme.front.CommandeBean;

public class ComparateurCommandeBean implements Comparator<CommandeBean> {
	
	private static String critere = "";
	private static ComparateurCommandeBean singleton = null;
	
	private ComparateurCommandeBean() {
		// TODO Auto-generated constructor stub
	}
	public static ComparateurCommandeBean getSingleton(String critere) {
		if (singleton == null) {
			singleton = new ComparateurCommandeBean();
		}
		ComparateurCommandeBean.critere = critere;
		return singleton;
	}

	@Override
	public int compare(CommandeBean o1, CommandeBean o2) {
		if (critere.compareTo("date") == 0) {
			if (o1.getDateCommande().after(o2.getDateCommande())) return 1;
			else if (o1.getDateCommande().before(o2.getDateCommande())) return -1;
			return 0;
		} else
		if (critere.compareTo("idCommande") == 0) {
			if (o1.getIdCommande() > o2.getIdCommande()) return 1;
			else if (o1.getIdCommande() < o2.getIdCommande()) return -1;
			return 0;
		} else
		if (critere.compareTo("nom") == 0) {
			return o1.getNomClient().compareTo(o2.getNomClient()); 
		}
		return 0;
	}

}
