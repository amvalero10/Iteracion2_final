package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido {
	
	@JsonProperty(value="id")
	private long id;
	
	@JsonProperty(value="idUsuario")
	private long idUsuario;
	
	@JsonProperty(value="mesa")
	private int mesa;
	
	@JsonProperty(value="costo")
	private double costo;
	
	public Pedido(@JsonProperty(value="id")long id, @JsonProperty(value="idUsuario")long idUsuario,
			@JsonProperty(value="mesa")int mesa, @JsonProperty(value="costo")double costo){
		setId(id);
		setIdUsuario(idUsuario);
		setMesa(mesa);
		setCosto(costo);
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
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the mesa
	 */
	public int getMesa() {
		return mesa;
	}

	/**
	 * @param mesa the mesa to set
	 */
	public void setMesa(int mesa) {
		this.mesa = mesa;
	}

	/**
	 * @return the costo
	 */
	public double getCosto() {
		return costo;
	}

	/**
	 * @param costo the costo to set
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}
}
