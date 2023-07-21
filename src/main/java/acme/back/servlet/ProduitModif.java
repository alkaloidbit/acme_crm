package acme.back.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ProduitModif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProduitModif() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PROVISOIRE POUR TEST
		// Récupérer les valeurs envoyées
		String codeProduit = request.getParameter("codeProduit");
        String libelleProduit = request.getParameter("libelleProduit");
        String description = request.getParameter("description");
        String choix = request.getParameter("choix"); 

        // Les envoyer vers la jsp
        request.setAttribute("codeProduit", codeProduit);
        request.setAttribute("libelleProduit", libelleProduit);
        request.setAttribute("description", description);
        request.setAttribute("choix", choix);
        
        // Rediriger vers la page
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/produit_modif.jsp").forward(request, response);
	}

}
