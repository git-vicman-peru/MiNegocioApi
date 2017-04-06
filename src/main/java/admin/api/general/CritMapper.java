package admin.api.general;

public class CritMapper {

	private String sscriteria;
	private boolean flag;
	private int optype;
	private String comp;
	private float num1;
	private float num2;
	private String strvalue;
	
	public CritMapper(){
		
	}

	private boolean isNumeric(String str) {  
	  try{  
	    double d = Double.parseDouble(str);  
	  }catch(NumberFormatException nfe){  
	    return false;  
	  }  
	  return true;  
	}
	
	//possible entry formats of scriteria:
	// 1) direct string value
	// 2) direct number, evaluates for = (equal)
	// 3) compare number: realvalue < n2 ( < can be any operator < = > <>, separated by spaces )
	// 4) range number: n1 - n2 ( - minus sign )
	private void doMap(){
		String chk = this.sscriteria;
		String[] ss;
		String[] ops;
		if (chk.indexOf(" ")>-1){
			if (chk.indexOf("-")>-1){
				ss = chk.split("-");
				this.optype = 4;
				try{
					this.num1 = Float.parseFloat(ss[0].trim());
					this.num2 = Float.parseFloat(ss[1].trim());
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return;
			}
			
			ops = "<=;>=;<>;<;>".split(";");
			for(int i=0;i<ops.length;i++){
				if (chk.indexOf(ops[i])>-1){
					chk = chk.replace(ops[i], "");
					System.out.println(chk);
					this.optype = 3;
					try{
						this.comp = ops[i];
						this.num1 = Float.parseFloat(chk.trim());
					}catch(Exception ex){
						ex.printStackTrace();
					}
					return;
				}
			}
		}else{
			if (chk.indexOf("-")>-1){
				ss = chk.split("-");
				this.optype = 4;
				try{
					this.num1 = Float.parseFloat(ss[0].trim());
					this.num2 = Float.parseFloat(ss[1].trim());
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return;
			}else{
				ops = "<=;>=;<>;<;>".split(";");
				for(int i=0;i<ops.length;i++){
					if (chk.indexOf(ops[i])>-1){
						chk = chk.replace(ops[i], "");
						System.out.println(chk);
						this.optype = 3;
						try{
							this.comp = ops[i];
							this.num1 = Float.parseFloat(chk.trim());
						}catch(Exception ex){
							ex.printStackTrace();
						}
						return;
					}
				}
				if (this.isNumeric(chk)){
					this.optype = 2;
					return;
				}else{
					this.optype = 1;
					return;
				}
			}
			
		}
		this.optype = 0;
	}
	public int getOptype() {
		return optype;
	}

	public void setOptype(int optype) {
		this.optype = optype;
	}


	public String getSscriteria() {
		return sscriteria;
	}

	public void setSscriteria(String sscriteria) {
		this.sscriteria = sscriteria;
		this.doMap();
	}

	
	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public float getNum1() {
		return num1;
	}

	public void setNum1(float num1) {
		this.num1 = num1;
	}

	public float getNum2() {
		return num2;
	}

	public void setNum2(float num2) {
		this.num2 = num2;
	}

	public String getStrvalue() {
		return strvalue;
	}

	public void setStrvalue(String strvalue) {
		this.strvalue = strvalue;
	}
	
}
