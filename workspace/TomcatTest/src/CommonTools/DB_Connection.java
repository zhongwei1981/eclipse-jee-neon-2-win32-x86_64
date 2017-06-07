package CommonTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB_Connection {

	private static final DB_Connection me = new DB_Connection();
	public static DB_Connection getInstance() {
		return me;
	}
	
	private static Log log = new Log(DB_Connection.class.getSimpleName());
	
	private static final String DB_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_url = "jdbc:sqlserver://localhost;databaseName=testDB";
	private static final String DB_name = "sa";
	private static final String DB_pwd = "IloveChina1981"; 
	
	private Connection conn = null;
	
	private DB_Connection() {
		try {
			Class.forName(DB_driver);
			conn = DriverManager.getConnection(DB_url, DB_name, DB_pwd);
		} catch (ClassNotFoundException e) {
			log.e(e);
		} catch (SQLException e) {
			log.e(e);
		}
	}
	
	protected void finalize() {
		if (conn == null) {
			return;
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			log.e(e);
		}
	}

	public boolean exec(String strSQL) throws SQLException{
		boolean ret = true;

		try {
			PreparedStatement prep = conn.prepareStatement(strSQL);
			prep.executeUpdate();
			log.d("#### Done: %s", strSQL);
		} catch (SQLException e) {
			log.e(e);
			ret = false;
			throw e;
		}
		
		return ret;
	}
}
