package admin.api.general;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class MySqlConnector {

	private String url;
	private String driver;
	private String user;
	private String pwd;
	
	public MySqlConnector(){
		Properties props = new Properties();
		try {
			//InputStream input = servctx.getResourceAsStream("/resources/config/app.settings");
			
			//props.load(MySqlConnector.class.getResourceAsStream("/vic/apps/global/module.settings"));
			//InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("WEB-INF/app.settings");
			InputStream input = MySqlConnector.class.getClassLoader().getResourceAsStream("database.properties");
			props.load(input);
			this.url = props.getProperty("db_url");
			this.driver = props.getProperty("db_driver");
			this.user = props.getProperty("db_user");
			this.pwd = props.getProperty("db_pass");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getCnx(){
		Connection cnx = null;
		try{
			Class.forName(driver);
			cnx = DriverManager.getConnection(url,user,pwd);
			System.out.println("db hooked!");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return cnx;
	}
	/*
	public static void main(String[] args) {
		//System.out.println(Thread.currentThread().getContextClassLoader());
		new MySqlConnector().getCnx();
		System.out.println("OK");
	}
	*/
}
