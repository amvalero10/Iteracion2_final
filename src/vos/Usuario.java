package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public abstract class Usuario {

	@JsonProperty(value="id")
	private long id;
	
	@JsonProperty(value="tipoId")
	private String tipoId;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="correo")
	private String correo;
	
	@JsonProperty(value="rol")
	private String rol;
	
	public Usuario(@JsonProperty(value="id")long id, @JsonProperty(value="tipoId")String tipoId, 
			@JsonProperty(value="nombre")String nombre, @JsonProperty(value="correo")String correo,
			@JsonProperty(value="rol")String rol){
		setId(id);
		setTipoId(tipoId);
		setNombre(nombre);
		setCorreo(correo);
		setRol(rol);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the tipoId
	 */
	public String getTipoId() {
		return tipoId;
	}

	/**
	 * @param tipoId the tipoId to set
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
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
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
}
