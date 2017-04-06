package admin.api.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import admin.api.entities.Cliente;
import admin.api.entities.fix.ClienteRest;
import admin.api.entities.fix.UserLogin;
import admin.api.service.ClienteService;

@RestController
@RequestMapping("/client")
public class ClienteRestController {

	@Autowired
	private ClienteService clieServ;

	@Value("#{myProps['UPLOADED_FOLDER']}")
	private String UPLOADED_FOLDER;
	
	@RequestMapping(value="/keepclient", method=RequestMethod.POST,headers="content-type=application/json")
	public Cliente saveClient(@RequestBody ClienteRest qclie){
		//System.out.println(qclie.toString());
		Cliente cli = new Cliente();
		cli.setId_emp(qclie.getId_emp());
		cli.setNombres(qclie.getNombres());
		cli.setAlias(qclie.getAlias());
		cli.setApellidos(qclie.getApellidos());
		Date fn = new Date(Long.parseLong(qclie.getFechanac()));
		cli.setFechanac(fn);
		cli.setDireccion(qclie.getDireccion());
		cli.setZona(qclie.getZona());
		cli.setCiudad(qclie.getCiudad());
		cli.setDni(qclie.getDni());
		cli.setSexo(qclie.getSexo());
		cli.setEcivil(qclie.getEcivil());
		cli.setRuc(qclie.getRuc());
		cli.setEmail(qclie.getEmail());
		cli.setUrl(qclie.getUrl());
		cli.setFacebook(qclie.getFacebook());
		cli.setFonos(qclie.getFonos());
		cli.setTipo(qclie.getTipo());
		cli.setClave(qclie.getClave());
		cli.setObs(qclie.getObs());
		
		Cliente q = this.clieServ.saveAndgetId(cli);
		
		
		if ((qclie.getFoto64() != null)&&(qclie.getFoto() != null)){
			
        	String fext;//file extension
        	fext = qclie.getFoto().split("\\.")[1];
        	String photoname = String.format("clie-%d-%d.%s", qclie.getId_emp(),q.getId_clie(),fext);
        	
        	this.clieServ.updateClientPhoto(q.getId_clie(), photoname);
			
			String basepath = UPLOADED_FOLDER + File.separator + "emp-"+qclie.getId_emp() + File.separator+"clients";
			File dir = new File(basepath);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	        String strcode = qclie.getFoto64().trim();
	        File fotoFile = new File(dir.getAbsolutePath() + File.separator + photoname);
	        byte[] imgbytes = Base64.getMimeDecoder().decode(strcode);
	        try {
	        	OutputStream os = new FileOutputStream(fotoFile);
	        	os.write(imgbytes);
	            os.close();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        imgbytes = null;
	        
		}
        
		return q;
	}
	
	
	@RequestMapping(value="/editclient", method=RequestMethod.POST,headers="content-type=application/json")
	public boolean updateClient(@RequestBody ClienteRest qclie){
		boolean b = false;
		try{
			Cliente cli = new Cliente();
			cli.setId_clie(qclie.getId_clie());
			cli.setId_emp(qclie.getId_emp());
			cli.setNombres(qclie.getNombres());
			cli.setAlias(qclie.getAlias());
			cli.setApellidos(qclie.getApellidos());
			Date fn = new Date(Long.parseLong(qclie.getFechanac()));
			cli.setFechanac(fn);
			cli.setDireccion(qclie.getDireccion());
			cli.setZona(qclie.getZona());
			cli.setCiudad(qclie.getCiudad());
			cli.setDni(qclie.getDni());
			cli.setSexo(qclie.getSexo());
			cli.setEcivil(qclie.getEcivil());
			cli.setRuc(qclie.getRuc());
			cli.setEmail(qclie.getEmail());
			cli.setUrl(qclie.getUrl());
			cli.setFacebook(qclie.getFacebook());
			cli.setFonos(qclie.getFonos());
			cli.setTipo(qclie.getTipo());
			cli.setClave(qclie.getClave());
			cli.setObs(qclie.getObs());
			//cli.setFoto(qclie.getFoto());
			
			try{
				this.clieServ.update(cli);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			
	        if ((qclie.getFoto64() != null)&&(qclie.getFoto() != null)){
	        	String fext;//file extension
	        	fext = qclie.getFoto().split("\\.")[1];
	        	String photoname = String.format("clie-%d-%d.%s", qclie.getId_emp(),qclie.getId_clie(),fext);
	        	
	        	this.clieServ.updateClientPhoto(qclie.getId_clie(), photoname);
	        
				String basepath = UPLOADED_FOLDER + File.separator + "emp-"+qclie.getId_emp() + File.separator+"clients";
				File dir = new File(basepath);
		        if (!dir.exists()) {
		            dir.mkdirs();
		        }
		        String strcode = qclie.getFoto64().trim();
		        File fotoFile = new File(dir.getAbsolutePath() + File.separator + photoname);
		        byte[] imgbytes = Base64.getMimeDecoder().decode(strcode);
		        try {
		        	OutputStream os = new FileOutputStream(fotoFile);
		        	os.write(imgbytes);
		            os.close();
		        } catch (IOException e) {
		        	System.out.println("Error al escribir archivo de foto\r\n");
		        	e.printStackTrace();
		        }
		        imgbytes = null;
		       
			}
			
	        b = true;
		}catch(Exception ex){
			b =false;
			ex.printStackTrace();
		}
        
		return b;
	}
	
	@RequestMapping(value="/userlogin", method=RequestMethod.POST,headers="content-type=application/json")
	public ClienteRest existUser(@RequestBody UserLogin quser){
		//System.out.print(quser.toString());
		
		ClienteRest r = this.clieServ.getForLogin(quser.getUsername(), quser.getPass());
		if (r==null) return null;
		String fs = File.separator;
		try{
			String imgpath = UPLOADED_FOLDER + fs + "emp-"+r.getId_emp() + fs +"clients"+fs+r.getFoto();
			Path p = Paths.get(imgpath);
			byte[] bytes = Files.readAllBytes(p);
			String imgStr = Base64.getEncoder().encodeToString(bytes);
			r.setFoto64(imgStr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
}
