package acme.back.service;

import java.util.ArrayList;

import acme.back.db.UtilisateurDb;
import acme.back.metier.Utilisateur;
import acme.front.UtilisateurBean;
import acme.util.BizException;
import acme.util.Connexion;

public class UtilisateurService {
	
	private static UtilisateurService singleton = null;
	public UtilisateurService() {
		// TODO Auto-generated constructor stub
	}
	
	public static UtilisateurService getService() {
		if (singleton == null) singleton = new UtilisateurService();
		return singleton;
	}
	
	private Utilisateur utilisateurBeanToUtilisateur(UtilisateurBean ub) {
		Utilisateur result = new Utilisateur();
		result.setLogin(ub.getLOGIN());
		result.setCodeRole(ub.getCODE_ROLE());
		return result;
	}
	
	public ArrayList<UtilisateurBean> search(UtilisateurBean ub, Connexion con) throws BizException {		
		ArrayList<UtilisateurBean> result = new ArrayList<UtilisateurBean>();
		Utilisateur ut = utilisateurBeanToUtilisateur(ub);
		try {
			ArrayList<Utilisateur> utilisateurs = UtilisateurDb.getAll(con);
			UtilisateurBean uba = null;
			for (int i=0; i<utilisateurs.size(); i++) {
				Utilisateur tmp = utilisateurs.get(i);
				if (uba != null) result.add(uba);
				uba = new UtilisateurBean();
				uba.setCODE_ROLE(tmp.getCodeRole());
				uba.setLIBELLE_ROLE(tmp.getCodeRole());
				if (i == (utilisateurs.size()-1)) result.add(uba);
			}
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	
	public ArrayList<UtilisateurBean> search(UtilisateurBean ub) throws BizException {
		
		ArrayList<UtilisateurBean> result;
		Connexion con = new Connexion();
		
		try {
			result = search(ub, con);
			con.close();
			return result;
		} catch (BizException be) {
			con.close();
			throw be;
		}
	}

}
