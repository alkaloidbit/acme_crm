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
		int index = Integer.parseInt(request.getParameter("index"));
		HttpSession session = (HttpSession)request.getSession();
		ArrayList<ProduitBean> produitbeans = (ArrayList<ProduitBean>)session.getAttribute("produits");
		ProduitBean produitBean = produitbeans.get(index);			
		session.setAttribute("produitBean", produitBean);
		//String codeProduit = request.getParameter("codeProduit");

		// Récupérer le produit concerné
		
		
        // Envoyer le détails à la jsp

		request.setAttribute("page_name", "Nos produits");
<<<<<<< HEAD
		request.setAttribute("page_content", "produit_details");
=======
       
>>>>>>> 507a646 (Update chaine Produit)
        // Rediriger vers la page
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

}
