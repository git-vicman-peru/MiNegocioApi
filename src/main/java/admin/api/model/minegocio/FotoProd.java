package admin.api.model.minegocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="fotos")
public class FotoProd {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_fot;
	@Column
	private int id_emp;
	/*
	@Column
	private int id_prod;
	*/
	@Column
	private String nombre;
	@Column
	private String tipo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_prod", nullable=false)
	@JsonIgnoreProperties(value={"fotos"}, allowSetters=true)
	private Producto prod;
	
	public FotoProd(){
		
	}

	public Producto getProd() {
		return prod;
	}

	public void setProd(Producto prod) {
		this.prod = prod;
	}

	public int getId_fot() {
		return id_fot;
	}

	public void setId_fot(int id_fot) {
		this.id_fot = id_fot;
	}

	public int getId_emp() {
		return id_emp;
	}

	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}

	/*
	public int getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	*/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "FotoProd [id_fot=" + id_fot + ", id_emp=" + id_emp + ", nombre=" + nombre + ", tipo=" + tipo + ",";// foto="
				//+ foto + "]";
	}
	
	
}
