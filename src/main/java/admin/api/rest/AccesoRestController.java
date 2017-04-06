package admin.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import admin.api.model.minegocioadm.Acceso;
import admin.api.service.AccesoService;

@RestController
public class AccesoRestController {
/*
	@Autowired
	private AccesoService accServ;
	
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST) 
	public Acceso checkForAccess(@RequestBody Acceso data){
		Acceso a = null;
		a = accServ.checkUserAccess(data.getUsuario(), data.getClave());
		return a;
	}
	
	@RequestMapping(value = "/logincheckfem", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded") 
	public Acceso checkForAccessFEM(Acceso data){//FormEncodedMethod
		Acceso a = null;
		a = accServ.checkUserAccess(data.getUsuario(), data.getClave());
		return a;
	}
	*/
}
