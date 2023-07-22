package acme.back.service;

import java.util.ArrayList;
import acme.back.metier.Utilisateur;
import acme.back.db.UtilisateurDb;
import acme.back.metier.Role;
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
	
	public ArrayList<UtilisateurBean> getAllUtilisateurs() throws BizException {

		ArrayList<UtilisateurBean> result;
		Connexion con = new Connexion();

		try {
			result = getAllUtilisateurs(con);
			System.out.println(result);
			con.close();
			return result;
		} catch (BizException be) {
			con.close();
			throw be;
		}
	}

	public ArrayList<UtilisateurBean> getAllUtilisateurs(Connexion con) throws BizException {

		ArrayList<UtilisateurBean> result = new ArrayList<UtilisateurBean>();

		try {
			ArrayList<Utilisateur> utilisateurs = UtilisateurDb.getAll(con);
			for (Utilisateur utilisateur : utilisateurs) {
				UtilisateurBean cl = new UtilisateurBean();
				cl.setCODE_ROLE(utilisateur.getCodeRole());
				cl.setLOGIN(utilisateur.getLogin());
				cl.setPSW(utilisateur.getPsw());
				cl.setSTIMESTAMP_UTILISATEUR(utilisateur.getStimestamp());
				result.add(cl);
			}
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}

	public int deleteUtilisateur(UtilisateurBean cb) throws BizException {
		
		int result;
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			result = deleteUtilisateur(cb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			be.printStackTrace();
			throw be;
		}
	}
	public int deleteUtilisateur(UtilisateurBean cb, Connexion con) throws BizException {
		
		int result = 0;
		System.out.println(cb);
		Utilisateur cm = utilisateurBeanToUtilisateur(cb);
		try {
			System.out.println("suppression=" + UtilisateurDb.deleteByKey(con, cm));
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	private Utilisateur utilisateurBeanToUtilisateur(UtilisateurBean cb) {
		Utilisateur result = new Utilisateur();
		result.setCodeRole(cb.getCODE_ROLE());
		result.setLogin(cb.getLOGIN());
		result.setPsw(cb.getPSW());
		result.setStimestamp(cb.getSTIMESTAMP_UTILISATEUR());		

		return result;
	}
	private UtilisateurBean utilisateurToUtilisateurBean(Utilisateur c) {
		UtilisateurBean result = new UtilisateurBean();
		result.setCODE_ROLE(c.getCodeRole());
		result.setLOGIN(c.getLogin());
		result.setPSW(c.getPsw());
		result.setSTIMESTAMP_UTILISATEUR(c.getStimestamp());
		return result;
	}

}
