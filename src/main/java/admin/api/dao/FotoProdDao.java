package admin.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import admin.api.entities.FotoProd;
import admin.api.general.BOManager;
import admin.api.general.MySqlConnector;
import admin.api.general.ServletContextHook;

public class FotoProdDao {

	private Connection cnx;
	
	public FotoProdDao(){
		this.cnx = ServletContextHook.con;
	}
	
	public void setConnection(Connection qcnx){
		this.cnx = qcnx;
	}
	
	public void save(FotoProd qfot){
		String sql = "Insert Into fotos (id_emp,id_prod,nombre,tipo)Values(?,?,?,?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qfot.getId_emp());
			ps.setInt(2, qfot.getId_prod());
			ps.setString(3, qfot.getNombre());
			ps.setString(4, qfot.getTipo());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(FotoProd qfot){
		String sql = "Update fotos Set id_emp=?,id_prod=?,nombre=?,tipo=? Where (id_fot=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1,qfot.getId_emp());
			ps.setInt(2,qfot.getId_prod());
			ps.setString(3,qfot.getNombre());
			ps.setString(4,qfot.getTipo());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<FotoProd> loadByIdProd(int nidprod){
		List<FotoProd> lst = null;
		String sql = "Select id_fot,id_emp,id_prod,nombre,tipo From fotos Where (id_prod=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1,nidprod);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, FotoProd.class);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public List<FotoProd> loadMulti(int[] prodIds){
		List<FotoProd> lst = null;
		String scrit = "";

		for(int id : prodIds){
			scrit += String.format("(id_prod=%s) Or ", id);
		}
		if (!scrit.isEmpty()){
			scrit = scrit.trim();
			scrit = scrit.substring(0, scrit.length()-3);
			String sql = "Select id_fot,id_emp,id_prod,nombre,tipo From fotos Where " + scrit;
			try {
				PreparedStatement ps = this.cnx.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				lst = BOManager.BuildListBO(rs, FotoProd.class);
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lst;
	}
	/*
	public static void main(String[] args) {
		Connection cn = new MySqlConnector().getCnx();
		FotoProdDao fdao = new FotoProdDao();
		fdao.cnx = cn;
		List<FotoProd> lst = fdao.loadMulti(new int[]{6,7,26,27,28});
		System.out.println(lst.size());
		System.out.println(lst);
		System.out.println(lst.get(0));
	}
	*/
}
