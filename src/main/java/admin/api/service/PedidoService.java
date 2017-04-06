package admin.api.service;

import java.util.List;

import admin.api.entities.Pedido;
import admin.api.entities.fix.DocDefInfo;

public interface PedidoService {
	public void add(Pedido newpedido);
	public Pedido save(Pedido newpedido);
	public void edit(Pedido existpedido);
	public void delete(int pedId);
	public Pedido getPedido(int pedId);
	public List<Pedido> getAllPedido();
	public List<Pedido> getPedidoByIdEmp(int nidemp);
	public DocDefInfo getDocInfo(int empId, String numSerie);
	public List<Pedido> getByEmpClie(int nidemp, int nidclie);
}
