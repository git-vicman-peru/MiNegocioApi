package admin.api.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import admin.api.entities.Pedido;
import admin.api.entities.PedidoDet;
import admin.api.entities.fix.DocDefInfo;
import admin.api.general.BOManager;
import admin.api.general.ServletContextHook;

public class PedidoDao implements BaseDao<Pedido> {

	private Connection cnx;
	
	public PedidoDao(){
		this.cnx = ServletContextHook.con;
	}
	
	@Override
	public void save(Pedido qentity) {
		PedidoDetDao detdao = new PedidoDetDao();
		int mid;
		String[] idxs = {"id_ped"};
		String sql = qentity.sqlInsert();
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql, idxs);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()){
				mid = rs.getInt(1);
				qentity.setId_ped(mid);
				//System.out.println("/////");
				//System.out.println(mid);
				rs.close();
				ps.close();
				//------------------------------------------
				detdao.saveBatchWith(qentity.getDetalles(), mid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		detdao = null;
	}
	
	public Pedido add(Pedido qentity){
		PedidoDetDao detdao = new PedidoDetDao();
		int mid;
		String[] idxs = {"id_ped"};
		String sql = qentity.sqlInsert();
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql, idxs);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()){
				mid = rs.getInt(1);
				qentity.setId_ped(mid);
				rs.close();
				ps.close();
				//------------------------------------------
				detdao.saveBatchWith(qentity.getDetalles(), mid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		detdao = null;
		return qentity;
	}
	
	public void saveBatch(List<Pedido> qlist){
		PedidoDetDao detdao = new PedidoDetDao();
		int mid;
		String[] idxs = {"id_ped"};
		String sql;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			ps = this.cnx.prepareStatement(null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		for(Pedido ped : qlist){
			sql = ped.sqlInsert();
			try {
				ps.execute(sql, idxs);
				rs = ps.getGeneratedKeys();
				mid = rs.getInt(1);
				rs.close();
				//------------------------------------------
				detdao.saveBatchWith(ped.getDetalles(), mid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		detdao = null;
	}

	@Override
	public void update(Pedido qentity) {
		PedidoDetDao detdao = new PedidoDetDao();
		String sql = qentity.sqlEdit();
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			//-----------------------------
			detdao.updateBatch(qentity.getDetalles());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		detdao = null;
	}

	@Override
	public void delete(Pedido qentity) {
		String sql = qentity.sqlDelete();
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pedido> loadAll() {
		List<Pedido> lst = null;
		List<PedidoDet> ldet = null;
		//String sql = "Select id_ped,id_emp,serie,numero,id_clie,fecha,obs,fechaentrega,direcentrega,notiemail,notifono,estado,monto,acuenta,pagado From pedidos";
		String sql = "Select p.id_ped,p.id_emp,p.serie,p.numero,p.id_clie,concat(c.apellidos,', ',c.nombres) As Cliente,p.fecha,p.obs,p.fechaentrega,p.direcentrega,p.notiemail,p.notifono,p.estado,p.monto,p.acuenta,p.pagado From pedidos p Inner Join clientes c On p.id_clie=c.id_clie Where (p.id_emp=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Pedido.class);
			ps.close();
			//------------------------
			for(Pedido pd : lst){
				sql = "Select id_pdet,id_ped,id_prod,cantidad,unidad,descripcion,precio From pedidos_det Where (id_ped="+pd.getId_ped()+")";
				ps = this.cnx.prepareStatement(sql);
				rs = ps.executeQuery();
				ldet = BOManager.BuildListBO(rs, PedidoDet.class);
				pd.setDetalles(ldet);
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public Pedido get(int id) {
		Pedido p = null;
		List<PedidoDet> ldet = null;
		//String sql = "Select id_ped,id_emp,serie,numero,id_clie,fecha,obs,fechaentrega,direcentrega,notiemail,notifono,estado,monto,acuenta,pagado From pedidos Where (id_ped=?)";
		String sql = "Select p.id_ped,p.id_emp,p.serie,p.numero,p.id_clie,concat(c.apellidos,', ',c.nombres) As Cliente,p.fecha,p.obs,p.fechaentrega,p.direcentrega,p.notiemail,p.notifono,p.estado,p.monto,p.acuenta,p.pagado From pedidos p Inner Join clientes c On p.id_clie=c.id_clie Where (p.id_emp=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			p = BOManager.BuildBO(rs, Pedido.class);
			ps.close();
			//------------------------
			sql = "Select id_pdet,id_ped,id_prod,cantidad,unidad,descripcion,precio From pedidos_det Where (id_ped="+p.getId_ped()+")";
			ps = this.cnx.prepareStatement(sql);
			rs = ps.executeQuery();
			ldet = BOManager.BuildListBO(rs, PedidoDet.class);
			p.setDetalles(ldet);
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Pedido> getByCriteria(String scriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> getByCriteria(String scriteria, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pedido> getByIdEmp(int nidemp) {
		List<Pedido> lst = null;
		List<PedidoDet> ldet = null;
		//String sql = "Select id_ped,id_emp,serie,numero,id_clie,fecha,obs,fechaentrega,direcentrega,notiemail,notifono,estado,monto,acuenta,pagado From pedidos Where (id_emp=?)";
		String sql = "Select p.id_ped,p.id_emp,p.id_clie,concat(c.apellidos,', ',c.nombres) As Cliente,p.serie,p.numero,p.fecha,numero As fechaStr,p.obs,p.fechaentrega,numero As fechaentregaStr,p.direcentrega,p.notiemail,p.notifono,p.estado,p.monto,p.acuenta,p.pagado From pedidos p Inner Join clientes c On p.id_clie=c.id_clie Where (p.id_emp=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, nidemp);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Pedido.class);
			ps.close();
			//------------------------
			for(Pedido pd : lst){
				sql = "Select id_pdet,id_ped,id_prod,cantidad,unidad,descripcion,precio From pedidos_det Where (id_ped="+pd.getId_ped()+")";
				ps = this.cnx.prepareStatement(sql);
				rs = ps.executeQuery();
				ldet = BOManager.BuildListBO(rs, PedidoDet.class);
				pd.setDetalles(ldet);
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public List<Pedido> getByEmpClie(int nidemp, int nidclie) {
		List<Pedido> lst = null;
		List<PedidoDet> ldet = null;
		//String sql = "Select id_ped,id_emp,serie,numero,id_clie,fecha,obs,fechaentrega,direcentrega,notiemail,notifono,estado,monto,acuenta,pagado From pedidos Where (id_emp=?)";
		String sql = "Select p.id_ped,p.id_emp,p.id_clie,concat(c.apellidos,', ',c.nombres) As Cliente,p.serie,p.numero,p.fecha,dateToMilli(p.fecha) As fechaStr,p.obs,p.fechaentrega,dateToMilli(p.fechaentrega) As fechaentregaStr,p.direcentrega,p.notiemail,p.notifono,p.estado,p.monto,p.acuenta,p.pagado From pedidos p Inner Join clientes c On p.id_clie=c.id_clie Where (p.id_emp=?)And(p.id_clie=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, nidemp);
			ps.setInt(2, nidclie);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, Pedido.class);
			ps.close();
			//------------------------
			for(Pedido pd : lst){
				sql = "Select id_pdet,id_ped,id_prod,cantidad,unidad,descripcion,precio From pedidos_det Where (id_ped="+pd.getId_ped()+")";
				ps = this.cnx.prepareStatement(sql);
				rs = ps.executeQuery();
				ldet = BOManager.BuildListBO(rs, PedidoDet.class);
				pd.setDetalles(ldet);
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public DocDefInfo readDocInfo(int empid, String serie){
		DocDefInfo d = null;
		try {
			CallableStatement cst = this.cnx.prepareCall("{CALL numpedidonext(?,?)}");
			cst.setInt(1, empid);
			cst.setString(2, serie);
			cst.execute();
			ResultSet rs = cst.getResultSet();
			rs.next();
			d = new DocDefInfo();
			d.setId_cfg(rs.getInt(1));
			d.setSerie(serie);
			d.setNumero(rs.getString(2));
			d.setFormato(rs.getString(3));
			rs.close();
			cst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
	/*
	public static void main(String[] args) {
		Connection cn = new MySqlConnector().getCnx();
		PedidoDao pdao = new PedidoDao();
		Pedido ped = new Pedido();
		List<PedidoDet> ldet = new ArrayList<PedidoDet>();
		PedidoDet pdet;
		pdao.cnx = cn;
		
		ped.setId_emp(11); ped.setSerie("001"); ped.setNumero("048"); ped.setId_clie(2);
		ped.setFecha(new Date()); ped.setFechaentrega(new Date());
		pdet = new PedidoDet();
		pdet.setId_prod(14); pdet.setCantidad(1); pdet.setUnidad("uni"); pdet.setDescripcion("lo mejor de ropa interior."); pdet.setPrecio(2.3f);
		ldet.add(pdet);
		pdet = new PedidoDet();
		pdet.setId_prod(22); pdet.setCantidad(3); pdet.setUnidad("uni"); pdet.setDescripcion("aretes de metal chafalonia"); pdet.setPrecio(22.3f);
		ldet.add(pdet);
		ped.setDetalles(ldet);
		pdao.save(ped);
		//List<Pedido> lst = pdao.getByIdEmp(11);
		//System.out.println(lst.size());
		//System.out.println(lst.get(0));
		//System.out.println("detalles: " + lst.get(0).getDetalles().size());
		//System.out.println(lst.get(0).getDetalles());
	}
	*/
}
