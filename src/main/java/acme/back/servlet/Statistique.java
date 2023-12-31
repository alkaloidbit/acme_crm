package acme.back.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import acme.util.Connexion;

/**
 * Servlet implementation class Statistique
 */
public class Statistique extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static PreparedStatement stmt;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Statistique() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			if ("cabyyear".equals(request.getParameter("stat"))) {
				Connexion c = new Connexion();
				stmt = c.getConnection().prepareStatement(
						"select YEAR(c.`DATE`) as year, sum(p.PRIX*dc.quantite) as ca from produit p " +
								" inner join detail_commande dc " +
								" on dc.CODE_PRODUIT = p.CODE_PRODUIT" +
								" inner join commande c" +
								" on c.ID_COMMANDE = dc.ID_COMMANDE" +
								" GROUP BY YEAR(c.`DATE`)",
									ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_UPDATABLE);

				String json = "[";
				ResultSet rs = stmt.executeQuery();
				rs.beforeFirst();
				while (rs.next()) {
					json += "{year: " + rs.getString(1) + ", ca: " + rs.getFloat(2) + "}" ;
				}
				json += "]";

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(json);
			}


			request.setAttribute("page_name", "Stats");
			request.setAttribute("page_content", "statTemplate");
			this.getServletContext().getRequestDispatcher("/jsp/stat.jsp").forward(request, response);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
