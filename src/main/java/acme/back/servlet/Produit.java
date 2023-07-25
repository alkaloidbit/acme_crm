package acme.back.servlet;

import java.io.IOException;
import java.util.ArrayList;

import acme.back.service.AuthentificationService;
import acme.back.service.ClientService;
import acme.back.service.CommandeService;
import acme.back.service.ProduitService;
import acme.front.AuthentificationBean;
import acme.front.ClientBean;
import acme.front.CommandeBean;
import acme.front.ProduitBean;
import acme.util.BizException;
import acme.util.Utilitaire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Produit
 */

public class Produit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Produit() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	
		HttpSession session = (HttpSession)request.getSession();
		try {
			// Récuperer l'utilisateur loggé
			AuthentificationBean ab = (AuthentificationBean) session.getAttribute("authentification");
			// Récupération liste des produits
			ArrayList<ProduitBean> prodBeans = new ProduitService().ProductListBean(ab);
			session.setAttribute("produits", prodBeans);
		} catch (BizException e) {
			e.printStackTrace();
		}

		session.removeAttribute("erreur");
		request.setAttribute("page_name", "Nos Produits");
		request.setAttribute("page_content", "content_datatable_produits");
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
		// Récuperer l'utilisateur loggé
		AuthentificationBean ab = (AuthentificationBean) session.getAttribute("authentification");
        try {
            int i = Integer.parseInt(request.getParameter("index"));
            System.out.println("produit:" + i);
            ArrayList<ProduitBean> produits = (ArrayList<ProduitBean>) session.getAttribute("produits");
            ProduitBean pb = produits.get(i);

            int res;
            try {
                res = ProduitService.getService().deleteProduit(ab, pb);
                System.out.println("res" + res);
            } catch (Exception e) {
                e.printStackTrace();
                res = -1; 
            }

            String resultat;
            if (res == 0) {
                resultat = "success";
            } else {
                resultat = "error";
            }

            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");

            response.getWriter().write("{\"resultat\":\"" + resultat + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("erreur", e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur");
        }
    }
}
