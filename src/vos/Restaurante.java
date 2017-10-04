package vos;

import org.codehaus.jackson.annotate.*;

/**
 * clase que representa un restaurante
 * @author angeloMarcetty
 *
 */
public class Restaurante {
	
	//// Atributos
	
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="cuentaBancaria")
	private Long cuentaBancaria;
	
	@JsonProperty(value="personalizable")
	private Boolean personalizable;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="paginaWeb")
	private String paginaWeb;
	
	@JsonProperty(value="representante")
	private String representante;
	
	
	public Restaurante(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="cuentaBancaria")Long cuentaBancaria,
			@JsonProperty(value="personalizable")Boolean personalizable,@JsonProperty(value="descripcion")String descripcion,@JsonProperty(value="tipo")String tipo,
			@JsonProperty(value="paginaWeb")String paginaWeb,@JsonProperty(value="representante")String representante ){
		
		super();
		this.id = id;
		this.nombre = nombre;
		this.cuentaBancaria = cuentaBancaria;
		this.personalizable = personalizable;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.paginaWeb = paginaWeb;	
		this.representante = representante;
		
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


	public Long getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(Long cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}


	public Boolean getPersonalizable() {
		return personalizable;
	}
	public void setPersonalizable(Boolean personalizable) {
		this.personalizable = personalizable;
	}


	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getPaginaWeb() {
		return paginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}


	public String getRepresentante() {
		return representante;
	}


	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	
	
	
	

}
