package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Tarjeta {
	
	@JsonProperty(value="numero")
	private long numero;
	
	@JsonProperty(value="contrasenia")
	private int contrasenia;
	
	@JsonProperty(value="fechaExpiracion")
	private String fechaExpiracion;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="nombreBanco")
	private String nombreBanco;
	
	public Tarjeta(@JsonProperty(value="numero")long numero, @JsonProperty(value="contrasenia")int contrasenia,
			@JsonProperty(value="fechaExpiracion")String fechaExpiracion, @JsonProperty(value="tipo")String tipo,
			@JsonProperty(value="nombreBanco")String nombreBanco){
		setNumero(numero);
		setContrasenia(contrasenia);
		setFechaExpiracion(fechaExpiracion);
		setTipo(tipo);
		setNombreBanco(nombreBanco);
	}

	/**
	 * @return the numero
	 */
	public long getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(long numero) {
		this.numero = numero;
	}

	/**
	 * @return the contrasenia
	 */
	public int getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(int contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the fechaExpiracion
	 */
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	/**
	 * @param fechaExpiracion the fechaExpiracion to set
	 */
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the nombreBanco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * @param nombreBanco the nombreBanco to set
	 */
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	
}
