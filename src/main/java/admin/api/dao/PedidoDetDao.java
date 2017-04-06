package admin.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import admin.api.entities.PedidoDet;
import admin.api.general.BOManager;
import admin.api.general.ServletContextHook;

public class PedidoDetDao implements BaseDao<PedidoDet> {

	private Connection cnx;
	
	public PedidoDetDao(){
		this.cnx = ServletContextHook.con;
	}
	
	@Override
	public void save(PedidoDet qentity) {
		String sql = "Insert Into pedidos_det (id_ped,id_prod,cantidad,unidad,descripcion,precio)Values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_ped());
			ps.setInt(2, qentity.getId_prod());
			ps.setFloat(3, qentity.getCantidad());
			ps.setString(4, qentity.getUnidad());
			ps.setString(5, qentity.getDescripcion());
			ps.setFloat(6, qentity.getPrecio());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void saveBatch(List<PedidoDet> qlist){
		String sql;
		PreparedStatement ps;
		try {
			for(PedidoDet d : qlist){
				sql = d.sqlInsert();
				ps = this.cnx.prepareStatement(sql);
				ps.execute(sql);
				ps.close();
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void saveBatchWith(List<PedidoDet> qlist, int nidped){
		String sql;
		PreparedStatement ps;
		try {
			for(PedidoDet d : qlist){
				d.setId_ped(nidped);
				sql = d.sqlInsert();
				ps = this.cnx.prepareStatement(sql);
				ps.execute(sql);
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(PedidoDet qentity) {
		String sql = "Update pedidos_det Set id_ped=?,id_prod=?,cantidad=?,unidad=?,descripcion=?,precio=? Where (id_pdet=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_ped());
			ps.setInt(2, qentity.getId_prod());
			ps.setFloat(3, qentity.getCantidad());
			ps.setString(4, qentity.getUnidad());
			ps.setString(5, qentity.getDescripcion());
			ps.setFloat(6, qentity.getPrecio());
			ps.setInt(7, qentity.getId_pdet());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateBatch(List<PedidoDet> qdet){
		Statement stm = null;
		String sql;
		
		try {
			stm = this.cnx.createStatement();
			for(PedidoDet pd : qdet){
				sql = pd.getStatus().equals("x")?pd.sqlDelete():pd.sqlEdit();
				stm.executeUpdate(sql);
			}
			stm.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void delete(PedidoDet qentity) {
		String sql = "Delete From pedidos_det Where (id_pdet=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, qentity.getId_pdet());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<PedidoDet> loadAll() {
		List<PedidoDet> lst = null;
		String sql = "Select id_pdet,id_ped,id_prod,cantidad,unidad,descripcion,precio From pedidos_det";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, PedidoDet.class);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public PedidoDet get(int id) {
		PedidoDet p = null;
		String sql = "Select id_pdet,id_ped,id_prod,cantidad,unidad,descripcion,precio From pedidos_det Where (id_pdet=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			p = BOManager.BuildBO(rs, PedidoDet.class);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<PedidoDet> getByCriteria(String scriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PedidoDet> getByCriteria(String scriteria, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PedidoDet> getAllByIdPed(int nidped) {
		List<PedidoDet> lst = null;
		String sql = "Select id_pdet,id_ped,id_prod,cantidad,unidad,descripcion,precio From pedidos_det Where (id_ped=?)";
		try {
			PreparedStatement ps = this.cnx.prepareStatement(sql);
			ps.setInt(1, nidped);
			ResultSet rs = ps.executeQuery();
			lst = BOManager.BuildListBO(rs, PedidoDet.class);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/*
	public static void main(String[] args) {
		Connection cn = new MySqlConnector().getCnx();
		
		PedidoDetDao pdao = new PedidoDetDao();
		List<PedidoDet> lst = new ArrayList<PedidoDet>();
		pdao.cnx = cn;
		
		PedidoDet det;
		
		det = new PedidoDet();
		//det.setId_ped(id_ped);
		
		
		
		int idped=1, nidprod=6;
		String[] des = "todas;frios por;sin verg;frutas;s2323".split(";");
		for(int i=0; i< 5; i++){
			det = new PedidoDet();
			//det.setId_pdet(2);
			det.setId_ped(idped); det.setId_prod(nidprod); det.setCantidad(1+i); det.setUnidad("uni");
			det.setDescripcion("uan descrip "+des[i]); det.setPrecio((2 * (i+1)));
			lst.add(det);
			det = null;
		}
		
		//pdao.save(det);
		//pdao.delete(det);
		//pdao.saveBatch(lst);
		//pdao.saveBatchWith(lst, idped);
		//lst = pdao.getAllByIdPed(idped);
		//System.out.println(lst);
		
		
	}
	*/
}
