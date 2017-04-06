package admin.api.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ClieBlog {

	private int id_clieb;
	
	private int id_clie;
	
	private int id_emp;
	
	private int id_prod;
	
	private String nivel;
	
	private String comentario;

	private String rate;

	private String fecha;
	
	
	public ClieBlog(){
		
	}
	
	public int getId_clieb() {
		return id_clieb;
	}

	public void setId_clieb(int id_clieb) {
		this.id_clieb = id_clieb;
	}
	
	public int getId_clie() {
		return id_clie;
	}

	public void setId_clie(int id_clie) {
		this.id_clie = id_clie;
	}

	public int getId_emp() {
		return id_emp;
	}

	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}

	public int getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
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

	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ClieBlog [id_clieb=" + id_clieb + ", id_clie=" + id_clie + ", id_emp=" + id_emp + ", id_prod=" + id_prod
				+ ", nivel=" + nivel + ", comentario=" + comentario + ", rate=" + rate + ", fecha=" + fecha + "]";
	}

	private String SqlDate(Date qfecha){
		Calendar cal = new GregorianCalendar();
		cal.setTime(qfecha);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		return String.format("%d-%d-%d %d:%d:%d",year,month,day,hour,min,sec);
	}
	
	public String sqlInsert(){
		Date d = new Date(Long.parseLong(this.fecha));
		return String.format("Insert Into clientesblog (id_clie,id_emp,id_prod,nivel,comentario,rating,fecha)Values(%d,%d,%d,'%s','%s','%s','%s')", this.id_clie,this.id_emp,this.id_prod,this.nivel,this.comentario,this.rate,this.SqlDate(d));
	}
	
	public String sqlEdit(){
		Date d = new Date(Long.parseLong(this.fecha));
		return String.format("Update clientesblog Set id_clie=%d,id_emp=%d,id_prod=%d,nivel='%s',comentario='%s',rating='%s',fecha='%s' Where (id_clieb=%d)", this.id_clie,this.id_emp,this.id_prod,this.nivel,this.comentario,this.rate,this.SqlDate(d),this.id_clieb);
	}
	
	public String sqlDelete(){
		return String.format("Delete From clientesblog Where (id_clieb=%d)",this.id_clieb);
	}
}
