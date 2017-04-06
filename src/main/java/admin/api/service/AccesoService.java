package admin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.api.model.dao.AccesoDao;
import admin.api.model.minegocioadm.Acceso;

@Service
//@Transactional
public class AccesoService {
	
	//@Autowired
	private AccesoDao accDao;

	public Acceso checkUserAccess(String uname, String upass){
		//return accDao.checkCredentials(uname, upass);
		return null;
	}
}
