package vos;

import java.sql.Time;

/**
 * 
 * @author angeloMarcetty
 *
 */
public class Acompaniamiento extends AbstractAlimento {

	public Acompaniamiento(Long id, String nombre, Integer cantidad, Boolean personalizable, Double precioVenta,
			String restaurante, String categoria, String descripcion, Time tiempoPrep, Double precioProd, String tipo,
			String descripcionIng, Integer numeroVendidos, String traduccion) {
		super(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd,
				tipo, descripcionIng, numeroVendidos, traduccion);
		// TODO Auto-generated constructor stub
	}

	
}
