package acme.back.servlet;

import acme.front.ClientBean;
import acme.util.BizException;
import acme.back.service.ClientService;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

/**
 * Servlet implementation class Client
 */
public class Client extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = (HttpSession)request.getSession(false);
        if (session == null || session.getAttribute("authentification") == null) {
        	response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
		session.removeAttribute("erreur");
		try {
			if ("detail".equals(request.getParameter("action"))) {
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<ClientBean> clients = (ArrayList<ClientBean>)session.getAttribute("clients");
				session.setAttribute("cbdetail", clients.get(i));
				request.setAttribute("page_name", "Nos clients");
				request.setAttribute("page_content", "clientDetail");
			} else

			if ("creation".equals(request.getParameter("action"))) {
				request.setAttribute("page_name", "Nouveau client");
				request.setAttribute("page_content", "clientForm");

			} else if ("update".equals(request.getParameter("action"))) {
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<ClientBean> clients = (ArrayList<ClientBean>)session.getAttribute("clients");
				session.setAttribute("cbedit", clients.get(i));
				request.setAttribute("page_name", "Edition client");
				request.setAttribute("page_content", "clientUpdateForm");

			} else {
				ArrayList<ClientBean> clients = ClientService.getService().getAllClients();
				session.setAttribute("clients", clients);
				request.setAttribute("page_name", "Nos clients");
				request.setAttribute("page_content", "clientTable");
			}

			this.getServletContext().getRequestDispatcher("/jsp/client.jsp").forward(request, response);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			HttpSession session = (HttpSession) request.getSession();
		try {
			if ("update".equals(request.getParameter("action"))) {

				ClientBean cb = new ClientBean();
				cb.setCodeClient(request.getParameter("code_client"));
				cb.setNom(request.getParameter("nom"));
				cb.setPrenom(request.getParameter("prenom"));
				cb.setAdresse(request.getParameter("adresse"));
				cb.setCodePostal(request.getParameter("code_postal"));
				cb.setVille(request.getParameter("ville"));
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				cb.setStimestamp(timestamp);
				System.out.println(cb);
				int res = ClientService.getService().updateClient(cb);

				if (res == 1) {
					request.setAttribute("msg", "Mise a jour Effectuée !");
				} else {
					request.setAttribute("error", "Probleme durant la Mise a jour");
				}

				System.out.println("res :" + res);
				ArrayList<ClientBean> clients = ClientService.getService().getAllClients();
				session.setAttribute("clients", clients);
				request.setAttribute("page_name", "Nos clients");
				request.setAttribute("page_content", "clientTable");
			} else {

				ClientBean cb = new ClientBean();
				cb.setCodeClient(request.getParameter("code_client"));
				cb.setNom(request.getParameter("nom"));
				cb.setPrenom(request.getParameter("prenom"));
				cb.setAdresse(request.getParameter("adresse"));
				cb.setCodePostal(request.getParameter("code_postal"));
				cb.setVille(request.getParameter("ville"));
				System.out.println(cb);
				int res = ClientService.getService().createClient(cb);

				if (res == 1) {
					request.setAttribute("msg", "Creation Effectuée !");
				} else {
					request.setAttribute("error", "Probleme avec la creation");
				}
				System.out.println("res :" + res);
			}
			ArrayList<ClientBean> clients = ClientService.getService().getAllClients();
			session.setAttribute("clients", clients);
			request.setAttribute("page_name", "Nos clients");
			request.setAttribute("page_content", "clientTable");
			this.getServletContext().getRequestDispatcher("/jsp/client.jsp").forward(request, response);
		} catch (BizException be) {
			try {
				be.printStackTrace();
				session.setAttribute("erreur", be.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session = (HttpSession) request.getSession();
		try {
				int i = Integer.parseInt(request.getParameter("valeur"));
				System.out.println("client:" + i);
				ArrayList<ClientBean> clients = (ArrayList<ClientBean>)session.getAttribute("clients");
				ClientBean cb = clients.get(i);

				int res = ClientService.getService().deleteClient(cb);
				System.out.println("res" + res);
				String resultat = "success";

				if (res == 0) {
					resultat = "error";
				}

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");

				response.getWriter().write("{resultat:"+ resultat +"}");
		} catch (BizException be) {
			try {
				be.printStackTrace();
				session.setAttribute("erreur", be.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
