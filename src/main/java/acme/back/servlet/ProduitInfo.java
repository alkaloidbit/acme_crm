package acme.back.servlet;

import java.io.IOException;
import java.util.ArrayList;

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

		// PROVISOIRE POUR TEST
		// Récupérer le code produit
		HttpSession session = (HttpSession)request.getSession();
		ArrayList<ProduitBean> produits = (ArrayList<ProduitBean>)session.getAttribute("produits");
		String codeProduit = request.getParameter("codeProduit");

		// Récupérer le produit concerné
		
		
        // Envoyer le détails à la jsp
        request.setAttribute("codeProduit", codeProduit);
        
        // Rediriger vers la page
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/produit_details.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

}
