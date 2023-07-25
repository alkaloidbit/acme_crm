
package acme.back.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StatistiqueDb {
	private static PreparedStatement caByYear;

	public StatistiqueDb(){}

	private static void statementGetCaByYear(Connexion c) throwa BizException {
		caByYear = c.getConnection().prepareStatement(
			"select YEAR(c.`DATE`) as year, sum(p.PRIX*dc.quantite) as ca from produit p
			inner join detail_commande dc
			on dc.CODE_PRODUIT = p.CODE_PRODUIT
			inner join commande c
			on c.ID_COMMANDE = dc.ID_COMMANDE
			GROUP BY YEAR(c.`DATE`)",

			ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_UPDATABLE);
	}

	public static ArrayList GetCaByYear(Connexion c) throws BizException {
		ResultSet rs = null;
		ArrayList result = null;
		try {
			statementGetCaByYear(c);
			rs = caByYear.executeQuery();
			result = new ArrayList();
			rs.beforeFirst();
			while (rs.next()) {
				
			}
		} catch (Exception e) {
			//TODO: handle exception
		}
	}

}
