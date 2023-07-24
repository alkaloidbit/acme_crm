package acme.back.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.sql.Timestamp;

import acme.back.service.CommandeService;
import acme.back.service.ProduitService;
import acme.util.Utilitaire;
import acme.front.CommandeBean;
import acme.front.ProduitBean;
import acme.util.BizException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class ProduitModif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProduitModif() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choice = request.getParameter("choice");
		HttpSession session = (HttpSession)request.getSession();
		session.setAttribute("choice", choice);
		ArrayList<ProduitBean> produitbeans = (ArrayList<ProduitBean>) session.getAttribute("produits");
		// Ajout
		if(choice.equals("create")) {
				request.setAttribute("page_name", "Ajout de produit");
				request.setAttribute("page_content", "produit_ajout");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
		}
		else {		
			int index = Integer.parseInt(request.getParameter("index"));
			if (index>=0 && index < produitbeans.size()) {
				ProduitBean produitBean = produitbeans.get(index);			
				session.setAttribute("produitBean", produitBean);
				
				// Mise à jour
				if(choice.equals("update")) {

					request.setAttribute("page_name", "Modification de produit");
					request.setAttribute("page_content", "produit_modif");
					getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
	
				}
				// Suppression
				else if(choice.equals("delete")) {
					ProduitService ps = ProduitService.getService();
					try {
						int result = ps.deleteProduit(produitBean);	
							request.setAttribute("errorMessage", "");
							produitbeans.remove(index);
							request.setAttribute("page_content", "content_datatable_produits");
							// session.setAttribute("page_name", "Suppression de produit");
							// getServletConfig().getServletContext().getRequestDispatcher("/jsp/produit_modif.jsp").forward(request, response);
							getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
	
					} catch (BizException e) {
						e.printStackTrace();
						String errorMessage = "Suppression impossible : vérifiez que le produit n'est pas utilisé dans une commande ou qu'il n'a pas été modifié récemment";
						request.setAttribute("errorMessage", errorMessage);
						request.setAttribute("page_content", "content_datatable_produits");
						getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
					}
					
					
					request.setAttribute("page_content", "content_datatable_produits");
					// session.setAttribute("page_name", "Suppression de produit");
					// getServletConfig().getServletContext().getRequestDispatcher("/jsp/produit_modif.jsp").forward(request, response);
					getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
				}
				
				else {
					request.setAttribute("page_content", "content_datatable_produits");
					getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
				}
			}
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = (HttpSession)request.getSession();
		ArrayList<ProduitBean> produitbeans = (ArrayList<ProduitBean>) session.getAttribute("produits");
		String code_produit = request.getParameter("code_produit");
		String libelle_produit = request.getParameter("libelle_produit");
		String description = request.getParameter("description");
		String prixStr = request.getParameter("prix");
		String tsStr = request.getParameter("timestamp");
		
		
		ProduitService ps = ProduitService.getService();
		
		String choice = (String) request.getParameter("request_type");
		// Mise à jour
		if(choice.equals("update")){
			ProduitBean pb = createProduitBeanFromInputs(code_produit, libelle_produit, description, prixStr, tsStr, "update", request, response);
			try {
				ps.updateProduit(pb);
				// modification dans la liste du tableau affiché
				String codeProd = pb.getCodeProduit();
		        for (ProduitBean prodb : produitbeans) {
		            if (prodb.getCodeProduit().equals(codeProd)) {
		            	ProduitBean pbUpdated = ProduitService.getService().getProduitByKey(pb);
		            	prodb.setCodeProduit(pbUpdated.getCodeProduit());
		            	prodb.setLibelleProduit(pbUpdated.getLibelleProduit());
		            	prodb.setDescription(pbUpdated.getDescription());
		            	prodb.setPrix(pbUpdated.getPrix());
		            	prodb.setStimestamp(pbUpdated.getStimestamp());            	
		                break;
		            }
		        }
				
				request.setAttribute("page_content", "content_datatable_produits");
				session.setAttribute("page_name", "Table produits");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
			} catch (BizException e) {
				e.printStackTrace();
				String errorMessage = "Vérifiez que les champs sont correctement renseignés et qu'il n'a pas été modifié récemment";
				request.setAttribute("errorMessage", errorMessage);
				request.setAttribute("page_content", "content_datatable_produits");
				request.setAttribute("page_name", "Table produits");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
			}
		}
		// Ajout
		else if(choice.equals("create")) {
			ProduitBean pb = createProduitBeanFromInputs(code_produit, libelle_produit, description, prixStr, tsStr, "create", request, response);
			try {
				// Ajout à la base de données
				ps.insertProduit(pb);
				// Récupération du produit ajouté à la base; ce qui permettra d'obtenir le timestamp
				ProduitBean pbUpdated = ProduitService.getService().getProduitByKey(pb);
				// Ajout à la liste des produits affichése
				produitbeans.add(pbUpdated);
				request.setAttribute("page_content", "content_datatable_produits");
				session.setAttribute("page_name", "Table produits");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
			} catch (BizException e) {
				e.printStackTrace();
				String errorMessage = "Veuillez vérifier que les champs sont correctement renseignés et que le code produit n'est pas déjà utilisé par ailleurs";
				request.setAttribute("errorMessage", errorMessage);
				request.setAttribute("page_name", "Table produits");
				request.setAttribute("page_content", "content_datatable_produits");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
			}
			
		}
		// Suppression
		else if(choice.equals("delete")) {
			// TODO (évolution possible)
			
		}
		
	}
	
    private ProduitBean createProduitBeanFromInputs(String codeProduit, String libelleProduit, 
    		String description, String prixStr, String timestampStr, String requestType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Vérification des inputs
    	
    	// Si un champ est vide, on ne peut pas créer l'objet ProduitBean
        if (codeProduit == null || codeProduit.isEmpty()
            || libelleProduit == null || libelleProduit.isEmpty()
            || description == null || description.isEmpty()
            || prixStr == null || prixStr.isEmpty()) {
            request.setAttribute("errorMessage", "Tous les champs doivent être renseignés.");
			request.setAttribute("page_content", "produit_modif");
            request.getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
            return null; 
        }
        if(requestType.equals("update")) {
        	if (timestampStr == null || timestampStr.isEmpty()) {
                request.setAttribute("errorMessage", "Date de création invalide");

    			request.setAttribute("page_content", "produit_modif");
                request.getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
        		return null; 
        	}
        }
        // Echappement des caractères douteux
        codeProduit = echapInput(codeProduit);
        libelleProduit = echapInput(libelleProduit);
        description = echapInput(description);
        prixStr = echapInput(prixStr);
        if(requestType.equals("update")) {
            timestampStr = echapInput(timestampStr);
        }


        try {
            float prix = Float.parseFloat(prixStr);
            Timestamp timestamp = new Timestamp(0);
            if(requestType.equals("update")) {
            	timestamp = Timestamp.valueOf(timestampStr);
            }            
            ProduitBean produit = new ProduitBean();
            produit.setCodeProduit(codeProduit);
            produit.setLibelleProduit(libelleProduit);
            produit.setDescription(description);
            produit.setPrix(prix);
            if(requestType.equals("update")) {
            	produit.setStimestamp(timestamp);
            }      

            return produit;
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Veuillez vérifier que le champs prix est bien renseigné");
			request.setAttribute("page_content", "produit_modif");
            request.getRequestDispatcher("/jsp/produits.jsp").forward(request, response);

            return null;
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Date de création invalide");

			request.setAttribute("page_content", "produit_modif");
            request.getRequestDispatcher("/jsp/produits.jsp").forward(request, response);

            return null;
        }
    }
    private String echapInput(String input) {
        input = input.replace("&", "&amp;")
                     .replace("<", "&lt;")
                     .replace(">", "&gt;")
                     .replace("\"", "&quot;")
                     .replace("'", "&#39;");

        return input;
    }
}
