package admin.api.model.minegocio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="productos")
public class Producto {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_prod;
	@Column
	private int id_emp;
	@Column
	private String grupo;
	@Column
	private String subgrupo;
	@Column
	private String codigo;
	@Column
	private String nombre;
	@Column
	private String descripcionlarga;
	@Column
	private String descripcioncorta;
	@Column
	private String obs;
	@Column
	private float stock;
	@Column
	private String estado;
	@Column
	private int fotoidx;
	@Column
	private String unidadprecios;
	@Column
	private int unidadidx;
	
	@OneToMany(targetEntity=FotoProd.class, mappedBy="prod", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonIgnoreProperties(value={"prod"}, allowSetters=true)
	private Set<FotoProd> fotos = new HashSet<FotoProd>(0);
	
	public Set<FotoProd> getFotos() {
		return fotos;
	}

	public void setFotos(Set<FotoProd> fotos) {
		this.fotos = fotos;
	}

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

	@Override
	public String toString() {
		return "Producto [id_prod=" + id_prod + ", id_emp=" + id_emp + ", grupo=" + grupo + ", subgrupo=" + subgrupo
				+ ", codigo=" + codigo + ", nombre=" + nombre + ", descripcionlarga=" + descripcionlarga
				+ ", descripcioncorta=" + descripcioncorta + ", obs=" + obs + ", stock=" + stock + ", estado=" + estado
				+ ", fotoidx=" + fotoidx + ", unidadprecios=" + unidadprecios + ", unidadidx=" + unidadidx
				+ ", " + "]";//prodfotos=" + prodfotos + "]";
	}
	
	
}
