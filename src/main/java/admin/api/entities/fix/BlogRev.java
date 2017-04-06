package admin.api.entities.fix;

import java.util.Date;

public class BlogRev {

	private int id_prodblog;
	private int id_bc;
	private String blogger;
	private String avatar;
	private Date fecha;
	private String nivel;
	private String comentario;
	private String rating;
	
	public BlogRev(){
		
	}

	public int getId_prodblog() {
		return id_prodblog;
	}

	public void setId_prodblog(int id_prodblog) {
		this.id_prodblog = id_prodblog;
	}

	public int getId_bc() {
		return id_bc;
	}

	public void setId_bc(int id_bc) {
		this.id_bc = id_bc;
	}

	public String getBlogger() {
		return blogger;
	}

	public void setBlogger(String blogger) {
		this.blogger = blogger;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "BlogRev [id_prodblog=" + id_prodblog + ", id_bc=" + id_bc + ", blogger=" + blogger + ", avatar="
				+ avatar + ", fecha=" + fecha + ", nivel=" + nivel + ", comentario=" + comentario + ", rating=" + rating
				+ "]";
	}

	
}
