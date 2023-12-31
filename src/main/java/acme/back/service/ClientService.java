package acme.back.service;

import java.util.ArrayList;

import acme.back.db.ClientDb;
import acme.back.metier.Client;
import acme.front.ClientBean;
import acme.util.BizException;
import acme.util.Connexion;

public class ClientService {

	private static ClientService singleton = null;

	private ClientService() {}

	public static ClientService getService() {
		if (singleton == null) singleton = new ClientService();
		return singleton;
	}

	public ArrayList<ClientBean> getAllClients() throws BizException {

		ArrayList<ClientBean> result;
		Connexion con = new Connexion();

		try {
			result = getAllClient(con);
			// System.out.println(result);
			con.close();
			return result;
		} catch (BizException be) {
			con.close();
			throw be;
		}
	}

	public ArrayList<ClientBean> getAllClient(Connexion con) throws BizException {

			ArrayList<ClientBean> result = new ArrayList<ClientBean>();

			try {
				ArrayList<Client> clients = ClientDb.getAll(con);
				for (Client client : clients) {
					Client cl = new Client();
					cl.setCodeClient(client.getCodeClient());
					cl.setNom(client.getNom());
					cl.setPrenom(client.getPrenom());
					cl.setAdresse(client.getAdresse());
					cl.setCodePostal(client.getCodePostal());
					cl.setVille(client.getVille());
					cl.setStimestamp(client.getStimestamp());
					cl = cl.select(con);
					result.add(clientToClientBean(cl));
				}
				return result;
			} catch (BizException be) {
				be.printStackTrace();
				throw be;
			}
		}

	public int updateClient(ClientBean cb) throws BizException {
			int result;
			Connexion con = new Connexion();

			try {
				con.beginTransaction();
				result = updateClient(cb, con);
				con.endTransaction();
				return result;
			} catch (Exception be) {
				con.rollBack();
				be.printStackTrace();
				throw be;
			}

		}

	public int updateClient(ClientBean cb, Connexion con) throws BizException {

			int result = 0;
			Client cl = clientBeanToClient(cb);
			try {
				result = ClientDb.updateByKey(con, cl);
				return result;
			} catch (BizException be) {
				be.printStackTrace();
				throw be;
			}

		}
		
	public int createClient(ClientBean cb) throws BizException {

			int result;
			Connexion con = new Connexion();

			try {
				con.beginTransaction();
				result = createClient(cb, con);
				con.endTransaction();
				return result;
			} catch (Exception be) {
				con.rollBack();
				be.printStackTrace();
				throw be;
			}
	}

	public int createClient(ClientBean cb, Connexion con) throws BizException {

			int result = 0;
			Client cl = clientBeanToClient(cb);
			try {
				result = ClientDb.insert(con, cl);
				return result;
			} catch (BizException be) {
				be.printStackTrace();
				throw be;
			}

	}

	public int deleteClient(ClientBean cb) throws BizException {
			
		int result;
		Connexion con = new Connexion();
		
		try {
			con.beginTransaction();
			result = deleteClient(cb, con);
			con.endTransaction();
			return result;
		} catch (BizException be) {
			con.rollBack();
			throw be;
		}
	}
	public int deleteClient(ClientBean cb, Connexion con) throws BizException {
		
		int result = 0;
		System.out.println(cb);
		Client cm = clientBeanToClient(cb);
		try {
			result = ClientDb.deleteByKey(con, cm);
			return result;
		} catch (BizException be) {
			throw be;
		}
	}
	private Client clientBeanToClient(ClientBean cb) {
		Client result = new Client();
		result.setCodeClient(cb.getCodeClient());
		result.setNom(cb.getNom());
		result.setPrenom(cb.getPrenom());
		result.setAdresse(cb.getAdresse());
		result.setVille(cb.getVille());
		result.setCodePostal(cb.getCodePostal());
		result.setStimestamp(cb.getStimestamp());
		

		return result;
	}
	private ClientBean clientToClientBean(Client c) {
		ClientBean result = new ClientBean();
		result.setCodeClient(c.getCodeClient());
		result.setNom(c.getNom());
		result.setPrenom(c.getPrenom());
		result.setAdresse(c.getAdresse());
		result.setVille(c.getVille());
		result.setCodePostal(c.getCodePostal());
		result.setStimestamp(c.getStimestamp());
		return result;
	}
}
