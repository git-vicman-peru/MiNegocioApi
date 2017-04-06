package admin.api.rest;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import admin.api.entities.Pedido;
import admin.api.entities.fix.DocDefInfo;
import admin.api.general.EMailerApi;
import admin.api.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

	@Autowired
	private PedidoService pedSrv;
	
	@Autowired
	private EMailerApi mailer;
	
	@RequestMapping(value="/envio", method=RequestMethod.POST, headers = "content-type=application/json")
	public boolean hacerPedido(@RequestBody Pedido qped){
		//System.out.print(qped.toString());
		//System.out.print("\r\ncontinue execution ...");
		boolean r = false;
		try{
			String sserie;
			sserie = qped.getSerie();
			if ((sserie == null)||(sserie.isEmpty())){
				sserie = "001";
			}
							
			DocDefInfo doc = null;
			try{
				doc = this.pedSrv.getDocInfo(qped.getId_emp(), sserie);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			qped.setFecha(new Date(Long.parseLong(qped.getFechaStr())));
			qped.setFechaentrega(new Date(Long.parseLong(qped.getFechaentregaStr())));
			qped.setSerie(sserie);
			qped.setNumero(doc.getNumero());
			Pedido p = this.pedSrv.save(qped);
			//System.out.print(p.toString());
			try{
				String toAddr = qped.getNotiemail();
				String fromAddr = "minegociobph@gmail.com";
		 		String subject = "MiNegocio-BPH Servicio de Pedidos EnLinea - Confirmacion";
		 		String body = "Su pedido Numero : " + p.getNumero() + "\r\n" + 
		 				"de fecha : " + dateToStrFormat(qped.getFechaStr(), "dd/MM/yyy") + "\r\n" + 
		 				"Esta siendo procesada para su entrega en la fecha : " + dateToStrFormat(qped.getFechaentregaStr(), "dd/MM/yyy") + " a la siguiente direccion : " + qped.getDirecentrega() +"\r\n" +
		 				"Ante cualquier consulta no dude en contactarse con nosotros, por este medio."; 
				this.mailer.ReadyToSendEmail(toAddr, fromAddr, subject, body);
			}catch(Exception ex){
				ex.printStackTrace();
				r = false;
				return r;
			}
			r = true;
			
		}catch(Exception ex){
			r =false;
			ex.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping(value="/seeall/{empid}/{clieid}", method=RequestMethod.GET)
	public List<Pedido> getAllPedidos(@PathVariable int empid, @PathVariable int clieid){
		List<Pedido> lst = this.pedSrv.getByEmpClie(empid, clieid);
		for(Pedido p : lst){
			
		}
		return lst;
	}
	
	private  String dateToStrFormat(String millis, String format){
        long ml = Long.parseLong(millis);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ml);
        Date d = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(d);
    }
}
