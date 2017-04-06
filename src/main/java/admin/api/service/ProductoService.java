package admin.api.service;

import java.util.List;

import admin.api.entities.Pedido;
import admin.api.entities.Producto;

public interface ProductoService {
	public void add(Producto newProd);
	public void edit(Producto existProd);
	public void delete(int prodId);
	public Producto getProducto(int prodId);
	public List<Producto> getAllProducto();
	public List<Producto> getProductoByIdEmp(int nidemp); 
	public List<Producto> getByCriteria(String scriteria);
	public List<Producto> getByCriteriaEmp(String scriteria, int idemp);
	public List<String> getGrupoByEmp(int nidemp);
	public List<Producto> getProductoByIdEmpGrupo(int nidemp, String qgrupo);
	public List<Producto> getProdsThrIDs(String ssprodIds);
	public List<Producto> getProductoEmpGrupoCrit(int nidemp, String grupo, String crit);
}
