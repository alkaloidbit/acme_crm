package acme.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilitaire {

	public static Date getDateAmericaine(String date) throws BizException {
		try {
			return new Date(date.substring(6, 10) + "/" + date.substring(3, 5) + "/" + date.substring(0, 2));
		} catch (Exception e) { throw new BizException("Wrong date format."); }
	}
	
	public static Date getDateEuropeenne(String date) throws BizException {
		try {
			return new Date(date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 3));
		} catch (Exception e) { throw new BizException("Wrong date format."); }
	}
	
	public static String getDateEuropeenne(Date date) throws BizException {
		try {
			StringBuffer d = (new SimpleDateFormat("dd/MM/yyyy")).format(date, new StringBuffer(), new FieldPosition(DateFormat.LONG));
			return d.toString();
		} catch (Exception e) { throw new BizException("Wrong date format."); }
	}
	public static String getDateAmericaine(Date date) throws BizException {
		try {
			StringBuffer d = (new SimpleDateFormat("yyyy/MM/dd")).format(date, new StringBuffer(), new FieldPosition(DateFormat.LONG));
			return d.toString();
		} catch (Exception e) { throw new BizException("Wrong date format."); }
	}
	public static String getStringMD5(String pString) throws BizException {
		if (pString == null || "".equals(pString)) return "";
		
		String result = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Add password bytes to digest
			md.update(pString.getBytes());

			// Get the hash's bytes
			byte[] bytes = md.digest();

			// This bytes[] has bytes in decimal format. Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			// Get complete hashed password in hex format
			result = sb.toString();
			return result;
			
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			throw new BizException (nsae.getMessage());
		}
	}
}
