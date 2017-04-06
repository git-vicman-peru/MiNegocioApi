package admin.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import admin.api.dao.ClienteDao;
import admin.api.entities.Cliente;
import admin.api.entities.fix.ClienteRest;
import admin.api.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private ClienteDao clieDao = new ClienteDao();
	
	@Override
	public void save(Cliente qentity) {
		this.clieDao.save(qentity);
	}

	@Override
	public Cliente saveAndgetId(Cliente qentity) {
		return this.clieDao.saveAndgetId(qentity);
	}

	@Override
	public void update(Cliente qentity) {
		this.clieDao.update(qentity);
	}

	@Override
	public void delete(Cliente qentity) {
		this.clieDao.delete(qentity);
	}

	@Override
	public List<Cliente> loadAll() {
		return this.clieDao.loadAll();
	}

	@Override
	public Cliente get(int id) {
		return this.clieDao.get(id);
	}

	@Override
	public ClienteRest getForLogin(String alias, String pass){
		return this.clieDao.getForLogin(alias, pass);
	}
	
	@Override
	public List<Cliente> getByCriteria(String scriteria) {
		return this.clieDao.getByCriteria(scriteria);
	}

	@Override
	public List<Cliente> getByCriteria(String scriteria, int offset, int size) {
		return this.clieDao.getByCriteria(scriteria, offset, size);
	}

	public void updateClientPhoto(int clieid, String photoname){
		this.clieDao.updateClientPhoto(clieid, photoname);
	}
}
