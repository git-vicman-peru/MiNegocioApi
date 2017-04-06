package admin.api.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import admin.api.entities.Cliente;
import admin.api.entities.fix.ClienteRest;
import admin.api.general.BOManager;
import admin.api.general.MySqlConnector;
import admin.api.general.ServletContextHook;

public class ClienteDao implements BaseDao<Cliente> {

	private Connection cnx;
	
	public ClienteDao() {
		this.cnx = ServletContextHook.con;
	}
	
	@Override
	public void save(Cliente qentity) {
		Date dtDefault = new Date(System.currentTimeMillis());
		String sql = "Insert Into clientes (id_emp,nombres,alias,apellidos,fechanac,direccion,zona,ciudad,dni,sexo,ecivil,ruc,email,url,facebook,fonos,tipo,clave,obs,foto)Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//String sql = "Insert Into clientes (id_emp,nombres,alias,apellidos,direccion,zona,ciudad,dni,sexo,ecivil,ruc,email,url,facebook,fonos,tipo,clave,obs,foto)Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_emp());
			ps.setString(2, qentity.getNombres());
			ps.setString(3, qentity.getAlias());
			ps.setString(4, qentity.getApellidos());
			ps.setDate(5, new java.sql.Date(qentity.getFechanac(dtDefault).getTime()));
			ps.setString(6, qentity.getDireccion());
			ps.setString(7, qentity.getZona());
			ps.setString(8, qentity.getCiudad());
			ps.setString(9, qentity.getDni());
			ps.setString(10, qentity.getSexo());
			ps.setString(11, qentity.getEcivil());
			ps.setString(12, qentity.getRuc());
			ps.setString(13, qentity.getEmail());
			ps.setString(14, qentity.getUrl());
			ps.setString(15, qentity.getFacebook());
			ps.setString(16, qentity.getFonos());
			ps.setString(17, qentity.getTipo());
			ps.setString(18, qentity.getClave());
			ps.setString(19, qentity.getObs());
			ps.setString(20, qentity.getFoto());
			
			/*
			ps.setString(5, qentity.getDireccion());
			ps.setString(6, qentity.getZona());
			ps.setString(7, qentity.getCiudad());
			ps.setString(8, qentity.getDni());
			ps.setString(9, qentity.getSexo());
			ps.setString(10, qentity.getEcivil());
			ps.setString(11, qentity.getRuc());
			ps.setString(12, qentity.getEmail());
			ps.setString(13, qentity.getUrl());
			ps.setString(14, qentity.getFacebook());
			ps.setString(15, qentity.getFonos());
			ps.setString(16, qentity.getTipo());
			ps.setString(17, qentity.getClave());
			ps.setString(18, qentity.getObs());
			ps.setString(19, qentity.getFoto());
			*/
			
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public Cliente saveAndgetId(Cliente qentity){
		String[] retIds = {"id_clie"};
		String sql = "Insert Into clientes (id_emp,nombres,alias,apellidos,fechanac,direccion,zona,ciudad,dni,sexo,ecivil,ruc,email,url,facebook,fonos,tipo,clave,obs)Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//String sql = "Insert Into clientes (id_emp,nombres,alias,apellidos,direccion,zona,ciudad,dni,sexo,ecivil,ruc,email,url,facebook,fonos,tipo,clave,obs,foto)Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Date dtDefault = new Date(System.currentTimeMillis());
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql, retIds);
			ps.setInt(1, qentity.getId_emp());
			ps.setString(2, qentity.getNombres());
			ps.setString(3, qentity.getAlias());
			ps.setString(4, qentity.getApellidos());
			ps.setDate(5, new java.sql.Date(qentity.getFechanac(dtDefault).getTime()));
			ps.setString(6, qentity.getDireccion());
			ps.setString(7, qentity.getZona());
			ps.setString(8, qentity.getCiudad());
			ps.setString(9, qentity.getDni());
			ps.setString(10, qentity.getSexo());
			ps.setString(11, qentity.getEcivil());
			ps.setString(12, qentity.getRuc());
			ps.setString(13, qentity.getEmail());
			ps.setString(14, qentity.getUrl());
			ps.setString(15, qentity.getFacebook());
			ps.setString(16, qentity.getFonos());
			ps.setString(17, qentity.getTipo());
			ps.setString(18, qentity.getClave());
			ps.setString(19, qentity.getObs());
			//ps.setString(20, qentity.getFoto());
			
