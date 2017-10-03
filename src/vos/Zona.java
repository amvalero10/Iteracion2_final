package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Zona {
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="condiciones")
	private String condiciones;
	
	@JsonProperty(value="tipoEspacio")
	private String tipoEspacio;
	
	@JsonProperty(value="apto")
	private boolean apto;
	
	@JsonProperty(value="capacidad")
	private int capacidad;
	
	public Zona(String nombre, String condiciones, String tipoEspacio, boolean apto, int capacidad){
		setNombre(nombre);
		setCondiciones(condiciones);
		setTipoEspacio(tipoEspacio);
		setApto(apto);
		setCapacidad(capacidad);
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the condiciones
	 */
	public String getCondiciones() {
		return condiciones;
	}

	/**
	 * @param condiciones the condiciones to set
	 */
	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}

	/**
	 * @return the apto
	 */
	public boolean isApto() {
		return apto;
	}

	/**
	 * @param apto the apto to set
	 */
	public void setApto(boolean apto) {
		this.apto = apto;
	}

	/**
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return the tipoEspacio
	 */
	public String getTipoEspacio() {
		return tipoEspacio;
	}

	/**
	 * @param tipoEspacio the tipoEspacio to set
	 */
	public void setTipoEspacio(String tipoEspacio) {
		this.tipoEspacio = tipoEspacio;
	}
}
