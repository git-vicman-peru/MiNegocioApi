package admin.api.service;

import java.util.List;

import admin.api.entities.Empresa;
import admin.api.entities.fix.SSAutolist;

public interface EmpresaService {
	public void add(Empresa empresa);
	public void edit(Empresa empresa);
	public void delete(int empresaId);
	public Empresa getEmpresa(int empresaId);
	public List getAllEmpresa();
	public SSAutolist readAutoList(String lsttype);
	public Empresa getEmpByLogin(String user, String pass);
	public Empresa getEmpById_Sus(int nidsus);
	public void editByIdSus(int idemp, int idsus);
	public String getPregByUser(String username);
	public String getRespByUser(String username);
}
