package admin.api.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import admin.api.service.ProductoService;
import admin.api.entities.FotoProd;
import admin.api.entities.Producto;

@RestController
@RequestMapping(value="/prods/specific")
public class ProductoRestController {

	@Value("#{myProps['UPLOADED_FOLDER']}")
	private String UPLOADED_FOLDER;
	
	@Autowired
	private ProductoService prodServ;
	
	@RequestMapping(value="/listbyemp/{idemp}",  method = RequestMethod.GET)
	public List<Producto> getProdList(@PathVariable int idemp){
		List<Producto> pl = this.prodServ.getProductoByIdEmp(idemp);
		
		String fs = "/";
		String basepath, imgpath;
		basepath = UPLOADED_FOLDER + fs + "emp-"+idemp + fs +"items";
		
		for(Producto pd: pl){
			List<FotoProd> ps = pd.getFotos();
			for(int i=0; i<ps.size(); i++){
				imgpath = basepath + fs + ps.get(i).getNombre();
				try {
					Path p = Paths.get(imgpath);
					byte[] ib = Files.readAllBytes(p);
					Encoder enc = Base64.getEncoder();
					String imgStr = enc.encodeToString(ib);
					ps.get(i).setSrc64(imgStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		/*
		pl.forEach(item->{
			String nstr = item.getUnidadprecios().replace('Ø', ':');
			item.setUnidadprecios(nstr);
		});
		*/
		return pl;
	}
	
	@RequestMapping(value="/srch")
	public List<Producto> searchProdJson(String prodkey, int empId){
		List<Producto> lst;
		lst = this.prodServ.getByCriteriaEmp(prodkey,empId);
		String fs = "/";
		String basepath, imgpath;
		basepath = UPLOADED_FOLDER + fs + "emp-"+ empId + fs +"items";
		
		for(Producto pd: lst){
			List<FotoProd> ps = pd.getFotos();
			for(int i=0; i<ps.size(); i++){
				imgpath = basepath + fs + ps.get(i).getNombre();
				try {
					Path p = Paths.get(imgpath);
					byte[] ib = Files.readAllBytes(p);
					Encoder enc = Base64.getEncoder();
					String imgStr = enc.encodeToString(ib);
					ps.get(i).setSrc64(imgStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lst;
	}
	
	public String sayHello(){
		return "hello";
	}
	
	public String myImagePath(){
		return UPLOADED_FOLDER;
	}
	
	@RequestMapping(value="/grupos/{idemp}", method=RequestMethod.GET)
	@ResponseBody
	public List<String> getGrupoByEmp(@PathVariable int idemp){
		return this.prodServ.getGrupoByEmp(idemp);
	}

	@RequestMapping(value="/list/{idemp}/{grupo}",  method = RequestMethod.GET)
	public List<Producto> getProdByEmpGrupo(@PathVariable int idemp, @PathVariable String grupo){
		List<Producto> pl;
		String grp = grupo.toLowerCase().trim();
		if (grp.equals("todos")){
			pl = this.prodServ.getProductoByIdEmp(idemp);			
		}else{
			pl = this.prodServ.getProductoByIdEmpGrupo(idemp, grupo);			
		}
		
		String fs = File.separator;
		String basepath, imgpath;
		basepath = UPLOADED_FOLDER + fs + "emp-"+idemp + fs +"items";
		
		for(Producto pd: pl){
			List<FotoProd> ps = pd.getFotos();
			for(int i=0; i<ps.size(); i++){
				imgpath = basepath + fs + ps.get(i).getNombre();
				try {
					Path p = Paths.get(imgpath);
					byte[] ib = Files.readAllBytes(p);
					Encoder enc = Base64.getEncoder();
					String imgStr = enc.encodeToString(ib);
					ps.get(i).setSrc64(imgStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pl;
	}
	
	@RequestMapping(value="/list/{idemp}/{grupo}/{criterion}",  method = RequestMethod.GET)
	public List<Producto> getProdEmpGrupoCrit(@PathVariable int idemp, @PathVariable String grupo, @PathVariable String criterion){
		List<Producto> pl;
		
		pl = this.prodServ.getProductoEmpGrupoCrit(idemp, grupo, criterion);
		
		String fs = File.separator;
		String basepath, imgpath;
		basepath = UPLOADED_FOLDER + fs + "emp-"+idemp + fs +"items";
		
		for(Producto pd: pl){
			List<FotoProd> ps = pd.getFotos();
			for(int i=0; i<ps.size(); i++){
				imgpath = basepath + fs + ps.get(i).getNombre();
				try {
					Path p = Paths.get(imgpath);
					byte[] ib = Files.readAllBytes(p);
					Encoder enc = Base64.getEncoder();
					String imgStr = enc.encodeToString(ib);
					ps.get(i).setSrc64(imgStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pl;
	}
	
	@RequestMapping(value="/fav/{empid}/{prodids}",  method = RequestMethod.GET)
	public List<Producto> getFavProdList(@PathVariable int empid, @PathVariable String prodids){
		//System.out.println(prodids);
		List<Producto> pl = this.prodServ.getProdsThrIDs(prodids);
		
		String fs = "/";
		String basepath, imgpath;
		basepath = UPLOADED_FOLDER + fs + "emp-"+empid + fs +"items";
		
		for(Producto pd: pl){
			List<FotoProd> ps = pd.getFotos();
			for(int i=0; i<ps.size(); i++){
				imgpath = basepath + fs + ps.get(i).getNombre();
				try {
					Path p = Paths.get(imgpath);
					byte[] ib = Files.readAllBytes(p);
					Encoder enc = Base64.getEncoder();
					String imgStr = enc.encodeToString(ib);
					ps.get(i).setSrc64(imgStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		return pl;
	}
	
	
	/*
	public static void main(String[] args) {
		ProductoRestController r = new ProductoRestController();
		System.out.println(r.sayHello());
		System.out.println(r.myImagePath());
	}
	*/
}
