package com.ramos.models;

public class Inmueble {
	
	private int id;
	private String tipo;
	private String direccion;
	private String distrito;
	private String departamento;
	private double longitud;
	private double latitud;
	private double precio;
	private int num_dormitorios;
	private int num_banios;
	private double area_total;
	private String estado;
	private String descripcion;
	private String imagen;
	private int usuario_id;
	private int centro_educativo_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getNum_dormitorios() {
		return num_dormitorios;
	}
	public void setNum_dormitorios(int num_dormitorios) {
		this.num_dormitorios = num_dormitorios;
	}
	public int getNum_banios() {
		return num_banios;
	}
	public void setNum_banios(int num_banios) {
		this.num_banios = num_banios;
	}
	public double getArea_total() {
		return area_total;
	}
	public void setArea_total(double area_total) {
		this.area_total = area_total;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getCentro_educativo_id() {
		return centro_educativo_id;
	}
	public void setCentro_educativo_id(int centro_educativo_id) {
		this.centro_educativo_id = centro_educativo_id;
	}
	public Inmueble(int id, String tipo, String direccion, String distrito, String departamento, double longitud,
			double latitud, double precio, int num_dormitorios, int num_banios, double area_total, String estado,
			String descripcion, String imagen, int usuario_id, int centro_educativo_id) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.direccion = direccion;
		this.distrito = distrito;
		this.departamento = departamento;
		this.longitud = longitud;
		this.latitud = latitud;
		this.precio = precio;
		this.num_dormitorios = num_dormitorios;
		this.num_banios = num_banios;
		this.area_total = area_total;
		this.estado = estado;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.usuario_id = usuario_id;
		this.centro_educativo_id = centro_educativo_id;
	}
	public Inmueble() {
		super();
	}
	@Override
	public String toString() {
		return "Inmueble [id=" + id + ", tipo=" + tipo + ", direccion=" + direccion + ", distrito=" + distrito
				+ ", departamento=" + departamento + ", longitud=" + longitud + ", latitud=" + latitud + ", precio="
				+ precio + ", num_dormitorios=" + num_dormitorios + ", num_banios=" + num_banios + ", area_total="
				+ area_total + ", estado=" + estado + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", usuario_id=" + usuario_id + ", centro_educativo_id=" + centro_educativo_id + "]";
	}
}
