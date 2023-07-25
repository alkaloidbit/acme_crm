package acme.back.service;

import acme.util.BizException;
import acme.util.Connexion;




public class StatistiqueService {

	private static StatistiqueService singleton = null;

	private StatistiqueService() {}

	public static StatistiqueService getService() {
		if (singleton == null) singleton = new StatistiqueService();
		return singleton;
	}


}
