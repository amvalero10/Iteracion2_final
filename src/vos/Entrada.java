package vos;

import java.sql.Time;

/**
 * 
 * @author angeloMarcetty
 *
 */
public class Entrada extends AbstractAlimento {

	public Entrada(Long id, String nombre, Integer cantidad, Boolean personalizable, Double precioVenta,
			String restaurante, String categoria, String descripcion, Integer tiempoPrep, Double precioProd, String tipo,
			String descripcionIng, Integer numeroVendidos, String traduccion) {
		super(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd,
				tipo, descripcionIng, numeroVendidos, traduccion);
		// TODO Auto-generated constructor stub
	}



}
