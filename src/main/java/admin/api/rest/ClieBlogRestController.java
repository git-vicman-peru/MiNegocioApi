package admin.api.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import admin.api.service.ClieBlogService;
import admin.api.entities.ClieBlog;
import admin.api.entities.Empresa;
//import admin.api.entities.fix.BlogModel;
import admin.api.entities.fix.BlogRev;
import admin.api.entities.fix.ClieBlogRev;

@RestController
@RequestMapping("/clieblog")
public class ClieBlogRestController {

	@Autowired
	private ClieBlogService blogServ;
	
	@Value("#{myProps['UPLOADED_FOLDER']}")
	private String UPLOADED_FOLDER;
	
	@RequestMapping(value="/{empid}/{prodid}", method=RequestMethod.GET)
	public List<ClieBlogRev> getClieBlogs(@PathVariable int empid, @PathVariable int prodid){
		//String fs = "/";
		//String basepath = UPLOADED_FOLDER + "emp-"+empid +fs+"bloggers"+fs;
		List<ClieBlogRev> lst = this.blogServ.getClieBlogs(empid, prodid);
		/*
		Encoder enc = Base64.getEncoder();
		lst.forEach(blog->{
			String p = basepath+blog.getAvatar();
			File dir = new File(p);
			if (dir.exists()){
				Path path = Paths.get(p);
				try {
					byte[] idata = Files.readAllBytes(path);
					blog.setAvatar(enc.encodeToString(idata));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});*/
		return lst;
	}
	
	
	@RequestMapping(value="/avatarfor/{fileref}", method=RequestMethod.GET)
	public String getAvatarString(@PathVariable("fileref") String fileref,HttpSession session){ 
		Empresa oldemp = (Empresa)session.getAttribute("chempresa");
		int empid = oldemp.getId_emp();
		fileref = fileref.replace('}','.');
		String fs = "/";
		String basepath = UPLOADED_FOLDER + "emp-"+empid +fs+"bloggers"+fs;
		Encoder enc = Base64.getEncoder();
		
		String p = basepath+fileref;
		File dir = new File(p);
		String avstring=null;
		if (dir.exists()){
			Path path = Paths.get(p);
			try {
				byte[] idata = Files.readAllBytes(path);
				avstring = enc.encodeToString(idata);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return avstring;
	}
	
	@RequestMapping(value="/savecomment",method=RequestMethod.POST,headers="content-type=application/json")
	public void saveBlogClientComment(@RequestBody ClieBlog blogi){
		//System.out.println(blogi.toString());
		this.blogServ.save(blogi);
	}
	
	/*
	@RequestMapping(value="/blogs/savecomment",method=RequestMethod.POST,headers="content-type=application/json")
	public void saveBlogClientComment(@RequestBody BlogModel blogi){
		System.out.println(blogi);
		BlogItem com = new BlogItem();
		if (blogi.isIsuser()){
			if (blogi.isChangeavatar()){
				//save avatar
			}
			//save comment only
			com.setId_bc(blogi.getUserid());
			com.setId_emp(blogi.getEmpid());
			com.setId_prod(blogi.getProdid());
			com.setFecha(blogi.getFecha());
			com.setComentario(blogi.getComment());
			com.setRate(blogi.getRate()+"");
			this.blgServ.save(com);

		}
		
	}
	*/
	

}
