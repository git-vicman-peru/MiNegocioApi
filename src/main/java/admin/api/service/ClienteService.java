package admin.api.service;

import java.util.List;

import admin.api.entities.Cliente;
import admin.api.entities.fix.ClienteRest;

public interface ClienteService {

	public void save(Cliente qentity);
	
	public Cliente saveAndgetId(Cliente qentity);
	
	public void update(Cliente qentity);
	
	public void delete(Cliente qentity);
	
	public List<Cliente> loadAll();
	
	public Cliente get(int id);
	
	public ClienteRest getForLogin(String alias, String pass);
	
	public List<Cliente> getByCriteria(String scriteria);
	
	public List<Cliente> getByCriteria(String scriteria, int offset, int size);
	
	public void updateClientPhoto(int clieid, String photoname);
	
}
