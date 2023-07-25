package acme.front;
import java.sql.Timestamp;

public class UtilisateurBean extends AbstractBean {
	
	private String CODE_ROLE, LIBELLE_ROLE;
	private Timestamp STIMESTAMP_ROLE;
	private String LOGIN, PSW;
	private Timestamp STIMESTAMP_UTILISATEUR;
	
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
	
	

	public Timestamp getSTIMESTAMP_ROLE() {
		return STIMESTAMP_ROLE;
	}

	public void setSTIMESTAMP_ROLE(Timestamp sTIMESTAMP_ROLE) {
		STIMESTAMP_ROLE = sTIMESTAMP_ROLE;
	}

	public Timestamp getSTIMESTAMP_UTILISATEUR() {
		return STIMESTAMP_UTILISATEUR;
	}

	public void setSTIMESTAMP_UTILISATEUR(Timestamp sTIMESTAMP_UTILISATEUR) {
		STIMESTAMP_UTILISATEUR = sTIMESTAMP_UTILISATEUR;
	}
	
	
	@Override
	public String toString() {
		return "UtilisateurBean [CODE_ROLE=" + CODE_ROLE + ", LIBELLE_ROLE=" + LIBELLE_ROLE + ", STIMESTAMP_ROLE="
				+ STIMESTAMP_ROLE + ", LOGIN=" + LOGIN + ", PSW=" + PSW + ", STIMESTAMP_UTILISATEUR="
				+ STIMESTAMP_UTILISATEUR + "]";
	}
	
	
	

}
