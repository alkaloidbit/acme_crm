package acme.back.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

import acme.back.db.CommandeDb;
import acme.back.db.DetailCommandeDb;
import acme.back.metier.Commande;
import acme.back.metier.DetailCommande;
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
	public int deleteCommande(CommandeBean cb) throws BizException {
		
		int result;
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			result = deleteCommande(cb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			be.printStackTrace();
			throw be;
		}
	}
	public int deleteCommande(CommandeBean cb, Connexion con) throws BizException {
		
		int result = 0;
		Commande cm = commandeBeanToCommande(cb);
		try {
			for (DetailCommande tmp  : cm.getAl()) {
				System.out.println("suppression detail=" + DetailCommandeDb.deleteByKey(con, tmp)); 
			}
			System.out.println("suppression=" + CommandeDb.deleteByKey(con, cm));
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	public int insertCommande(CommandeBean cb) throws BizException {
		
		int result;
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			result = insertCommande(cb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			be.printStackTrace();
			throw be;
		}
	}
	public int insertCommande(CommandeBean cb, Connexion con) throws BizException {
		
		int result = 0;
		Commande cm = commandeBeanToCommande(cb);
		Date now = new Date();
		cm.setStimestamp(new Timestamp((now.getTime())));
		try {
			result = cm.insert(con);
			int idCommande = CommandeDb.getLastInsert(con);
			for (int i = 0; i < cm.getAl().size(); i++) {
				DetailCommande dc = cm.getAl().get(i);
				dc.setIdCommande(idCommande);
				dc.setStimestamp(cm.getStimestamp());
				dc.insert(con);
			}
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	public int updateCommande(CommandeBean cb) throws BizException {
		
		int result;
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			result = updateCommande(cb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			be.printStackTrace();
			throw be;
		}
	}
	public int updateCommande(CommandeBean cb, Connexion con) throws BizException {
		
		int result = 0;
		Commande cm = commandeBeanToCommande(cb);
		Date now = new Date();
		cm.setStimestamp(new Timestamp((now.getTime())));
		try {
			//Supression de la commande
			result = DetailCommandeDb.deleteByIdCommande(con, cm.getAl().get(0));
			System.out.println("resultat de delete=" + result);
			for (DetailCommande tmp  : cm.getAl()) {
				result = DetailCommandeDb.insert(con, tmp);
			}
			System.out.println("resultat de l'insert=" + result);
			result = cm.update(con);
			System.out.println("resultat de l'update=" + result);
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	private Commande commandeBeanToCommande(CommandeBean cb) {
		Commande result = new Commande();
		result.setDate(cb.getDateCommande());
		result.setIdCommande(cb.getIdCommande());
		result.setNomClient(cb.getNomClient());
		result.setCodeClient(cb.getCodeClient());
		result.setStimestamp(cb.getStimestamp());
		ArrayList<DetailCommandeBean> dcbs = cb.getAl();
		for (DetailCommandeBean tmp  : dcbs) {
			DetailCommande dc = new DetailCommande();
			dc.setCodeProduit(tmp.getCodeProduit());
			dc.setIdCommande(cb.getIdCommande());
			dc.setQuantite(tmp.getQuantite());
			dc.setStimestamp(tmp.getStimestamp());
			result.addDetailCommande(dc);
		}
		return result;
	}
}
