package admin.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.api.entities.Empresa;
import admin.api.general.BOManager;
import admin.api.general.ServletContextHook;

public class EmpresaDao implements BaseDao<Empresa> {

	private Connection cnx;
	
	public EmpresaDao() {
		this.cnx = ServletContextHook.con;
	}
	
	@Override
	public void save(Empresa qentity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Empresa qentity) {
		boolean res = false;
		String sql = "Update empresas Set razonsocial=?,ruc=?,direccion=?,fonos=?,distrito=?,ciudad=?,pais=?,url=?,email=?,facebook=?,coordenadas=?,fotos=?,obs=?,usuario=?,clave=?,pregunta=?,respuesta=? Where id_emp=? ";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setString(1, qentity.getRazonsocial());
			ps.setString(2, qentity.getRuc());
			ps.setString(3, qentity.getDireccion());
			ps.setString(4, qentity.getFonos());
			ps.setString(5, qentity.getDistrito());
			ps.setString(6, qentity.getCiudad());
			ps.setString(7, qentity.getPais());
			ps.setString(8, qentity.getUrl());
			ps.setString(9, qentity.getEmail());
			ps.setString(10, qentity.getFacebook());
			ps.setString(11, qentity.getCoordenadas());
			ps.setString(12, qentity.getFotos());
			ps.setString(13, qentity.getObs());
			ps.setString(14, qentity.getUsuario());
			ps.setString(15, qentity.getClave());
			ps.setString(16, qentity.getPregunta());
			ps.setString(17, qentity.getRespuesta());
			ps.setInt(18, qentity.getId_emp());
			res = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Empresa qentity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Empresa> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empresa get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empresa> getByCriteria(String scriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empresa> getByCriteria(String scriteria, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public Empresa getEmpByLogin(String uname, String upass) {
		Empresa e = new Empresa();
		e.setId_emp(-1);
		String sql = "Select * From empresas Where (usuario=?) And (clave=?)";
		try{
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				e = BOManager.BuildBO(rs, Empresa.class);
				rs.close();
			}
			ps.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return e;
	}	
	/*
	public static void main(String[] args) {
		Connection cn = new MySqlConnector().getCnx();
		Empresa e = null;
		String uname = "jose";
		String upass = "jose12";
		String sql = "Select id_emp,razonsocial,ruc From empresas Where usuario=? And clave=?";
		try{
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				e = new Empresa();
				e.setId_emp(rs.getInt(1));
				e.setRazonsocial(rs.getString(2));
				e.setRuc(rs.getString(3));
				rs.close();
			}			
			ps.close();
		}catch(SQLException ex){
			e = null;
			ex.printStackTrace();
		}
		System.out.println(e);
	}
	*/
}
