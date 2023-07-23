package acme.back.servlet;

import java.io.IOException;
import java.util.ArrayList;

import acme.back.service.UtilisateurService;
import acme.front.UtilisateurBean;
import acme.util.BizException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Utilisateur
 */
//@WebServlet("/Utilisateur")

public class Utilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Utilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = (HttpSession) request.getSession();
		session.removeAttribute("erreur");
		try {
			if ("detail".equals(request.getParameter("action"))) {
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<UtilisateurBean> utilisateurs = (ArrayList<UtilisateurBean>)session.getAttribute("utilisateurs");
				session.setAttribute("cbdetail", utilisateurs.get(i));
				request.setAttribute("page_name", "Nos utilisateurs");
				request.setAttribute("page_content", "utilisateurTable");
				this.getServletContext().getRequestDispatcher("/jsp/utilisateur.jsp").forward(request, response);
			} else

			if ("suppression".equals(request.getParameter("action"))) {
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<UtilisateurBean> utilisateurs = (ArrayList<UtilisateurBean>)session.getAttribute("utilisateurs");
				UtilisateurBean cb = utilisateurs.get(i);
				UtilisateurService.getService().deleteUtilisateur(cb);
				utilisateurs.remove(i);
				request.setAttribute("page_name", "Nos Utilisateurs");
				request.setAttribute("page_content", "utilisateurTable");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/utilisateur.jsp").forward(request, response);
			} else {

				ArrayList<UtilisateurBean> utilisateurs = UtilisateurService.getService().getAllUtilisateurs();
				session.setAttribute("utilisateurs", utilisateurs);
				request.setAttribute("page_name", "Nos utilisateurs");
				request.setAttribute("page_content", "utilisateurTable");
				this.getServletContext().getRequestDispatcher("/jsp/user.jsp").forward(request, response);
				System.out.println("Voilà! On est là!!!" + utilisateurs);
			}
		} catch (BizException be) {
			try {
				be.printStackTrace();
				session.setAttribute("erreur", be.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
