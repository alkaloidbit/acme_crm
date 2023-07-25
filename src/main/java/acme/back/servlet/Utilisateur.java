package acme.back.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acme.back.service.UtilisateurService;
import acme.front.UtilisateurBean;
import acme.util.BizException;
import jakarta.servlet.http.HttpSession;

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
		
		System.out.println("Dans HttpServlet#doGet");
		HttpSession session = (HttpSession)request.getSession(false);
        if (session == null || session.getAttribute("authentification") == null) {
        	response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
		session.removeAttribute("erreur");
		try {
			if ("detail".equals(request.getParameter("action"))) {
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<UtilisateurBean> userBeanList = (ArrayList<UtilisateurBean>)session.getAttribute("utilisateurs");
				session.setAttribute("cbdetail", userBeanList.get(i));
				request.setAttribute("page_name", "Nos utilisateurs");
				request.setAttribute("page_content", "clientDetail");
			} else

			if ("creation".equals(request.getParameter("action"))) {
				request.setAttribute("page_name", "Nouveau utilisateur");
				request.setAttribute("page_content", "clientForm");

			} else if ("update".equals(request.getParameter("action"))) {
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<UtilisateurBean> userBeanList = (ArrayList<UtilisateurBean>)session.getAttribute("utilisateurs");
				session.setAttribute("cbedit", userBeanList.get(i));
				request.setAttribute("page_name", "Edition utilisateur");
				request.setAttribute("page_content", "clientUpdateForm");

			} else {
				ArrayList<UtilisateurBean> userBeanList = UtilisateurService.getService().getAllUtilisateurs();
				session.setAttribute("utilisateurs", userBeanList);
				request.setAttribute("page_name", "Nos utilisateurs");
				request.setAttribute("page_content", "utilisateur_details");
				System.out.println("On est là!" + userBeanList);
			}

			this.getServletContext().getRequestDispatcher("/jsp/utilisateur.jsp").forward(request, response);
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
