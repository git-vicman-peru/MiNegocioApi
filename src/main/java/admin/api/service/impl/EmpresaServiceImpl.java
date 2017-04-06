package admin.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.api.dao.EmpresaDao;
import admin.api.dao.SSAutolistDao;
import admin.api.entities.Empresa;
import admin.api.entities.fix.SSAutolist;
import admin.api.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private EmpresaDao empresaDao = new EmpresaDao();


	
	private SSAutolistDao listasDao = new SSAutolistDao();

	
	@Transactional
	public void add(Empresa empresa) {
		//empresaDao.add(empresa);
	}

	@Override
	public void edit(Empresa empresa) {
		empresaDao.update(empresa);
	}

	@Transactional
	public void delete(int empresaId) {
		//empresaDao.delete(empresaId);
	}

	@Transactional
	public Empresa getEmpresa(int empresaId) {
		//return empresaDao.getEmpresa(empresaId);
		return null;
	}

	@Transactional
	public List getAllEmpresa() {
		//return empresaDao.getAllEmpresa();
		return null;
	}

	@Override
	public SSAutolist readAutoList(String lsttype){
		return this.listasDao.readAutolist(lsttype);
	}

	@Override
	public Empresa getEmpByLogin(String user, String pass) {
		return this.empresaDao.getEmpByLogin(user, pass);
	}

	@Transactional
	public Empresa getEmpById_Sus(int nidsus) {
		//return this.empresaDao.getEmpByIdSus(nidsus);
		return null;
	}
	
	@Transactional
	public void editByIdSus(int idemp, int idsus){
		//this.empresaDao.editByIdSus(idemp, idsus);
	}
	
	@Transactional
	public String getPregByUser(String username){
		//return this.empresaDao.getPregByUser(username);
		return null;
	}
	
	@Transactional
	public String getRespByUser(String username){
		//return this.empresaDao.getRespByUser(username);
		return null;
	}

}
