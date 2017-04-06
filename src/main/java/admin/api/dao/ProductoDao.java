package admin.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import admin.api.entities.FotoProd;
import admin.api.entities.Producto;
import admin.api.general.BOManager;
import admin.api.general.CritMapper;
import admin.api.general.MySqlConnector;
import admin.api.general.ServletContextHook;

public class ProductoDao implements BaseDao<Producto> {

	private Connection cnx;
	
	public ProductoDao() {
		this.cnx = ServletContextHook.con;
	}
	
	@Override
	public void save(Producto qentity) {
		String sql = "Insert Into productos (id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx)Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_emp());
			ps.setString(2, qentity.getGrupo());
			ps.setString(3, qentity.getSubgrupo());
			ps.setString(4, qentity.getCodigo());
			ps.setString(5, qentity.getNombre());
			ps.setString(6, qentity.getDescripcionlarga());
			ps.setString(7, qentity.getDescripcioncorta());
			ps.setString(8, qentity.getObs());
			ps.setFloat(9, qentity.getStock());
			ps.setString(10, qentity.getEstado());
			ps.setInt(11, qentity.getFotoidx());
			ps.setString(12, qentity.getUnidadprecios());
			ps.setInt(13, qentity.getUnidadidx());
			ps.execute();
			sql = "Insert Into fotos (id_emp,id_prod,nombre,tipo)Values(?,?,?,?)";
			ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_emp());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void saveprod(Producto qentity, FotoProd qfot) {
		String sql = "Insert Into productos (id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx)Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String backIds[] = {"id_prod"};
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql,backIds);
			ps.setInt(1, qentity.getId_emp());
			ps.setString(2, qentity.getGrupo());
			ps.setString(3, qentity.getSubgrupo());
			ps.setString(4, qentity.getCodigo());
			ps.setString(5, qentity.getNombre());
			ps.setString(6, qentity.getDescripcionlarga());
			ps.setString(7, qentity.getDescripcioncorta());
			ps.setString(8, qentity.getObs());
			ps.setFloat(9, qentity.getStock());
			ps.setString(10, qentity.getEstado());
			ps.setInt(11, qentity.getFotoidx());
			ps.setString(12, qentity.getUnidadprecios());
			ps.setInt(13, qentity.getUnidadidx());
			ps.execute();
			ResultSet res = ps.getGeneratedKeys();
			if (res.next()){
				int idprod = res.getInt(1);
				sql = "Insert Into fotos (id_emp,id_prod,nombre,tipo)Values(?,?,?,?)";
				ps = this.cnx.prepareStatement(sql);
				ps.setInt(1, qfot.getId_emp());
				ps.setInt(2, idprod);
				ps.setString(3, qfot.getNombre());
				ps.setString(4, qfot.getTipo());
				ps.execute();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void update(Producto qentity) {
		String sql = "Update productos Set id_emp=?,grupo=?,subgrupo=?,codigo=?,nombre=?,descripcionlarga=?,descripcioncorta=?,obs=?,stock=?,estado=?,fotoidx=?,unidadprecios=?,unidadidx=? Where id_prod=?";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_emp());
			ps.setString(2, qentity.getGrupo());
			ps.setString(3, qentity.getSubgrupo());
			ps.setString(4, qentity.getCodigo());
			ps.setString(5, qentity.getNombre());
			ps.setString(6, qentity.getDescripcionlarga());
			ps.setString(7, qentity.getDescripcioncorta());
			ps.setString(8, qentity.getObs());
			ps.setFloat(9, qentity.getStock());
			ps.setString(10, qentity.getEstado());
			ps.setInt(11, qentity.getFotoidx());
			ps.setString(12, qentity.getUnidadprecios());
			ps.setInt(13, qentity.getUnidadidx());
			ps.setInt(14, qentity.getId_prod());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateprod(Producto qprod, FotoProd qfot){
		String sql = "Update productos Set id_emp=?,grupo=?,subgrupo=?,codigo=?,nombre=?,descripcionlarga=?,descripcioncorta=?,obs=?,stock=?,estado=?,fotoidx=?,unidadprecios=?,unidadidx=? Where id_prod=?";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qprod.getId_emp());
			ps.setString(2, qprod.getGrupo());
			ps.setString(3, qprod.getSubgrupo());
			ps.setString(4, qprod.getCodigo());
			ps.setString(5, qprod.getNombre());
			ps.setString(6, qprod.getDescripcionlarga());
			ps.setString(7, qprod.getDescripcioncorta());
			ps.setString(8, qprod.getObs());
			ps.setFloat(9, qprod.getStock());
			ps.setString(10, qprod.getEstado());
			ps.setInt(11, qprod.getFotoidx());
			ps.setString(12, qprod.getUnidadprecios());
			ps.setInt(13, qprod.getUnidadidx());
			ps.setInt(14, qprod.getId_prod());
			ps.execute();
			sql = "Update fotos Set id_emp=?,id_prod=?,nombre=?,tipo=? Where (id_fot=?)";
			ps.setInt(1, qfot.getId_emp());
			ps.setInt(2, qfot.getId_prod());
			ps.setString(3, qfot.getNombre());
			ps.setString(4, qfot.getTipo());
			ps.setInt(5, qfot.getId_fot());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Producto qentity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Producto> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Producto> getByCriteria(String scriteria) {
		//possible entry formats of scriteria:
		// 1) direct string value
		// 2) direct number, evaluates for = (equal)
		// 3) compare number: realvalue < n1 ( < can be any operator < = > <>, separated by spaces )
		// 4) range number: n1 - n2 ( - minus sign )
		FotoProdDao ft = new FotoProdDao();
		ft.setConnection(this.cnx);
		String scrit;
		CritMapper cm = new CritMapper();
		cm.setSscriteria(scriteria);
		switch (cm.getOptype()){
			case 1://direct string value
				scrit = "(grupo Like ?) Or (subgrupo Like ?) Or (codigo Like ?) Or (nombre Like ?) Or (descripcionlarga Like ?) Or (descripcioncorta Like ?) Or (obs Like ?) Or (estado Like ?)";
				scrit = scrit.replace("?","'%"+scriteria+"%'");
				break;
			case 2://direct number
				scrit = "(stock = ?)";
				scrit = scrit.replace("?",scriteria);
				break;
			case 3://compare number: realvalue < n2
				scrit = String.format("(stock %s %s)",cm.getComp(),cm.getNum1());
				break;
			case 4://range number: n1 - n2
				scrit = String.format("(stock >= %s) And (stock <= %s)",cm.getNum1(),cm.getNum2());
				break;
			default:
				scrit = "(grupo Like ?) Or (subgrupo Like ?) Or (codigo Like ?) Or (nombre Like ?) Or (descripcionlarga Like ?) Or (descripcioncorta Like ?) Or (obs Like ?) Or (estado Like ?)";
				scrit = scrit.replace("?","'%"+scriteria+"%'");
		}
		List<Producto> lst = null;
		String sql = "Select id_prod,id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx From productos Where "+ scrit;
		//System.out.println(sql);
		try{
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Producto.class);
			rs.close();
			ps.close();
			//----------------------------------------------
			int[] pids = lst.stream().map(Producto::getId_prod).collect(Collectors.toList()).stream().mapToInt(i->i).toArray();
			List<FotoProd> fotos = ft.loadMulti(pids);
			for(Producto t : lst){
				List<FotoProd> f = fotos.stream().filter(m->m.getId_prod()==t.getId_prod()).collect(Collectors.toList());
				t.setFotos(f);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return lst;
	}

	public List<Producto> getByCriteriaEmp(String scriteria, int nidemp) {
		//possible entry formats of scriteria:
		// 1) direct string value
		// 2) direct number, evaluates for = (equal)
		// 3) compare number: realvalue < n1 ( < can be any operator < = > <>, separated by spaces )
		// 4) range number: n1 - n2 ( - minus sign )
		FotoProdDao ft = new FotoProdDao();
		ft.setConnection(this.cnx);
		String scrit;
		CritMapper cm = new CritMapper();
		cm.setSscriteria(scriteria);
		switch (cm.getOptype()){
			case 1://direct string value
				scrit = "(grupo Like ?) Or (subgrupo Like ?) Or (codigo Like ?) Or (nombre Like ?) Or (descripcionlarga Like ?) Or (descripcioncorta Like ?) Or (obs Like ?) Or (estado Like ?)";
				scrit = scrit.replace("?","'%"+scriteria+"%'");
				break;
			case 2://direct number
				scrit = "(stock = ?)";
				scrit = scrit.replace("?",scriteria);
				break;
			case 3://compare number: realvalue < n2
				scrit = String.format("(stock %s %s)",cm.getComp(),cm.getNum1());
				break;
			case 4://range number: n1 - n2
				scrit = String.format("(stock >= %s) And (stock <= %s)",cm.getNum1(),cm.getNum2());
				break;
			default:
				scrit = "(grupo Like ?) Or (subgrupo Like ?) Or (codigo Like ?) Or (nombre Like ?) Or (descripcionlarga Like ?) Or (descripcioncorta Like ?) Or (obs Like ?) Or (estado Like ?)";
				scrit = scrit.replace("?","'%"+scriteria+"%'");
		}
		List<Producto> lst = null;
		String sql = "Select id_prod,id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx From productos Where "+ scrit + " And (id_emp="+nidemp+")";
		//System.out.println(sql);
		try{
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Producto.class);
			rs.close();
			ps.close();
			//----------------------------------------------
			int[] pids = lst.stream().map(Producto::getId_prod).collect(Collectors.toList()).stream().mapToInt(i->i).toArray();
			List<FotoProd> fotos = ft.loadMulti(pids);
			for(Producto t : lst){
				List<FotoProd> f = fotos.stream().filter(m->m.getId_prod()==t.getId_prod()).collect(Collectors.toList());
				t.setFotos(f);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return lst;
	}
	
	@Override
	public List<Producto> getByCriteria(String scriteria, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Producto> getProductoByIdEmp(int nidemp){
		FotoProdDao ft = new FotoProdDao();
		ft.setConnection(this.cnx);
		List<Producto> lst = null;
		String sql = "Select id_prod,id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx From productos Where id_emp=?";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, nidemp);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Producto.class);
			rs.close();
			ps.close();
			//----------------------------------------------
			int[] pids = lst.stream().map(Producto::getId_prod).collect(Collectors.toList()).stream().mapToInt(i->i).toArray();
			List<FotoProd> fotos = ft.loadMulti(pids);
			for(Producto t : lst){
				List<FotoProd> f = fotos.stream().filter(m->m.getId_prod()==t.getId_prod()).collect(Collectors.toList());
				t.setFotos(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public List<Producto> getProdThrIDs(String ssProdIds){
		String[] a = ssProdIds.split("-");
		String orcrit = "";
		for(int i=0; i<a.length; i++){
			orcrit += "(id_prod="+ a[i] + ") Or ";
		}
		orcrit = orcrit.substring(0, orcrit.length()-4);
		FotoProdDao ft = new FotoProdDao();
		ft.setConnection(this.cnx);
		List<Producto> lst = null;
		String sql = "Select id_prod,id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx From productos Where "+orcrit+" Order By grupo";
		//System.out.println(sql);
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Producto.class);
			rs.close();
			ps.close();
			//----------------------------------------------
			int[] pids = lst.stream().map(Producto::getId_prod).collect(Collectors.toList()).stream().mapToInt(i->i).toArray();
			List<FotoProd> fotos = ft.loadMulti(pids);
			for(Producto t : lst){
				List<FotoProd> f = fotos.stream().filter(m->m.getId_prod()==t.getId_prod()).collect(Collectors.toList());
				t.setFotos(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public List<Producto> getProductoEmpGrupoCrit(int nidemp, String grupo, String crit){
		FotoProdDao ft = new FotoProdDao();
		ft.setConnection(this.cnx);
		List<Producto> lst = null;
		String scrit = "(nombre Like ?) Or (descripcionlarga Like ?) Or (descripcioncorta Like ?)";
		scrit = scrit.replace("?","'%"+crit+"%'");
		String grp = grupo.toLowerCase().trim();
		String sql;
		boolean flgtodos;
		if (grp.equals("todos")){
			sql = "Select id_prod,id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx From productos Where (id_emp=?)And("+scrit+")";
			flgtodos = true;
		}else{
			sql = "Select id_prod,id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx From productos Where (id_emp=?)And(grupo=?)And("+scrit+")";
			flgtodos = false;
		}
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			if (flgtodos){
				ps.setInt(1, nidemp);
			}else{
				ps.setInt(1, nidemp);
				ps.setString(2, grupo);
			}
			
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Producto.class);
			rs.close();
			ps.close();
			//----------------------------------------------
			int[] pids = lst.stream().map(Producto::getId_prod).collect(Collectors.toList()).stream().mapToInt(i->i).toArray();
			List<FotoProd> fotos = ft.loadMulti(pids);
			for(Producto t : lst){
				List<FotoProd> f = fotos.stream().filter(m->m.getId_prod()==t.getId_prod()).collect(Collectors.toList());
				t.setFotos(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public List<Producto> getProductoByIdEmpGrupo(int nidemp, String grupo){
		FotoProdDao ft = new FotoProdDao();
		ft.setConnection(this.cnx);
		List<Producto> lst = null;
		String sql = "Select id_prod,id_emp,grupo,subgrupo,codigo,nombre,descripcionlarga,descripcioncorta,obs,stock,estado,fotoidx,unidadprecios,unidadidx From productos Where (id_emp=?)And(grupo=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, nidemp);
			ps.setString(2, grupo);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Producto.class);
			rs.close();
			ps.close();
			//----------------------------------------------
			int[] pids = lst.stream().map(Producto::getId_prod).collect(Collectors.toList()).stream().mapToInt(i->i).toArray();
			List<FotoProd> fotos = ft.loadMulti(pids);
			for(Producto t : lst){
				List<FotoProd> f = fotos.stream().filter(m->m.getId_prod()==t.getId_prod()).collect(Collectors.toList());
				t.setFotos(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public List<String> getGrupoByEmp(int nidemp){
		List<String> l = new ArrayList<String>();
		String sql = "Select Distinct grupo From productos Where (id_emp=?) Order By grupo";
		try{
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, nidemp);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String s = rs.getString(1);
				l.add(s);
			}
			rs.close();
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return l;
	}
	
	/*
	public static void main(String[] args) {
		Connection cn = new MySqlConnector().getCnx();
		ProductoDao k = new ProductoDao();
		k.cnx = cn;
		List<Producto> lst = k.getByCriteria("1 - 10");
		//System.out.println("fotos Item 0: "+ lst.get(0).getFotos().size());
		System.out.println(lst.size());
		System.out.println(lst);
	}
	*/
}
