package admin.api.dao;

import java.util.List;

public interface BaseDao<T> {
	
	public void save(T qentity);
	
	public void update(T qentity);
	
	public void delete(T qentity);
	
	public List<T> loadAll();
	
	public T get(int id);
	
	public List<T> getByCriteria(String scriteria);
	
	public List<T> getByCriteria(String scriteria, int offset, int size);
	
}
