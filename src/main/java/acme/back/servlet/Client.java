package acme.back.servlet;

import acme.front.ClientBean;
import acme.util.BizException;
import acme.back.service.ClientService;

import java.io.IOException;
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
		HttpSession session = (HttpSession) request.getSession();
		session.removeAttribute("erreur");
		try {
			if ("detail".equals(request.getParameter("action"))) {
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<ClientBean> clients = (ArrayList<ClientBean>)session.getAttribute("clients");
				session.setAttribute("cbdetail", clients.get(i));
				request.setAttribute("page_name", "Nos clients");
				request.setAttribute("page_content", "clientDetail");
				this.getServletContext().getRequestDispatcher("/jsp/client.jsp").forward(request, response);
			} else

			if ("suppression".equals(request.getParameter("action"))) {
				int i = Integer.parseInt(request.getParameter("valeur"));
				ArrayList<ClientBean> clients = (ArrayList<ClientBean>)session.getAttribute("clients");
				ClientBean cb = clients.get(i);
				ClientService.getService().deleteClient(cb);
				clients.remove(i);
				request.setAttribute("page_name", "Nos clients");
				request.setAttribute("page_content", "clientTable");
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/client.jsp").forward(request, response);
			} else {

				ArrayList<ClientBean> clients = ClientService.getService().getAllClients();
				session.setAttribute("clients", clients);
				request.setAttribute("page_name", "Nos clients");
				request.setAttribute("page_content", "clientTable");
				this.getServletContext().getRequestDispatcher("/jsp/client.jsp").forward(request, response);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub doGet(request, response);
	}

}
