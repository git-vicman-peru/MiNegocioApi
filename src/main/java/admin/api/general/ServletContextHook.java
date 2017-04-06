package admin.api.general;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextHook implements ServletContextListener {

	public static Connection con;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		this.con = new MySqlConnector().getCnx();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
