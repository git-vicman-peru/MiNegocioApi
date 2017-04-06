package admin.api.entities;

import admin.api.entities.fix.FotoDef;


public class Empresa {

	private int id_emp;

	private int id_sus;

	private String razonsocial;

	private String ruc;

	private String fonos;

	private String direccion;

	private String url;

	private String email;

	private String coordenadas;

	private String facebook;

	private String distrito;

	private String ciudad;

	private String pais;

	private String obs;

	private String usuario;

	private String clave;

	private String pregunta;

	private String respuesta;

	private String fotos;
	
	public Empresa(){
		
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}
	
	public int getId_sus() {
		return id_sus;
	}

	public void setId_sus(int id_sus) {
		this.id_sus = id_sus;
	}

	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public String getPregunta() {
		return pregunta;
	}


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	public int getId_emp() {
		return id_emp;
	}

	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}

	public String getRazonsocial() {
		return razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getFonos() {
		return fonos;
	}

	public void setFonos(String fonos) {
		this.fonos = fonos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	@Override
	public String toString() {
		return "Empresa [id_emp=" + id_emp + ", id_sus=" + id_sus + ", razonsocial=" + razonsocial + ", ruc=" + ruc
				+ ", fonos=" + fonos + ", direccion=" + direccion + ", url=" + url + ", email=" + email
				+ ", coordenadas=" + coordenadas + ", facebook=" + facebook + ", distrito=" + distrito + ", ciudad="
				+ ciudad + ", pais=" + pais + ", obs=" + obs + ", usuario=" + usuario + ", clave=" + clave
				+ ", pregunta=" + pregunta + ", respuesta=" + respuesta + ", fotos=" + fotos + "]";
	}

	public boolean hasRawFotos(){
		if (this.fotos == null) return false;
		if (this.fotos.isEmpty()) return false;
		return true;
	}
	
	public FotoDef parseFotos(){
		FotoDef f = new FotoDef();
		String[] a, b;
		try{
			f.setRawdef(this.fotos);
			a = this.fotos.split("Ø");
		}catch(Exception ex){
			ex.printStackTrace();
			return f;
		}
		try{
			b = a[0].split(":");
			f.setLogos(b[1]);
		}catch(Exception ex){
			
		}
		try{
			b = a[1].split(":");
			f.setFondos(b[1]);
		}catch(Exception ex){
			
		}
		return f;
	}
	
}
