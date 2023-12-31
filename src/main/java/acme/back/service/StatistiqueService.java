package acme.back.service;

import java.util.ArrayList;

import acme.back.db.StatistiqueDb;
import acme.back.metier.Statistique;
import acme.front.StatistiqueBean;
import acme.util.BizException;
import acme.util.Connexion;




public class StatistiqueService {

	private static StatistiqueService singleton = null;

	private StatistiqueService() {}

	public static StatistiqueService getService() {
		if (singleton == null) singleton = new StatistiqueService();
		return singleton;
	}

	public ArrayList<StatistiqueBean> getCaByYear() throws BizException {
		ArrayList<StatistiqueBean> result = new ArrayList<StatistiqueBean>();
		Connexion con = new Connexion();

		try {
			ArrayList<Statistique> stats = StatistiqueDb.GetCaByYear(con);
			for (Statistique stat: stats) {
				Statistique st = new Statistique();
				st.setYear(stat.getYear());
				st.setCa(stat.getCa());
				result.add(st)
				
			}
		} catch (BizException be) {
			be.printStackTrace();
			throw be;
		}


	}

	private StatistiqueBean statistiqueToStatistiqueBean(Statistique s) {
		StatistiqueBean result = new StatistiqueBean();
		result.setYear(s.getYear());
		result.setCa(s.getCa());
		return result;
	}
}
