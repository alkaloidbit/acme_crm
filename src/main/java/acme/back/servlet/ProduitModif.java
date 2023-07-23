package acme.back.servlet;

import java.io.IOException;
import java.util.ArrayList;

import acme.front.ProduitBean;
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

		int index = Integer.parseInt(request.getParameter("index"));
		String choice = request.getParameter("choice");
		HttpSession session = (HttpSession)request.getSession();
		ArrayList<ProduitBean> produitbeans = (ArrayList<ProduitBean>) session.getAttribute("produits");
		ProduitBean produitBean = produitbeans.get(index);
			
		session.setAttribute("produitBean", produitBean);
		session.setAttribute("choice", choice);
        // Rediriger vers la page
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/produit_modif.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
