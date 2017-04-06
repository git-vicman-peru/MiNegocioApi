package admin.api.entities.fix;

public class DocDefInfo {
	private int id_cfg;
	private String serie;
	private String numero;
	private String formato;
	
	public DocDefInfo(){
		
	}

	public int getId_cfg() {
		return id_cfg;
	}

	public void setId_cfg(int id_cfg) {
		this.id_cfg = id_cfg;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	@Override
	public String toString() {
		return "DocDefInfo [id_cfg=" + id_cfg + ", serie=" + serie + ", numero=" + numero + ", formato=" + formato
				+ "]";
	}
	
}
