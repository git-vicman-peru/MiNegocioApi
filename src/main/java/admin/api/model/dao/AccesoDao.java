package admin.api.model.dao;
/*
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import admin.api.model.minegocioadm.Acceso;
*/
public class AccesoDao {

/*	
	
	public Acceso checkCredentials(String qusername, String qpassword){
		Acceso acc = null;

		//Query qry = session.createQuery("update accesos set vigente=0");
		//int r = qry.executeUpdate();
		Criteria crit = session.createCriteria(Acceso.class)
				.add(Restrictions.eq("usuario", qusername))
				.add(Restrictions.eq("clave", qpassword))
				.add(Restrictions.eq("vigente", true));
		Object result = crit.uniqueResult();
		if (result != null){
			acc = (Acceso)result;
		}
		return acc;
	}
*/
}
