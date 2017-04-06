package admin.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.api.dao.PedidoDao;
import admin.api.entities.Pedido;
import admin.api.entities.fix.DocDefInfo;
import admin.api.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private PedidoDao pedDao = new PedidoDao();
	
	@Override
	public void add(Pedido newpedido) {
		this.pedDao.save(newpedido);
	}

	@Override
	public Pedido save(Pedido newpedido) {
		return this.pedDao.add(newpedido);
	}
	
	@Override
	public void edit(Pedido existpedido) {
		this.pedDao.update(existpedido);
	}

	@Override
	public void delete(int pedId) {
		Pedido p = new Pedido();
		p.setId_ped(pedId);
		this.pedDao.delete(p);
	}

	@Override
	public Pedido getPedido(int pedId) {
		return this.pedDao.get(pedId);
	}

	@Override
	public List<Pedido> getAllPedido() {
		return this.pedDao.loadAll();
	}

	@Override
	public List<Pedido> getPedidoByIdEmp(int nidemp) {
		return this.pedDao.getByIdEmp(nidemp);
	}
	
	@Override
	public DocDefInfo getDocInfo(int empId, String numSerie){
		return this.pedDao.readDocInfo(empId, numSerie);
	}
	
	@Override
	public List<Pedido> getByEmpClie(int nidemp, int nidclie){
		return this.pedDao.getByEmpClie(nidemp, nidclie);
	}

}
