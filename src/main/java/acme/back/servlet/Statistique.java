package acme.back.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mysql.cj.protocol.x.Ok;

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

	private JSONObject jsonObject;

	private JSONArray array;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Statistique() {
		super();
		jsonObject = new JSONObject();
		array = new JSONArray();
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

				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {
					JSONObject record = new JSONObject();
					//Inserting key-value pairs into the json object
					record.put("year", rs.getLong("year"));
					record.put("ca", rs.getFloat("ca"));
					array.add(record);
				}
				c.close();
				jsonObject.put("status", "ok");
				jsonObject.put("year_ca_data", array);
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");

				response.getWriter().write(jsonObject.toJSONString());
			} else

			if ("orderscount".equals(request.getParameter("stat"))) {
				Connexion c = new Connexion();
				stmt = c.getConnection().prepareStatement("select count(ID_COMMANDE) from commande", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				int records = 0;
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					records = rs.getInt(1); 
				}
				c.close();
					
				jsonObject.put("status", "ok");
				jsonObject.put("orderscount", records);
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");

				response.getWriter().write(jsonObject.toJSONString());
			} else {
				request.setAttribute("page_name", "Stats");
				request.setAttribute("page_content", "statTemplate");
				this.getServletContext().getRequestDispatcher("/jsp/stat.jsp").forward(request, response);
			}
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
