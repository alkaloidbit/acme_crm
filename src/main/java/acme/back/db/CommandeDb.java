package acme.back.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import acme.back.metier.Commande;
import acme.util.BizException;
import acme.util.Connexion;
import acme.util.Utilitaire;

public class CommandeDb {

	private static PreparedStatement selectAll;
	private static PreparedStatement selectByKey;
	private static PreparedStatement updateByKey;
	private static PreparedStatement insert;
	private static PreparedStatement deleteByKey;
	private static PreparedStatement search;

	public CommandeDb(){}

	private static void statementSelectAll(Connexion c) throws SQLException {
		selectAll = c.getConnection().prepareStatement(
		"SELECT ID_COMMANDE, CODE_CLIENT, DATE, STIMETAMP FROM commande");
	}
	private static void statementSelectByKey(Connexion c) throws SQLException {
		selectByKey = c.getConnection().prepareStatement(
		"SELECT ID_COMMANDE, CODE_CLIENT, DATE, STIMETAMP FROM commande " + 
		"WHERE ID_COMMANDE = ? " ); 
 	}
	private static void statementUpdateByKey(Connexion c) throws SQLException {
		updateByKey = c.getConnection().prepareStatement(
		"UPDATE commande " + 
		"SET 		CODE_CLIENT = ?, " +  
		"DATE = ?, " +  
		"STIMETAMP = ? " + 
		"WHERE ID_COMMANDE = ? "); 
	}
	private static void statementInsert(Connexion c) throws SQLException {
		insert = c.getConnection().prepareStatement(
		"INSERT INTO commande " + 
		"(ID_COMMANDE, CODE_CLIENT, DATE, STIMETAMP) " + 
		"values(?, ?, ?, ?)");
	}
	private static void statementDeleteByKey(Connexion c) throws SQLException {
		deleteByKey = c.getConnection().prepareStatement(
		"DELETE FROM commande " + 
		"WHERE ID_COMMANDE = ? "); 
	}
	private static void statementSearch(Connexion c) throws SQLException {
		search = c.getConnection().prepareStatement(
		"SELECT c.ID_COMMANDE, c.CODE_CLIENT, c.DATE, cl.NOM, p.CODE_PRODUIT, p.LIBELLE_PRODUIT, p.PRIX, dc.QUANTITE, dc.STIMESTAMP "
		+ "FROM commande c, client cl, produit p, detail_commande dc " 
		+ "WHERE c.DATE <= ? " 
		+ "AND c.ID_COMMANDE LIKE ? "
		+ "AND cl.NOM LIKE ? "
		+ "AND c.ID_COMMANDE = dc.ID_COMMANDE "
		+ "AND c.CODE_CLIENT = cl.CODE_CLIENT "
		+ "AND dc.CODE_PRODUIT = p.CODE_PRODUIT "
		+ "ORDER BY c.ID_COMMANDE LIMIT 500");
	}
	public static ArrayList<Commande> search(Connexion c, Commande t) throws BizException {
		ResultSet rs = null;
		ArrayList<Commande> result = null;
		try {
			statementSearch(c);
			if (t.getDate() == null) search.setString(1, Utilitaire.getDateAmericaine(new Date()));
			else search.setString(1, Utilitaire.getDateAmericaine(t.getDate()));
			if (t.getIdCommande() == 0) search.setString(2, "%");
			else search.setString(2, String.valueOf(t.getIdCommande()));
			if (t.getNomClient() == null || "".equals(t.getNomClient())) search.setString(3, "%");
			else search.setString(3, t.getNomClient());
			System.out.println(search);
			rs = search.executeQuery();
			result = new ArrayList<Commande>();
			rs.beforeFirst();
			while (rs.next()) {
				Commande cm = new Commande();
				cm.setIdCommande(rs.getInt(1));
				cm.setCodeClient(rs.getString(2));
				cm.setDate(rs.getDate(3));
				cm.setNomClient(rs.getString(4));
				cm.setCodeProduit(rs.getString(5));
				cm.setLibelleProduit(rs.getString(6));
				cm.setPrix(rs.getDouble(7));
				cm.setQuantite(rs.getInt(8));
				cm.setStimestamp(rs.getTimestamp(9));
				result.add(cm);
			}
			if (rs != null) rs.close();
			return result;
		}
		catch(SQLException sqle) {
			throw new BizException(sqle.getMessage());
		}
	}
	public static int deleteByKey(Connexion c, Commande t) throws BizException {
		ResultSet rs = null;
		int result;
		try {
			statementSelectByKey(c);
			statementDeleteByKey(c);
			selectByKey.setInt(1, t.getIdCommande());
			rs = selectByKey.executeQuery();
			rs.beforeFirst();
			if(rs.next()) {
				if (rs.getTimestamp(4).after(t.getStimestamp())) {
					throw new BizException("Data modified by another user");
				}
			}
			else { throw new BizException("Data modified by another user"); }
			deleteByKey.setInt(1, t.getIdCommande());
			result = deleteByKey.executeUpdate();
			if (rs != null) rs.close();
			return result;
		}
		catch(SQLException sqle) {
			throw new BizException(sqle.getMessage());
		}
	}
	public static int insert(Connexion c, Commande t) throws BizException {
		ResultSet rs = null;
		int result;
		try {
			statementSelectByKey(c);
			statementInsert(c);
			selectByKey.setInt(1, t.getIdCommande());
			rs = selectByKey.executeQuery();
			rs.beforeFirst();
			if (rs.next()) throw new BizException("Data already exists");
			insert.setInt(1, t.getIdCommande());
			insert.setString(2, t.getCodeClient());
			insert.setDate(3, new java.sql.Date(t.getDate().getTime()));
			insert.setTimestamp(4, t.getStimestamp());
			result = insert.executeUpdate();
			if (rs != null) rs.close();
			return result;
		}
		catch(SQLException sqle) {
			throw new BizException(sqle.getMessage());
		}
	}
	public static int updateByKey(Connexion c, Commande t) throws BizException {
		ResultSet rs = null;
		int result;
		try {
			statementSelectByKey(c);
			statementUpdateByKey(c);
			selectByKey.setInt(1, t.getIdCommande());
			rs = selectByKey.executeQuery();
			rs.beforeFirst();
			if(rs.next()) {
				if (rs.getTimestamp(4).after(t.getStimestamp())) {
					throw new BizException("Data modified by another user");
				}
			}
			else { throw new BizException("Data modified by another user"); }
			updateByKey.setString(1, t.getCodeClient());
			updateByKey.setDate(2, new java.sql.Date(t.getDate().getTime()));
			updateByKey.setTimestamp(3, t.getStimestamp());
			updateByKey.setInt(4, t.getIdCommande());
			result = updateByKey.executeUpdate();
			if (rs != null) rs.close();
			return result;
		}
		catch(SQLException sqle) {
			throw new BizException(sqle.getMessage());
		}
	}
	public static Commande getByKey(Connexion c, Commande t) throws BizException {
		ResultSet rs = null;
		Commande result = new Commande();
		try {
			statementSelectByKey(c);
			selectByKey.setInt(1, t.getIdCommande());
			rs = selectByKey.executeQuery();
			rs.beforeFirst();
			if (rs.next()) {
				result.setIdCommande(rs.getInt(1));
				result.setCodeClient(rs.getString(2));
				result.setDate(rs.getDate(3));
				result.setStimestamp(rs.getTimestamp(4));
			}
			if (rs != null) rs.close();
			return result;
		}
		catch(SQLException sqle) {
			throw new BizException(sqle.getMessage());
		}
	}
	public static ArrayList getAll(Connexion c) throws BizException {
		ResultSet rs = null;
		ArrayList result = null;
		try {
			statementSelectAll(c);
			rs = selectAll.executeQuery();
			result = new ArrayList();
			rs.beforeFirst();
			while (rs.next()) {
				Commande t = new Commande();
				t.setIdCommande(rs.getInt(1));
				t.setCodeClient(rs.getString(2));
				t.setDate(rs.getDate(3));
				t.setStimestamp(rs.getTimestamp(4));
				result.add(t);
			}
			if (rs != null) rs.close();
			return result;
		}
		catch(SQLException sqle) {
			throw new BizException(sqle.getMessage());
		}
	}
}
