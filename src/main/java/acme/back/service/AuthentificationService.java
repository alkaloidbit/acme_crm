package acme.back.service;

import acme.back.metier.Utilisateur;
import acme.front.AuthentificationBean;
import acme.util.BizException;
import acme.util.Connexion;

public class AuthentificationService {

	private static AuthentificationService singleton = null;
	
	private AuthentificationService() {}

	public static AuthentificationService getService() {
		if (singleton == null) singleton = new AuthentificationService();
		return singleton;
	}
	public AuthentificationBean authenticate(AuthentificationBean ab) throws BizException {
		
		AuthentificationBean result;
		Connexion con = new Connexion();
		System.out.println("Connexion : " + con.getConnection());
		try {
			result = authenticate(ab, con);
			con.close();
			return result;
		} catch (BizException be) {
			con.close();
			throw be;
		}
	}
	public AuthentificationBean authenticate(AuthentificationBean ab, Connexion con) throws BizException {
		
		AuthentificationBean result = new AuthentificationBean();
		
		try {
			Utilisateur u;
			u = AuthentificationBeanToMetier(ab).select(con);
			if (u.getPsw() != null) {
				if (ab.getPassword().compareTo(u.getPsw()) == 0) {
					result = MetierToAuthentificationBean(u);
				} else {
					throw new BizException("Mot de passe incorrect");
				}
			} else {
				throw new BizException("Mot de passe incorrect");
			}
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	private Utilisateur AuthentificationBeanToMetier(AuthentificationBean ab) {
		Utilisateur result = new Utilisateur();
		result.setLogin(ab.getLogin());
		return result;
	}
	private AuthentificationBean MetierToAuthentificationBean(Utilisateur u) {
		AuthentificationBean result = new AuthentificationBean();
		result.setCodeRole(u.getCodeRole());
		result.setLogin(u.getLogin());
		return result;
	}
}
