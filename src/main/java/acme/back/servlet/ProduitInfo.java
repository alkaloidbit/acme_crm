package acme.back.servlet;

import java.io.IOException;
import java.util.ArrayList;

import acme.front.AuthentificationBean;
import acme.front.CommandeBean;
import acme.front.ProduitBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class ProduitInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProduitInfo() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession)request.getSession(false);
        if (session == null || session.getAttribute("authentification") == null) {
        	response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

		// Récuperer l'utilisateur loggé
		AuthentificationBean ab = (AuthentificationBean) session.getAttribute("authentification");
		
		// Récupérer le code produit
		int index = Integer.parseInt(request.getParameter("index"));
		ArrayList<ProduitBean> produitbeans = (ArrayList<ProduitBean>)session.getAttribute("produits");
		ProduitBean produitBean = produitbeans.get(index);			
		session.setAttribute("produitBean", produitBean);

		
        // Envoyer le détails à la jsp

		request.setAttribute("page_name", "Nos produits");
		request.setAttribute("page_content", "produit_details");
        // Rediriger vers la page
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

}
