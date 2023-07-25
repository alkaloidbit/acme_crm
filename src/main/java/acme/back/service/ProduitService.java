package acme.back.service;

import java.util.ArrayList;


import acme.back.db.ProduitDb;
import acme.back.metier.Produit;
import acme.front.AuthentificationBean;
import acme.front.ProduitBean;
import acme.util.BizException;
import acme.util.Connexion;

public class ProduitService {
	private static ProduitService singleton = null;
	
	public ProduitService() {}

	public static ProduitService getService() {
		if (singleton == null) singleton = new ProduitService();
		return singleton;
	}

	
	public ArrayList<ProduitBean> ProductListBean(AuthentificationBean user) throws BizException {		
		if (user.hasPermissionToReadProduct()) {
			Connexion con = new Connexion();
			ArrayList<ProduitBean> prodBeans = new ArrayList<ProduitBean>();
			try {
				ArrayList<Produit> productList = ProduitDb.getAll(user, con);
				for (Produit produit : productList) {
					ProduitBean pb = new ProduitBean();
					pb.setCodeProduit(produit.getCodeProduit());
					pb.setLibelleProduit(produit.getLibelleProduit());
					pb.setPrix(produit.getPrix());
					pb.setDescription(produit.getDescription());
					pb.setStimestamp(produit.getStimestamp());
					prodBeans.add(pb);
				}
				con.close();
				return prodBeans;
	
			} catch (BizException be) {
				con.close();
				throw be;
			}
		}
		else {
			throw new BizException("Accès refusé pour afficher les produits.");
	    }

	}
	
	public int deleteProduit(AuthentificationBean user, ProduitBean pb) throws BizException {
		if (user.hasPermissionToDeleteProduct()) {
			int result;
			Connexion con = new Connexion();
			
			try {
				con.beginTransaction();
				result = deleteProduit(user, pb, con);
				con.endTransaction();
				return result;
			} catch (BizException be) {
				con.rollBack();
				be.printStackTrace();
				throw be;
			}
		}else {
			throw new BizException("Accès refusé pour supprimer un produit.");
	    }
	}
	
	public int deleteProduit(AuthentificationBean user, ProduitBean pb, Connexion con) throws BizException {
		if (user.hasPermissionToDeleteProduct()) {
			int result = 0;
			Produit pr = produitBeanToProduit(pb);
			try {
				System.out.println("suppression produit = " + ProduitDb.deleteByKey(user, con, pr));
				return result;
			} catch (BizException be) {
				be.printStackTrace();
				throw be;
			}
			
		}else {
			throw new BizException("Accès refusé pour supprimer un produit.");
	    }
	}
	
	public int updateProduit(AuthentificationBean user, ProduitBean pb) throws BizException {
		if (user.hasPermissionToUpdateProduct()) {
			int result;
			Connexion con = new Connexion();			
			try {
				con.beginTransaction();
				result = updateProduit(user, pb, con);
				con.endTransaction();
				return result;
			} catch (BizException be) {
				con.rollBack();
				be.printStackTrace();
				throw be;
			}
		}else {
			throw new BizException("Accès refusé pour mettre à jour un produit.");
	    }
		
	}
	
	public int updateProduit(AuthentificationBean user, ProduitBean pb, Connexion con) throws BizException {
		if (user.hasPermissionToUpdateProduct()) {
			int result = 0;
			Produit p = produitBeanToProduit(pb);
			try {
				System.out.println("modification produit = " + ProduitDb.updateByKey(user, con, p));
				return result;
			} catch (BizException be) {
				be.printStackTrace();
				throw be;
			}
		}else {
			throw new BizException("Accès refusé pour mettre à jour un produit.");
	    }
	}
	
	public int insertProduit(AuthentificationBean user, ProduitBean pb) throws BizException {
		if (user.hasPermissionToCreateProduct()) {
			int result;
			Connexion con = new Connexion();
			
			try {
				con.beginTransaction();
				result = insertProduit(user, pb, con);
				con.endTransaction();
				return result;
			} catch (BizException be) {
				con.rollBack();
				be.printStackTrace();
				throw be;
			}
		}else {
			throw new BizException("Accès refusé pour créer un produit.");
        }
	}
	
	public int insertProduit(AuthentificationBean user, ProduitBean pb, Connexion con) throws BizException {
		if (user.hasPermissionToCreateProduct()) {
			int result = 0;
			Produit p = produitBeanToProduit(pb);
			try {
				System.out.println("ajout produit = " + ProduitDb.insert(user, con, p));
				return result;
			} catch (BizException be) {
				be.printStackTrace();
				throw be;
			}
		}else {
			throw new BizException("Accès refusé pour créer un produit.");
        }
	}
	
	
	public ProduitBean getProduitByKey(AuthentificationBean user, ProduitBean pb) throws BizException {
		if (user.hasPermissionToReadProduct()) {
			Connexion con = new Connexion();
			
			try {
				con.beginTransaction();
				ProduitBean result = getProduitByKey(user, pb, con);
				con.endTransaction();
				return result;
			} catch (BizException be) {
				con.rollBack();
				be.printStackTrace();
				throw be;
			}
		}else {
			throw new BizException("Accès refusé pour afficher un produit.");
        }
	}
	
	public ProduitBean getProduitByKey(AuthentificationBean user, ProduitBean pb, Connexion con) throws BizException {
		if (user.hasPermissionToReadProduct()) {
			Produit p = produitBeanToProduit(pb);
			try {
				Produit pNew = ProduitDb.getByKey(user, con, p);
				System.out.println("le produit demandé = " + pNew);
				return produitToProduitBean(pNew);
			} catch (BizException be) {
				be.printStackTrace();
				throw be;
			}
		}else {
			throw new BizException("Accès refusé pour afficher un produit.");
        }
	}
	
	
	
	private Produit produitBeanToProduit(ProduitBean pb) {
		Produit result = new Produit();
		result.setCodeProduit(pb.getCodeProduit());
		result.setLibelleProduit(pb.getLibelleProduit());
		result.setDescription(pb.getDescription());
		result.setPrix(pb.getPrix());
		result.setStimestamp(pb.getStimestamp());
		return result;
	}
	
	private ProduitBean produitToProduitBean(Produit p) {
		ProduitBean result = new ProduitBean();
		result.setCodeProduit(p.getCodeProduit());
		result.setLibelleProduit(p.getLibelleProduit());
		result.setDescription(p.getDescription());
		result.setPrix(p.getPrix());
		result.setStimestamp(p.getStimestamp());
		return result;
	}
}