			/*
			ps.setString(5, qentity.getDireccion());
			ps.setString(6, qentity.getZona());
			ps.setString(7, qentity.getCiudad());
			ps.setString(8, qentity.getDni());
			ps.setString(9, qentity.getSexo());
			ps.setString(10, qentity.getEcivil());
			ps.setString(11, qentity.getRuc());
			ps.setString(12, qentity.getEmail());
			ps.setString(13, qentity.getUrl());
			ps.setString(14, qentity.getFacebook());
			ps.setString(15, qentity.getFonos());
			ps.setString(16, qentity.getTipo());
			ps.setString(17, qentity.getClave());
			ps.setString(18, qentity.getObs());
			ps.setString(19, qentity.getFoto());
			*/
			
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			int mainId = -1;
			if (rs.next()){
				mainId = rs.getInt(1);
			}
			qentity.setId_clie(mainId);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qentity;
	}
	
	@Override
	public void update(Cliente qentity) {
		Date dtDefault = new Date(System.currentTimeMillis());
		String sql = "Update clientes Set id_emp=?,nombres=?,alias=?,apellidos=?,fechanac=?,direccion=?,zona=?,ciudad=?,dni=?,sexo=?,ecivil=?,ruc=?,email=?,url=?,facebook=?,fonos=?,tipo=?,clave=?,obs=? Where (id_clie=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_emp());
			ps.setString(2, qentity.getNombres());
			ps.setString(3, qentity.getAlias());
			ps.setString(4, qentity.getApellidos());
			ps.setDate(5, new java.sql.Date(qentity.getFechanac(dtDefault).getTime()));
			ps.setString(6, qentity.getDireccion());
			ps.setString(7, qentity.getZona());
			ps.setString(8, qentity.getCiudad());
			ps.setString(9, qentity.getDni());
			ps.setString(10, qentity.getSexo());
			ps.setString(11, qentity.getEcivil());
			ps.setString(12, qentity.getRuc());
			ps.setString(13, qentity.getEmail());
			ps.setString(14, qentity.getUrl());
			ps.setString(15, qentity.getFacebook());
			ps.setString(16, qentity.getFonos());
			ps.setString(17, qentity.getTipo());
			ps.setString(18, qentity.getClave());
			ps.setString(19, qentity.getObs());
			//ps.setString(20, qentity.getFoto());
			ps.setInt(20, qentity.getId_clie());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.print("Error en el update Sql\r\n");
			e.printStackTrace();
		}
	}

	public void updateClientPhoto(int clieid, String photoname) {
		String sql = "Update clientes Set foto=? Where (id_clie=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setString(1, photoname);
			ps.setInt(2, clieid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void delete(Cliente qentity) {
		String sql = "Delete From clientes Where (id_clie=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_clie());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Cliente> loadAll() {
		List<Cliente> lst = null;
		String sql = "Select id_clie,id_emp,nombres,alias,apellidos,fechanac,direccion,zona,ciudad,dni,sexo,ecivil,ruc,email,url,facebook,fonos,tipo,clave,obs,foto From clientes";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Cliente.class);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public Cliente get(int id) {
		Cliente c = null;
		String sql = "Select id_clie,id_emp,nombres,alias,apellidos,fechanac,direccion,zona,ciudad,dni,sexo,ecivil,ruc,email,url,facebook,fonos,tipo,clave,obs,foto From clientes Where (id_clie=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				c = BOManager.BuildBO(rs, Cliente.class);
				rs.close();
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public ClienteRest getForLogin(String alias, String pass){
		ClienteRest r = null;
		String sql = "Select id_clie,id_emp,nombres,alias,apellidos,dateToMilli(fechanac)As fechanac,direccion,zona,ciudad,dni,sexo,ecivil,ruc,email,url,facebook,fonos,tipo,clave,obs,foto From clientes Where alias=? and clave=?";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setString(1, alias);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				r = BOManager.BuildBO(rs, ClienteRest.class);
				rs.close();
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public List<Cliente> getByCriteria(String scriteria) {
		String scrit = "(nombres Like '*s*') Or (apellidos Like '*s*') Or (direccion Like '*s*') Or (zona Like '*s*') Or (ciudad Like '*s*') Or (dni Like '*s*') Or (sexo Like '*s*') Or (ecivil Like '*s*') Or (ruc Like '*s*') Or (email Like '*s*') Or (url Like '*s*') Or (facebook Like '*s*') Or (fonos Like '*s*')";
		scrit = scrit.replace("*s*","%"+scriteria+"%");
		List<Cliente> lst = null;
		 
		String sql = "Select id_clie,id_emp,nombres,alias,apellidos,fechanac,direccion,zona,ciudad,dni,sexo,ecivil,ruc,email,url,facebook,fonos,tipo,obs,foto From clientes Where "+scrit;
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			lst = BOManager.BuildListBO(rs, Cliente.class);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public List<Cliente> getByCriteria(String scriteria, int offset, int size) {
		return null;
	}
/*
	public static void main(String[] args) {
		Connection cn = new MySqlConnector().getCnx();
		ClienteDao k = new ClienteDao();
		k.cnx = cn;
		
		Cliente cli, res;
		cli = new Cliente();
		cli.setNombres("Cintia");
		cli.setApellidos("Cardozo");
		cli.setEmail("cintiacardozo@gmail.com");
		cli.setFonos("938878389");
		res = k.saveAndgetId(cli);
		
		List<Cliente> l = k.getByCriteria("cu");
		System.out.println(l.size());
		System.out.println(l);
		
		ClienteRest r = k.getForLogin("rigo", "rigo123");
		//Cliente r = k.get(2);
		if (r==null){
			System.out.println("null result");
		}else{
			System.out.println("\r\n"+r.toString());
		}
		
	}
*/
}
