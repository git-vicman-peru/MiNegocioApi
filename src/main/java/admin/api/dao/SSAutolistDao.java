package admin.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.api.entities.fix.SSAutolist;
import admin.api.general.ServletContextHook;

public class SSAutolistDao {

	private Connection cnx;
	
	public SSAutolistDao(){
		this.cnx = ServletContextHook.con;
	}
	
	public SSAutolist readAutolist(String lsttype){
		SSAutolist ssa = null;
		String sql = "Select id_cfg,valor from config_ap where (categoria=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setString(1, lsttype);
			ssa = new SSAutolist();
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ssa.setId_cfg(rs.getInt(1));
				ssa.setValor(rs.getString(2));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ssa;
	}
}
