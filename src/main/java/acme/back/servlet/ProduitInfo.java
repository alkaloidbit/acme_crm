package acme.back.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class ProduitInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProduitInfo() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PROVISOIRE POUR TEST
		// Récupérer le code produit
		String codeProduit = request.getParameter("codeProduit");
		
		// Récupérer le produit concerné

        // Envoyer le détails à la jsp
        request.setAttribute("codeProduit", codeProduit);
        
        // Rediriger vers la page
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/produit_details.jsp").forward(request, response);
	}

}
