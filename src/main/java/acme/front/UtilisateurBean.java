package acme.front;

public class UtilisateurBean extends AbstractBean {
	
	private String CODE_ROLE, LIBELLE_ROLE;
	private String LOGIN, PSW, CODE_ROLE;

	public UtilisateurBean() {
		// TODO Auto-generated constructor stub
	}

	public String getCODE_ROLE() {
		return CODE_ROLE;
	}

	public void setCODE_ROLE(String cODE_ROLE) {
		CODE_ROLE = cODE_ROLE;
	}

	public String getLIBELLE_ROLE() {
		return LIBELLE_ROLE;
	}

	public void setLIBELLE_ROLE(String lIBELLE_ROLE) {
		LIBELLE_ROLE = lIBELLE_ROLE;
	}

	public String getLOGIN() {
		return LOGIN;
	}

	public void setLOGIN(String lOGIN) {
		LOGIN = lOGIN;
	}

	public String getPSW() {
		return PSW;
	}

	public void setPSW(String pSW) {
		PSW = pSW;
	}

	@Override
	public String toString() {
		return "UtilisateurBean [CODE_ROLE=" + CODE_ROLE + ", LIBELLE_ROLE=" + LIBELLE_ROLE + ", LOGIN=" + LOGIN
				+ ", PSW=" + PSW + "]";
	}
	
	

}
