package admin.api.entities;

import java.util.List;

public class Producto {

	private int id_prod;

	private int id_emp;

	private String grupo;

	private String subgrupo;

	private String codigo;

	private String nombre;

	private String descripcionlarga;

	private String descripcioncorta;

	private String obs;

	private float stock;

	private String estado;

	private int fotoidx;

	private String unidadprecios;

	private int unidadidx;
	
	private List<FotoProd> fotos;
	

	public Producto(){
		
	}

	public int getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}

	public int getId_emp() {
		return id_emp;
	}

	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcionlarga() {
		return descripcionlarga;
	}

	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
	}

	public String getDescripcioncorta() {
		return descripcioncorta;
	}

	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getFotoidx() {
		return fotoidx;
	}

	public void setFotoidx(int fotoidx) {
		this.fotoidx = fotoidx;
	}

	public String getUnidadprecios() {
		return unidadprecios;
	}

	public void setUnidadprecios(String unidadprecios) {
		this.unidadprecios = unidadprecios;
	}

	public int getUnidadidx() {
		return unidadidx;
	}

	public void setUnidadidx(int unidadidx) {
		this.unidadidx = unidadidx;
	}

	public List<FotoProd> getFotos() {
		return fotos;
	}

	public void setFotos(List<FotoProd> fotos) {
		this.fotos = fotos;
	}
	
	@Override
	public String toString() {
		return "Producto [id_prod=" + id_prod + ", id_emp=" + id_emp + ", grupo=" + grupo + ", subgrupo=" + subgrupo
				+ ", codigo=" + codigo + ", nombre=" + nombre + ", descripcionlarga=" + descripcionlarga
				+ ", descripcioncorta=" + descripcioncorta + ", obs=" + obs + ", stock=" + stock + ", estado=" + estado
				+ ", fotoidx=" + fotoidx + ", unidadprecios=" + unidadprecios + ", unidadidx=" + unidadidx
				+ ", " + "]";//prodfotos=" + prodfotos + "]";
	}
	
	
}
