package admin.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import admin.api.dao.ClieBlogDao;
import admin.api.entities.ClieBlog;
import admin.api.entities.fix.BlogRev;
import admin.api.entities.fix.ClieBlogRev;
import admin.api.service.ClieBlogService;

@Service
public class ClieBlogServiceImpl implements ClieBlogService {

	private ClieBlogDao blgDao = new ClieBlogDao();
	
	@Override
	public void save(ClieBlog qentity) {
		this.blgDao.save(qentity);
	}

	@Override
	public void update(ClieBlog qentity) {
		this.blgDao.update(qentity);
	}

	@Override
	public void delete(ClieBlog qentity) {
		this.blgDao.delete(qentity);
	}

	@Override
	public List<ClieBlog> loadAll() {
		return this.blgDao.loadAll();
	}

	@Override
	public ClieBlog get(int id) {
		return this.blgDao.get(id);
	}

	@Override
	public List<ClieBlog> getByCriteria(String scriteria) {
		return this.blgDao.getByCriteria(scriteria);
	}

	@Override
	public List<ClieBlog> getByCriteria(String scriteria, int offset, int size) {
		return this.getByCriteria(scriteria, offset, size);
	}

	@Override
	public List<ClieBlog> getByEmpAndProd(int idemp, int idprod) {
		return this.blgDao.getByEmpAndProd(idemp, idprod);
	}

	@Override
	public List<ClieBlogRev> getClieBlogs(int idemp, int idprod) {
		return blgDao.getClieBlogs(idemp, idprod);
	}

}
