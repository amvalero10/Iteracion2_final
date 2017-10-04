package vos;

import java.sql.Time;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase abstracta de alimentos -entrada, platoFuerte, ect
 * @author angeloMarcetty
 *
 */
public abstract class AbstractAlimento {
	
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="cantidad")
	private Integer cantidad;
	
	@JsonProperty(value="personalizable")
	private Boolean personalizable;
	
	@JsonProperty(value="precioVenta")
	private Double precioVenta;
	
	@JsonProperty(value="restaurante")
	private String restaurante;
	
	@JsonProperty(value="categoria")
	private String categoria;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="tiempoPrep")
	private Integer tiempoPrep;
	
	@JsonProperty(value="precioProd")
	private Double precioProd;
	
	@JsonProperty(value="tipo")
	private String tipo;

	@JsonProperty(value="descripcionIng")
	private String descripcionIng;
	
	@JsonProperty(value="numeroVendidos")
	private Integer numeroVendidos;
	
	@JsonProperty(value="traduccion")
	private String traduccion;
	
	
	public AbstractAlimento(@JsonProperty(value="id")Long id,@JsonProperty(value="nombre")String nombre,@JsonProperty(value="cantidad")Integer cantidad,@JsonProperty(value="personalizable")Boolean personalizable,
			@JsonProperty(value="precioVenta")Double precioVenta,@JsonProperty(value="restaurante")String restaurante, @JsonProperty(value="categoria")String categoria, @JsonProperty(value="descripcion")String descripcion, @JsonProperty(value="tiempoPrep")Integer tiempoPrep, @JsonProperty(value="precioProd")Double precioProd, @JsonProperty(value="tipo")String tipo,
		@JsonProperty(value="descripcionIng")String descripcionIng, @JsonProperty(value="numeroVendidos")Integer numeroVendidos, @JsonProperty(value="traduccion")String traduccion)
	{
		super();
		setId(id);
		setNombre(nombre);
		setCantidad(cantidad);
		setPersonalizable(personalizable);
		setPrecioVenta(precioVenta);
		setRestaurante(restaurante);
		setCategoria(categoria);
		setDescripcion(descripcion);
		setTiempoPrep(tiempoPrep);
		setPrecioProd(precioProd);
		setTipo(tipo);
		setDescripcionIng(descripcionIng);
		setNumeroVendidos(numeroVendidos);
		setTraduccion(traduccion);	
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	public Boolean getPersonalizable() {
		return personalizable;
	}
	public void setPersonalizable(Boolean personalizable) {
		this.personalizable = personalizable;
	}
	
	
	public Double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}


	public String getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}


	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getTiempoPrep() {
		return tiempoPrep;
	}
	public void setTiempoPrep(Integer tiempoPrep) {
		this.tiempoPrep = tiempoPrep;
	}


	public Double getPrecioProd() {
		return precioProd;
	}
	public void setPrecioProd(Double precioProd) {
		this.precioProd = precioProd;
	}


	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getDescripcionIng() {
		return descripcionIng;
	}
	public void setDescripcionIng(String descripcionIng) {
		this.descripcionIng = descripcionIng;
	}


	public Integer getNumeroVendidos() {
		return numeroVendidos;
	}
	public void setNumeroVendidos(Integer numeroVendidos) {
		this.numeroVendidos = numeroVendidos;
	}


	public String getTraduccion() {
		return traduccion;
	}
	public void setTraduccion(String traduccion) {
		this.traduccion = traduccion;
	}



	
	
	
}
