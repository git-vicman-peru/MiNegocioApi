package admin.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.api.dao.ProductoDao;
import admin.api.entities.Pedido;
import admin.api.entities.Producto;
import admin.api.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	private ProductoDao prodDao = new ProductoDao();
	
	@Transactional
	public void add(Producto newProd) {
		//this.prodDao.add(newProd);
	}

	@Transactional
	public void edit(Producto existProd) {
		//this.prodDao.edit(existProd);
	}

	@Transactional
	public void delete(int prodId) {
		//this.prodDao.delete(prodId);
	}

	@Transactional
	public Producto getProducto(int prodId) {
		return null;
		//return this.prodDao.getProducto(prodId);
	}

	@Transactional
	public List<Producto> getAllProducto() {
		return null;
		//return this.prodDao.getAllProducto();
	}

	@Override
	public List<Producto> getProductoByIdEmp(int nidemp) {
		return this.prodDao.getProductoByIdEmp(nidemp);
	}

	@Override
	public List<Producto> getByCriteria(String scriteria) {
		return this.prodDao.getByCriteria(scriteria);
	}

	@Override
	public List<Producto> getByCriteriaEmp(String scriteria, int idemp) {
		return this.prodDao.getByCriteriaEmp(scriteria,idemp);
	}

	@Override
	public List<String> getGrupoByEmp(int nidemp) {
		return this.prodDao.getGrupoByEmp(nidemp);
	}

	@Override
	public List<Producto> getProductoByIdEmpGrupo(int nidemp, String qgrupo) {
		return this.prodDao.getProductoByIdEmpGrupo(nidemp, qgrupo);
	}
	
	@Override
	public List<Producto> getProdsThrIDs(String ssprodIds){
		return this.prodDao.getProdThrIDs(ssprodIds);
	}
	
	@Override
	public List<Producto> getProductoEmpGrupoCrit(int nidemp, String grupo, String crit){
		return this.prodDao.getProductoEmpGrupoCrit(nidemp, grupo, crit);
	}
}
