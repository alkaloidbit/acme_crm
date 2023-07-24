package acme.back.service;

import java.util.ArrayList;


import acme.back.db.ProduitDb;
import acme.back.metier.Produit;
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

	
	public ArrayList<ProduitBean> ProductListBean() throws BizException {		

		Connexion con = new Connexion();
		ArrayList<ProduitBean> prodBeans = new ArrayList<ProduitBean>();
		try {
			ArrayList<Produit> productList = ProduitDb.getAll(con);
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
	
	public int deleteProduit(ProduitBean pb) throws BizException {
		
		int result;
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			result = deleteProduit(pb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			be.printStackTrace();
			throw be;
		}
	}
	
	public int deleteProduit(ProduitBean pb, Connexion con) throws BizException {
		
		int result = 0;
		Produit pr = produitBeanToProduit(pb);
		try {
			System.out.println("suppression produit = " + ProduitDb.deleteByKey(con, pr));
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	
	public int updateProduit(ProduitBean pb) throws BizException {
		
		int result;
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			result = updateProduit(pb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			be.printStackTrace();
			throw be;
		}
	}
	
	public int updateProduit(ProduitBean pb, Connexion con) throws BizException {
		
		int result = 0;
		Produit p = produitBeanToProduit(pb);
		try {
			System.out.println("modification produit = " + ProduitDb.updateByKey(con, p));
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	
	public int insertProduit(ProduitBean pb) throws BizException {
		
		int result;
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			result = insertProduit(pb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			be.printStackTrace();
			throw be;
		}
	}
	
	public int insertProduit(ProduitBean pb, Connexion con) throws BizException {
		
		int result = 0;
		Produit p = produitBeanToProduit(pb);
		try {
			System.out.println("ajout produit = " + ProduitDb.insert(con, p));
			return result;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}
	}
	
	
	public ProduitBean getProduitByKey(ProduitBean pb) throws BizException {
		
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			ProduitBean result = getProduitByKey(pb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			be.printStackTrace();
			throw be;
		}
	}
	
	public ProduitBean getProduitByKey(ProduitBean pb, Connexion con) throws BizException {
		
		Produit p = produitBeanToProduit(pb);
		try {
			Produit pNew = ProduitDb.getByKey(con, p);
			System.out.println("le produit demandé = " + pNew);
			return produitToProduitBean(pNew);
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
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
