package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author angeloMarcetty
 *
 */
public class Menu 
{
	
	@JsonProperty(value="idRestaurante")
	private long idRestaurante;
	
	@JsonProperty(value="idAlimento")
	private long idAlimento;
	
	public Menu(@JsonProperty(value="idRestaurante") long idRestaurante, @JsonProperty(value="idAlimento") long idAlimento)
	{
		super();
	this.idRestaurante = idRestaurante;
	this.idAlimento = idAlimento;
		
	}

	public long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}
	
	
	

}
