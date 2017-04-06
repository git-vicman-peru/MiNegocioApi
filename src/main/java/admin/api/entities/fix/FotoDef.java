package admin.api.entities.fix;

public class FotoDef {

	private String rawdef;
	private String logos;
	private String fondos;
	
	public FotoDef(){
		
	}

	public FotoDef(String rawDef){
		this.rawdef = rawDef;
	}

	public String getRawdef() {
		return rawdef;
	}

	public void setRawdef(String rawdef) {
		this.rawdef = rawdef;
	}

	public String getLogos() {
		return logos;
	}

	public void setLogos(String logos) {
		this.logos = logos;
	}

	public String getFondos() {
		return fondos;
	}

	public void setFondos(String fondos) {
		this.fondos = fondos;
	}
	
	public boolean hasLogos(){
		if (this.logos == null) return false;
		if (this.logos.isEmpty()) return false;
		return true;
	}
	
	public boolean hasFondos(){
		if (this.fondos == null) return false;
		if (this.fondos.isEmpty()) return false;
		return true;
	}
	
	public String getSS(){
		return "logos:"+this.logos+"Øfotos:"+this.fondos;
	}

	@Override
	public String toString() {
		return "FotoDef [rawdef=" + rawdef + ", logos=" + logos + ", fondos=" + fondos + "]";
	}
	
	
}
