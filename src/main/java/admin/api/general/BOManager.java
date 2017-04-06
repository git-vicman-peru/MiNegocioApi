package admin.api.general;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.api.entities.Empresa;

public class BOManager {

	//Two types of generated objects
	//a) hardcoded
	//b) on-the-fly
	
	//EntityRef_Action/Process
	private final String mfEmpresa="";
	
	public static <T> List<T> BuildListBO(ResultSet qrset, Class outClass){
		T anyBo = null;
		List<T> lst = null;
		Field[] flds = outClass.getDeclaredFields();
		Field fld;
		int i, cidx;
		String s, ssIdx = "";
		for(i=0; i<flds.length; i++){
			fld = flds[i];
			fld.setAccessible(true);
			s = fld.getName();
			try {
				cidx = qrset.findColumn(s);
			} catch (SQLException e) {
				cidx = -1;
			}
			if (cidx > 0){
				ssIdx += String.format("%d-%d;", i,cidx);
			}
		}
		if (ssIdx.isEmpty()) return null;
		ssIdx = ssIdx.substring(0, ssIdx.length()-1);
		String[] indexes = ssIdx.split(";");
		int[][] idxNums = new int[indexes.length][2];
		for (i=0; i< indexes.length; i++){
			String[] m = indexes[i].split("-");
			idxNums[i][0] = Integer.parseInt(m[0]);//entity index
			idxNums[i][1] = Integer.parseInt(m[1]);//result set index
			m = null;
		}
		lst = new ArrayList<T>();
		Object oval;
		try {
			while(qrset.next()){
				try {
					anyBo = (T)outClass.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				for (i=0; i< indexes.length; i++){
					fld = flds[i];
					oval = qrset.getObject(idxNums[i][1]);
					try {
						fld.set(anyBo, oval);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				lst.add(anyBo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static <T> T BuildBO(ResultSet qrset, Class outClass){
		String s;
		Field[] flds = outClass.getDeclaredFields();
		T anyBo = null;
		try{
			anyBo = (T)outClass.newInstance();
			for(int i=0; i<flds.length; i++){
				Field fld = flds[i];
				fld.setAccessible(true);
				s = fld.getName();
				try{
					Object oval = qrset.getObject(s);
					if (oval != null) fld.set(anyBo, oval);
				}catch(IllegalArgumentException ex){
				//System.out.print(ex.getMessage());
				}catch(SQLException ex){
				//System.out.print(ex.getMessage());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		flds = null;
		return anyBo;
	}
	
	public static Empresa empresaBuildBO(ResultSet qrset){
		Empresa e = new Empresa();
		String s;
		int cidx = -1;
		Field[] flds = Empresa.class.getDeclaredFields();
		
		try{
			for(int i=0; i<flds.length; i++){
				Field fld = flds[i];
				fld.setAccessible(true);
				s = fld.getName();
				cidx = qrset.findColumn(s);
				if (cidx > 0){
					Object oval = qrset.getObject(cidx);
					fld.set(e, oval);
				}
			}
		}catch (SQLException e1) {
			e = null;
			e1.printStackTrace();
		} catch (Exception e1) {
			e = null;
			e1.printStackTrace();
		}
		return e;
	}
	/*
	public static void main(String[] args) {
		Empresa e = new Empresa();
		e.setId_emp(11); e.setId_sus(88); e.setCiudad("Lima");e.setRazonsocial("Rusu");e.setDireccion("Av. Las Begonias 288" );
		e.setEmail("mmm@gmail.com");
		Field[] flds = Empresa.class.getDeclaredFields();
		for(int i=0; i<flds.length; i++){
			try {
				Field fld = flds[i];
				fld.setAccessible(true);
				Object oval = fld.get(e);
				if (oval != null){
					System.out.println(flds[i].getName() + ": " + oval.toString());
				}
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e2) {
				e2.printStackTrace();
			}
			
		}
	}
	*/
}
