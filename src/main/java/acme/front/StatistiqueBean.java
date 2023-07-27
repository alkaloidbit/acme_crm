package acme.front;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class StatistiqueBean extends AbstractBean {

	private String year;

	private float ca;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public float getCa() {
		return ca;
	}

	public void setCa(float ca) {
		this.ca = ca;
	}
}
