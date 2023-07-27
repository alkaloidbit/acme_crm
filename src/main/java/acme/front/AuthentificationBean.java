package acme.front;

@SuppressWarnings("serial")
public class AuthentificationBean extends AbstractBean {

	private String login="";
	private String password="";
	private String codeRole="";
	//private boolean isAuthenticate = Boolean.FALSE;

	public AuthentificationBean() {}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodeRole() {
		return codeRole;
	}

	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}
	/*
	public boolean isAuthenticate() {
		return isAuthenticate;
	}

	public void setAuthenticate(boolean isAuthenticate) {
		this.isAuthenticate = isAuthenticate;
	}*/

	public boolean hasPermissionToReadStats() {
		return "R001".equals(codeRole);
	}

	public boolean hasPermissionToReadUser() {
		return "R001".equals(codeRole);
	}

	public boolean hasPermissionToCreateUser() {
	    return "R001".equals(codeRole);
	}


	public boolean hasPermissionToUpdateUser() {
	    return "R001".equals(codeRole);
	}


	public boolean hasPermissionToDeleteUser() {
	    return "R001".equals(codeRole);
	}

	// ########################################################################################
	public boolean hasPermissionToReadProduct() {
		return "R001".equals(codeRole) || "R002".equals(codeRole) || "R003".equals(codeRole);
	}

	public boolean hasPermissionToCreateProduct() {
	    return "R001".equals(codeRole) || "R002".equals(codeRole);
	}


	public boolean hasPermissionToUpdateProduct() {
	    return "R001".equals(codeRole) || "R002".equals(codeRole);
	}


	public boolean hasPermissionToDeleteProduct() {
	    return "R001".equals(codeRole) || "R002".equals(codeRole);
	}



	@Override
	public String toString() {
		return "AuthentificationBean [login=" + login + ", password=" + password + ", codeRole=" + codeRole /* + ", isAthenticate=" + isAuthenticate*/ + "]";

	}

}
