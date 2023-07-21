package acme.back.servlet;

import java.io.IOException;
import java.util.ArrayList;

import acme.back.service.AuthentificationService;
import acme.back.service.ProduitService;
import acme.front.AuthentificationBean;
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
		
		request.setAttribute("page_name", "Nos Produits");
		
		// PROVISOIRE POUR TEST
		AuthentificationBean ab = new AuthentificationBean();
		ab.setLogin("totobidon");
		ab.setLogin("moulin");
		ab.setCodeRole("ADMIN");
		// FIN PROVISOIRE POUR TEST
		// TRY CATCH A FAIRE
	
		
		HttpSession session = (HttpSession)request.getSession();
		try {
			ProduitBean prodBean = new ProduitService().ProductListBean();
			session.setAttribute("produits", prodBean);
		} catch (BizException e) {
			e.printStackTrace();
		}

		session.removeAttribute("erreur");
		session.setAttribute("authentification", ab);
		request.setAttribute("page_content", "content_datatable_produits");
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
