package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClienteUs extends Usuario{

	
	private ArrayList<Pedido> pedidos;
	public ClienteUs(@JsonProperty(value="id")long id, @JsonProperty(value="tipoId")String tipoId, 
			@JsonProperty(value="nombre")String nombre, @JsonProperty(value="correo")String correo,
			@JsonProperty(value="rol")String rol) {
		super(id, tipoId, nombre, correo, rol);
		setPedidos(new ArrayList<Pedido>());
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the pedidos
	 */
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	/**
	 * @param pedidos the pedidos to set
	 */
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
