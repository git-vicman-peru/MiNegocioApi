package admin.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import admin.api.entities.ClieBlog;
import admin.api.entities.fix.BlogRev;
import admin.api.entities.fix.ClieBlogRev;
import admin.api.general.BOManager;
import admin.api.general.ServletContextHook;

public class ClieBlogDao implements BaseDao<ClieBlog> {

	private Connection cnx;
	
	public ClieBlogDao(){
		this.cnx = ServletContextHook.con;
	}
	
	@Override
	public void save(ClieBlog qentity) {
		try {
			String sql = qentity.sqlInsert();
			PreparedStatement ps = this.cnx.prepareStatement(sql);			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ClieBlog qentity) {
		try {
			String sql = qentity.sqlEdit();
			PreparedStatement ps = this.cnx.prepareStatement(sql);			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ClieBlog qentity) {
		try {
			String sql = qentity.sqlDelete();
			PreparedStatement ps = this.cnx.prepareStatement(sql);			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ClieBlog> loadAll() {
		List<ClieBlog> lst = null;
		try{
			String sql = "Select id_clieb,id_clie,id_emp,id_prod,fecha,nivel,comentario,rating From clientesblog";
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, ClieBlog.class);
			rs.close();
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public ClieBlog get(int id) {
		ClieBlog blg = null;
		try{
			String sql = "Select id_clieb,id_clie,id_emp,id_prod,fecha,nivel,comentario,rating From clientesblog Where (id_clieb=?)";
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			blg = BOManager.BuildBO(rs, ClieBlog.class);
			rs.close();
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return blg;
	}

	@Override
	public List<ClieBlog> getByCriteria(String scriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClieBlog> getByCriteria(String scriteria, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ClieBlog> getByEmpAndProd(int idemp, int idprod){
		List<ClieBlog> lst = null;
		try{
			String sql = "Select id_clieb,id_clie,id_emp,id_prod,dateToMilli(fecha)As fecha,nivel,comentario,rating From clientesblog Where (id_emp=?) And (id_prod=?)";
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, idemp);
			ps.setInt(2, idprod);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, ClieBlog.class);
			rs.close();
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	public List<ClieBlogRev> getClieBlogs(int idemp, int idprod){
		List<ClieBlogRev> lst = null;
		try{
			String sql = "Select a.id_clieb,a.id_clie,b.alias As blogger,b.foto As avatar,dateToMilli(a.fecha) As fecha,a.nivel,a.comentario,a.rating From clientesblog a Inner Join clientes b On a.id_clie=b.id_clie Where (a.id_emp=?) And (a.id_prod=?)";
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, idemp);
			ps.setInt(2, idprod);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, ClieBlogRev.class);
			rs.close();
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	/*
	public static void main(String[] args) {
		Connection cn = new MySqlConnector().getCnx();
		BlogItemDao dao = new BlogItemDao();
		dao.cnx = cn;
		List<BlogRev> l = dao.getBlogs(11, 30);
		System.out.println(l.size()+"");
	}
	*/
}
