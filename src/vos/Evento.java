package vos;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonProperty;

public class Evento {
	
	@JsonProperty(value="fecha")
	private Timestamp fecha;
	
	//@JsonProperty(value="hora")
	//private Time hora;
	
	@JsonProperty(value="cantidad")
	private int cantidad;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	public Evento(@JsonProperty(value="fecha")Timestamp fecha, @JsonProperty(value="cantidad")int cantidad, @JsonProperty(value="nombre")String nombre){
		setFecha(fecha);
		//setHora(hora);
		setCantidad(cantidad);
		setNombre(nombre);
	}

	/**
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hora
	 */
	//public Time getHora() {
		//return hora;
	//}

	/**
	 * @param hora the hora to set
	 */
	//public void setHora(Time hora) {
		//this.hora = hora;
	//}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
	
	
	
}
