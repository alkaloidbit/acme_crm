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
	public ArrayList<ProduitBean> search(ProduitBean pb) throws BizException {
		
		ArrayList<ProduitBean> result;
		Connexion con = new Connexion();
		
		try {
			result = search(pb, con);
			con.close();
			return result;
		} catch (BizException be) {
			con.close();
			throw be;
		}
	}
	public ArrayList<ProduitBean> search(ProduitBean pb, Connexion con) throws BizException {
		
		ArrayList<ProduitBean> results = new ArrayList<ProduitBean>();
		
		Produit prod = new Produit();			
		
		prod.setCodeProduit(pb.getCodeProduit());
		prod.setLibelleProduit(pb.getLibelleProduit());
		prod.setPrix(pb.getPrix());
		prod.setDescription(pb.getDescription());
		// TO DO
		/*try {
			ArrayList<Produit> produits = ProduitDb.search(con, prod);
			ProduitBean cbt = null;
			int pId = -1; 
			for (int i=0; i<commandes.size(); i++) {
				Commande tmp = commandes.get(i);
				int cId = tmp.getIdCommande();
				if (cId != pId) {
					if (cbt != null) results.add(cbt);
					cbt = new ProduitBean();
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
				if (i == (commandes.size()-1)) results.add(cbt);
				pId = cId;
			}
			return results;
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}*/

		//PROVISOIRE
		return null;
	}
		
	private Produit produitBeanToProduit(ProduitBean pb) {
		Produit result = new Produit();
		result.setCodeProduit(pb.getCodeProduit());
		result.setLibelleProduit(pb.getLibelleProduit());
		result.setDescription(pb.getDescription());
		result.setPrix(pb.getPrix());
		return result;
	}
	
	private ProduitBean produitToProduitBean(Produit p) {
		ProduitBean result = new ProduitBean();
		result.setCodeProduit(p.getCodeProduit());
		result.setLibelleProduit(p.getLibelleProduit());
		result.setDescription(p.getDescription());
		result.setPrix(p.getPrix());
		return result;
	}
	
	
	public ProduitBean ProductListBean() throws BizException {
		
		ProduitBean pb = new ProduitBean();
		ProduitDb prodDb = new ProduitDb();
		Connexion con = new Connexion();
		
		try {
			ArrayList<Produit> ProductList = prodDb.getAll(con);
			pb.setProds(ProductList);
			con.close();
			return pb;
		} catch (BizException be) {
			con.close();
			throw be;
		}


	}
	
	
}
