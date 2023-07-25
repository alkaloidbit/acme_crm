package acme.back.servlet;

import java.io.IOException;
import java.util.ArrayList;

import acme.back.service.CommandeService;
import acme.back.service.ProduitService;
import acme.front.AuthentificationBean;
import acme.front.CommandeBean;
import acme.front.DetailCommandeBean;
import acme.front.ProduitBean;
import acme.util.BizException;
import acme.util.Utilitaire;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Commande extends HttpServlet {
	
	private static final long serialVersionUID = 0L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = (HttpSession)request.getSession();
		session.removeAttribute("erreur");
		String pageApresErreur = "/jsp/erreur.jsp";
		try {
			//Bouton rechercher
			if ("Rechercher".equals(request.getParameter("rechercher")))  {
				System.out.println("Rechercher");
				CommandeBean cb = (CommandeBean)session.getAttribute("cb");
				//idCommande
				String d = (String)request.getParameter("idCommande");
				if (d == null || d == "") { cb.setIdCommande(0); }
				else { cb.setIdCommande(Integer.parseInt(d)); }
				//Client
				d = (String)request.getParameter("nom");
				if (d == null || d == "") { cb.setNomClient("%"); }
				else { cb.setNomClient(d); }
				//dateCommande
				d = (String)request.getParameter("dateCommande");
				if (d != null && d != "") { cb.setDateCommande(Utilitaire.getDateAmericaine(d)); }
				else { cb.setDateCommande(null); }
				session.setAttribute("cb", cb);
				ArrayList<CommandeBean> commandes = CommandeService.getService().search(cb);
				session.setAttribute("commandes", commandes);
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commande.jsp").forward(request, response);
			} else
			//Bouton Détail
			if ("detail".equals(request.getParameter("parametre"))) {
				System.out.println("Détail");
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<CommandeBean> commandes = (ArrayList<CommandeBean>)session.getAttribute("commandes");
				session.setAttribute("cb", commandes.get(i));
				session.setAttribute("cdbs", commandes.get(i).getAl());
				request.setAttribute("page_content", "detailCommandeTable");
				request.setAttribute("page_button", "detailCommandeButton");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/detailCommande.jsp").forward(request, response);
			} else
			//Bouton Suppression
			if ("supprimer".equals(request.getParameter("parametre"))) {
				System.out.println("Supprimer");
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<CommandeBean> commandes = (ArrayList<CommandeBean>)session.getAttribute("commandes");
				CommandeBean cb = commandes.get(i); 
				CommandeService.getService().deleteCommande(cb);
				commandes.remove(i);
				request.setAttribute("page_content", "commandeTable");
				request.setAttribute("page_button", "creerCommandeButton");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commande.jsp").forward(request, response);
			} else
			//Bouton Retour
			if ("retour".equals(request.getParameter("parametre"))) {
				System.out.println("retour");
				request.setAttribute("page_content", "commandeTable");
				request.setAttribute("page_button", "creerCommandeButton");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commande.jsp").forward(request, response);
			} else
			//Bouton Creer
			if ("creer".equals(request.getParameter("parametre")))  {
				System.out.println("Créer");
				request.setAttribute("page_content", "commandeCreer");
				AuthentificationBean ab = (AuthentificationBean) session.getAttribute("authentification");
				ArrayList<ProduitBean> produits = ProduitService.getService().ProductListBean(ab);
				session.setAttribute("produits", produits);
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commandeCreer.jsp").forward(request, response);
			} else
			
			//Bouton Annuler
			if ("annuler".equals(request.getParameter("parametre")))  {
				System.out.println("Annuler");
				request.setAttribute("page_content", "commandeTable");
				request.setAttribute("page_button", "creerCommandeButton");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commandeCreer.jsp").forward(request, response);
			} else
			//Bouton modifier
			if ("modifier".equals(request.getParameter("parametre")))  {
				System.out.println("modifier");
				ArrayList<ProduitBean> produits = ProduitService.getService().ProductListBean();
				session.setAttribute("produits", produits);
				int i = Integer.parseInt(request.getParameter("valeur"));
				System.out.println("Choix commande="+i);
				ArrayList<CommandeBean> commandes = (ArrayList<CommandeBean>)session.getAttribute("commandes");
				System.out.println("Commande="+commandes.get(i));
				session.setAttribute("cb", commandes.get(i));
				request.setAttribute("page_content", "commandeModifier");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commandeCreer.jsp").forward(request, response);
			} else
			//Bouton modifier/enregistrer une commande
			if ("enregistrer".equals(request.getParameter("parametre")) && "commandeModifier".equals(request.getParameter("page")))  {
				System.out.println("enregistrer modification");
				pageApresErreur = "/jsp/blanc.jsp";
				request.setAttribute("page_content", "commandeModifier");
				request.setAttribute("page_button", "enregistrerCommandeButton");
				CommandeBean cbid = (CommandeBean) session.getAttribute("cb"); 
				CommandeBean cb = new CommandeBean(); 
				String d = (String)request.getParameter("codeClient");
				System.out.println("codeclient="+d);
				cb.setCodeClient(d);
				d = (String)request.getParameter("dateCommande");
				System.out.println("datecommande="+d);
				cb.setDateCommande(Utilitaire.getDateAmericaineSansTiret(d));
				//d = (String)request.getParameter("idCommande");
				System.out.println("idCommande="+cbid.getIdCommande());
				cb.setIdCommande(cbid.getIdCommande());
				ArrayList<ProduitBean> produits = (ArrayList<ProduitBean>)session.getAttribute("produits");
				for (int i=0; i<produits.size(); i++) {
					d = (String)request.getParameter("box"+i);
					System.out.println("box"+i +"="+d);
					if (d != null) {
						d = (String)request.getParameter("qte"+i);
						System.out.println("qte"+i +"="+d);
						DetailCommandeBean dcb = new DetailCommandeBean();
						dcb.setCodeProduit(produits.get(i).getCodeProduit());
						dcb.setLibelleProduit(produits.get(i).getLibelleProduit());
						dcb.setQuantite(Integer.parseInt(d));
						dcb.setMontant(Integer.parseInt(d) * produits.get(i).getPrix());
						cb.addDetailCommandeBean(dcb);
					}
				}
				System.out.println(cb);
				CommandeService.getService().updateCommande(cb);
				cb.setDateCommande(null);
				cb.setIdCommande(0);
				cb.setNomClient("%");
				ArrayList<CommandeBean> commandes = CommandeService.getService().search(cb);
				session.setAttribute("commandes", commandes);
				cb.setNomClient("");
				session.setAttribute("cb", cb);
				request.setAttribute("page_content", "commandeTable");
				request.setAttribute("page_button", "creerCommandeButton");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commandeCreer.jsp").forward(request, response);
			} else
			//Bouton Enregistrer
			if ("enregistrer".equals(request.getParameter("parametre")))  {
				System.out.println("enregistrer");
				pageApresErreur = "/jsp/blanc.jsp";
				request.setAttribute("page_content", "commandeCreer");
				request.setAttribute("page_button", "enregistrerCommandeButton");
				CommandeBean cb = new CommandeBean(); 
				String d = (String)request.getParameter("codeClient");
				System.out.println("codeclient"+d);
				cb.setCodeClient(d);
				d = (String)request.getParameter("dateCommande");
				System.out.println("datecommande"+d);
				cb.setDateCommande(Utilitaire.getDateAmericaineSansTiret(d));
				ArrayList<ProduitBean> produits = (ArrayList<ProduitBean>)session.getAttribute("produits");
				for (int i=0; i<produits.size(); i++) {
					d = (String)request.getParameter("box"+i);
					System.out.println("box"+i +"="+d);
					if (d != null) {
						d = (String)request.getParameter("qte"+i);
						System.out.println("qte"+i +"="+d);
						DetailCommandeBean dcb = new DetailCommandeBean();
						dcb.setCodeProduit(produits.get(i).getCodeProduit());
						dcb.setLibelleProduit(produits.get(i).getLibelleProduit());
						dcb.setQuantite(Integer.parseInt(d));
						dcb.setMontant(Integer.parseInt(d) * produits.get(i).getPrix());
						cb.addDetailCommandeBean(dcb);
					}
				}
				CommandeService.getService().insertCommande(cb);
				CommandeBean cbn = new CommandeBean();
				cb.setDateCommande(null);
				cb.setIdCommande(0);
				cb.setNomClient("%");
				ArrayList<CommandeBean> commandes = CommandeService.getService().search(cb);
				session.setAttribute("commandes", commandes);
				cb.setNomClient("");
				session.setAttribute("cb", cb);
				request.setAttribute("page_content", "commandeTable");
				request.setAttribute("page_button", "creerCommandeButton");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commandeCreer.jsp").forward(request, response);
			} else
			//Je viens du menu commande
			if ("menuCommande".equals(request.getParameter("parametre"))) {
				System.out.println("Menucommande");
				CommandeBean cb = new CommandeBean();
				cb.setDateCommande(null);
				cb.setIdCommande(0);
				cb.setNomClient("%");
				ArrayList<CommandeBean> commandes = CommandeService.getService().search(cb);
				session.setAttribute("commandes", commandes);
				cb.setNomClient("");
				session.setAttribute("cb", cb);
				request.setAttribute("page_content", "commandeTable");
				request.setAttribute("page_button", "creerCommandeButton");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/blanc.jsp").forward(request, response);
				//getServletConfig().getServletContext().getRequestDispatcher("/jsp/commande.jsp").forward(request, response);
			} else {
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/erreur.jsp").forward(request, response);
			}			
		}
		catch(BizException be) {
			try {
				be.printStackTrace();
				//En cas d'erreur on renseigne le message d'erreur dans la session sous forme de string
				session.setAttribute("erreur", be.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(pageApresErreur).forward(request, response);
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
}
