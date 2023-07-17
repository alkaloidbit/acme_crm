package acme.back.service;

import java.util.ArrayList;

import acme.back.db.CommandeDb;
import acme.back.metier.Commande;
import acme.front.CommandeBean;
import acme.front.DetailCommandeBean;
import acme.util.BizException;
import acme.util.Connexion;

public class CommandeService {

	private static CommandeService singleton = null;
	
	private CommandeService() {}

	public static CommandeService getService() {
		if (singleton == null) singleton = new CommandeService();
		return singleton;
	}
	public ArrayList<CommandeBean> search(CommandeBean cb) throws BizException {
		
		ArrayList<CommandeBean> result;
		Connexion con = new Connexion();
		
		try {
			result = search(cb, con);
			con.close();
			return result;
		} catch (BizException be) {
			con.close();
			throw be;
		}
	}
	public ArrayList<CommandeBean> search(CommandeBean cb, Connexion con) throws BizException {
		
		ArrayList<CommandeBean> result = new ArrayList<CommandeBean>();
		Commande cm = commandeBeanToCommande(cb);
		try {
			ArrayList<Commande> commandes = CommandeDb.search(con, cm);
			CommandeBean cbt = null;
			int pId = -1; 
			for (int i=0; i<commandes.size(); i++) {
				Commande tmp = commandes.get(i);
				int cId = tmp.getIdCommande();
				if (cId != pId) {
					if (cbt != null) result.add(cbt);
					cbt = new CommandeBean();
					DetailCommandeBean dcb = new DetailCommandeBean();
					cbt.setCodeClient(tmp.getCodeClient());
					cbt.setDateCommande(tmp.getDate());
					cbt.setIdCommande(tmp.getIdCommande());
					cbt.setNomClient(tmp.getNomClient());
					cbt.setStimestamp(tmp.getStimestamp());
					dcb.setCodeProduit(tmp.getCodeProduit());
					dcb.setLibelleProduit(tmp.getLibelleProduit());
					dcb.setMontant(tmp.getPrix() * tmp.getQuantite());
					dcb.setQuantite(tmp.getQuantite());
					dcb.setStimestamp(tmp.getStimestamp());
					cbt.addDetailCommandeBean(dcb);
				} else {
					DetailCommandeBean dcb = new DetailCommandeBean();
					dcb.setCodeProduit(tmp.getCodeProduit());
					dcb.setLibelleProduit(tmp.getLibelleProduit());
					dcb.setMontant(tmp.getPrix() * tmp.getQuantite());
					dcb.setQuantite(tmp.getQuantite());
					dcb.setStimestamp(tmp.getStimestamp());
					cbt.addDetailCommandeBean(dcb);	
				}
				if (i == (commandes.size()-1)) result.add(cbt);
				pId = cId;
			}
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	/*
	public ArrayList<CommandeBean> getAllCommandes() throws BizException {
		
		ArrayList<CommandeBean> result;
		Connexion con = new Connexion();
		
		try {
			result = getAllCommande(con);
			con.close();
			return result;
		} catch (BizException be) {
			con.close();
			throw be;
		}
	}
	public ArrayList<CommandeBean> getAllCommande(Connexion con) throws BizException {
		
		ArrayList<CommandeBean> result = new ArrayList<CommandeBean>();
				
		try {
			ArrayList<Commande> commandes = CommandeDb.getAll(con);
			for (Commande commande : commandes) {
				ArrayList<DetailCommandeBean> dcbs = new ArrayList<DetailCommandeBean>();
				ArrayList<DetailCommande> dcs = getAllDetailCommandeByIdCommande(commande.getIdCommande(), con);
				for (DetailCommande detailCommande : dcs) {
					Produit p = new Produit();
					p.setCodeProduit(detailCommande.getCodeProduit());
					p = p.select(con);
					DetailCommandeBean dcb = detailCommandeToDetailCommandeBean(detailCommande, p);
					dcbs.add(dcb);
				}
				Client cl = new Client();
				cl.setCodeClient(commande.getCodeClient());
				cl = cl.select(con);
				result.add(commandeToCommandeBean(commande, cl, dcbs));
			}
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	public ArrayList<DetailCommande> getAllDetailCommandeByIdCommande(int idCommande, Connexion con) throws BizException {
		
		ArrayList<DetailCommande> result;
		
		try {
			DetailCommande dc = new DetailCommande();
			dc.setIdCommande(idCommande);
			result = DetailCommandeDb.getByIdCommande(con, dc);
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	*/
	private Commande commandeBeanToCommande(CommandeBean cb) {
		Commande result = new Commande();
		result.setDate(cb.getDateCommande());
		result.setIdCommande(cb.getIdCommande());
		result.setNomClient(cb.getNomClient());
		return result;
	}
	/*
	private DetailCommandeBean detailCommandeToDetailCommandeBean(DetailCommande dc, Produit p) {
		DetailCommandeBean result = new DetailCommandeBean();
		result.setCodeProduit(dc.getCodeProduit());
		result.setLibelleProduit(p.getLibelleProduit());
		result.setStimestamp(dc.getStimestamp());
		result.setQuantite(dc.getQuantite());
		result.setMontant(p.getPrix()*dc.getQuantite());
		return result;
	}
	private CommandeBean commandeToCommandeBean(Commande c, Client cl, ArrayList<DetailCommandeBean> al) {
		CommandeBean result = new CommandeBean();
		result.setCodeClient(c.getCodeClient());
		result.setDateCommande(c.getDate());
		result.setIdCommande(c.getIdCommande());
		result.setStimestamp(c.getStimestamp());
		result.setCodeClient(cl.getCodeClient());
		result.setNomClient(cl.getNom());
		result.setAl(al);
		return result;
	}
	*/
}
