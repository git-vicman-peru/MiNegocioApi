package admin.api.model.minegocioadm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="accesos")
public class Acceso {

	@Column
	@Id
	private int id_acc;
	
	@Column
	private String usuario;
	@Column
	private String clave;
	@Column
	private Boolean vigente;
	
	public Acceso(){
		
	}

	public int getId_acc() {
		return id_acc;
	}

	public void setId_acc(int id_acc) {
		this.id_acc = id_acc;
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

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	@Override
	public String toString() {
		return "Acceso [id_acc=" + id_acc + ", usuario=" + usuario + ", clave=" + clave + ", vigente=" + vigente + "]";
	}
	
}
