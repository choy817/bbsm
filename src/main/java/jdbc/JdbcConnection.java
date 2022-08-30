package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@bbsm_medium?TNS_ADMIN=/Users/choyeonju/Desktop/cyj/programming/project/download/Wallet_bbsm";
			String user = "ADMIN";
			String password = "Bbangsm1234!";
			con = DriverManager.getConnection(url, user, password);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}

}
