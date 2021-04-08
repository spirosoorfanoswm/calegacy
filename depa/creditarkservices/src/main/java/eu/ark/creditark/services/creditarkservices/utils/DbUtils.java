package eu.ark.creditark.services.creditarkservices.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtils {

	public static void close(ResultSet rows, PreparedStatement st) {
		try {
			if(null!=rows)  {
				rows.close();
				rows = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			if(null!=st) st.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void close(CallableStatement cs) {
		try {
			if(null!=cs) cs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void close(PreparedStatement ps) {
		try {
			if(null!=ps) ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void close(Connection ps) {
		try {
			if(null!=ps) ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
