package admin.api.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pedido {

	private int id_ped;

	private int id_emp;

	private int id_clie;
	
	private String cliente;
	
	private String serie;

	private String numero;
	
	private Date fecha;
	
	private String fechaStr;

	private String obs;

	private Date fechaentrega;

	private String fechaentregaStr;
	
	private String direcentrega;

	private String notiemail;
	
	private String notifono;
	
	private String estado;

	private float monto;
	
	private float acuenta;
	
	private boolean pagado;
	
	private Cliente clie;
	
	private List<PedidoDet> detalles = new ArrayList<PedidoDet>();
	
	
	public Pedido(){
		
	}

	public int getId_ped() {
		return id_ped;
	}

	public void setId_ped(int id_ped) {
		this.id_ped = id_ped;
	}

	public int getId_emp() {
		return id_emp;
	}

	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}

	public int getId_clie() {
		return id_clie;
	}

	public void setId_clie(int id_clie) {
		this.id_clie = id_clie;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFechaStr() {
		return fechaStr;
	}

	public void setFechaStr(String fechaStr) {
		this.fechaStr = fechaStr;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getFechaentrega() {
		return fechaentrega;
	}

	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}

	public String getFechaentregaStr() {
		return fechaentregaStr;
	}

	public void setFechaentregaStr(String fechaentregaStr) {
		this.fechaentregaStr = fechaentregaStr;
	}

	public String getDirecentrega() {
		return direcentrega;
	}

	public void setDirecentrega(String direcentrega) {
		this.direcentrega = direcentrega;
	}
	
	public String getNotiemail() {
		return notiemail;
	}

	public void setNotiemail(String notiemail) {
		this.notiemail = notiemail;
	}

	public String getNotifono() {
		return notifono;
	}

	public void setNotifono(String notifono) {
		this.notifono = notifono;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public float getAcuenta() {
		return acuenta;
	}

	public void setAcuenta(float acuenta) {
		this.acuenta = acuenta;
	}

	
	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public List<PedidoDet> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<PedidoDet> detalles) {
		this.detalles = detalles;
	}

	public Cliente getClie() {
		return clie;
	}

	public void setClie(Cliente clie) {
		this.clie = clie;
	}


	@Override
	public String toString() {
		return "Pedido [id_ped=" + id_ped + ", id_emp=" + id_emp + ", id_clie=" + id_clie + ", cliente=" + cliente
				+ ", serie=" + serie + ", numero=" + numero + ", fecha=" + fecha + ", obs=" + obs + ", fechaentrega="
				+ fechaentrega + ", direcentrega=" + direcentrega + ", notiemail=" + notiemail + ", notifono="
				+ notifono + ", estado=" + estado + ", monto=" + monto + ", acuenta=" + acuenta + ", pagado=" + pagado
				+ ", clie=" + clie + ", detalles=" + detalles + "]";
	}

	public String sqlInsert(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		return String.format("Insert Into pedidos (id_emp,serie,numero,id_clie,fecha,obs,fechaentrega,direcentrega,notiemail,notifono,estado,monto,acuenta,pagado)"
				+ "Values(%d,'%s','%s',%d,'%s','%s','%s','%s','%s','%s','%s',%f,%f,%b)", 
				this.id_emp,this.serie,this.numero,this.id_clie,sdf.format(this.fecha),this.obs,sdf.format(this.fechaentrega),this.direcentrega,this.notiemail,this.notifono,this.estado,this.monto,this.acuenta,this.pagado);
	}
	
	public String sqlEdit(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		return String.format("Update pedidos Set id_emp=%d,serie='%s',numero='%s',id_clie=%d,fecha='%s',obs='%s',fechaentrega='%s',direcentrega='%s',notiemail='%s',notifono='%s',estado='%s',monto=%f,acuenta=%f,pagado=%b"
				+ " Where (id_ped=%d)", 
				this.id_emp,this.serie,this.numero,this.id_clie,sdf.format(this.fecha),this.obs,sdf.format(this.fechaentrega),this.direcentrega,this.notiemail,this.notifono,this.estado,this.monto,this.acuenta,this.pagado,this.id_ped);
	}
	
	public String sqlDelete(){
		return String.format("Delete From pedidos Where (id_ped=%d)", this.id_ped);
	}
	
	/*
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		//Calendar cal = Calendar.getInstance();
		Date fecha = new Date();
		String s = sdf.format(fecha);
		System.out.println(s);
	}
	*/
}
