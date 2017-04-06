package admin.api.service;

import java.util.List;

import admin.api.entities.ClieBlog;
import admin.api.entities.fix.BlogRev;
import admin.api.entities.fix.ClieBlogRev;

public interface ClieBlogService {

	public void save(ClieBlog qentity);
	
	public void update(ClieBlog qentity);
	
	public void delete(ClieBlog qentity);
	
	public List<ClieBlog> loadAll();

	public ClieBlog get(int id);
	
	public List<ClieBlog> getByCriteria(String scriteria);
	
	public List<ClieBlog> getByCriteria(String scriteria, int offset, int size);
	
	public List<ClieBlog> getByEmpAndProd(int idemp, int idprod);
	
	public List<ClieBlogRev> getClieBlogs(int idemp, int idprod);
	
}
