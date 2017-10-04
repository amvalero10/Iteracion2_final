package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import vos.Acompaniamiento;

/**
 * 
 * @author angeloMarcetty
 *
 */
public class DAOTablaAcompaniamiento {
	
	
	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaAcompaniamiento() {
		recursos = new ArrayList<Object>();
	}
	
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	
	
	public void setConn(Connection con){
		this.conn = con;
	}
	
	
	public ArrayList<Acompaniamiento> darAcompaniamientos() throws SQLException, Exception {
		ArrayList<Acompaniamiento> acompaniamientos = new ArrayList<Acompaniamiento>();

		String sql = "SELECT * FROM ACOMPANIAMIENTO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			Integer cantidad = rs.getInt("CANTIDAD");
			Boolean personalizable = rs.getBoolean("PERSONALIZABLE");
			Double precioVenta = rs.getDouble("PRECIO_VENTA");
			String restaurante = rs.getString("RESTAURANTE");
			String categoria = rs.getString("CATEGORIA");
			String descripcion = rs.getString("DESCRIPCION");
			Integer tiempoPrep = rs.getInt("TIEMPO_PREP");
			Double precioProd = rs.getDouble("PRECIO_PROD");
			String tipo = rs.getString("TIPO");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			Integer numeroVendidos = rs.getInt("NUMERO_VENDIDOS");
			String traduccion = rs.getString("TRADUCCION");
			
			
			Acompaniamiento entr = new Acompaniamiento(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			acompaniamientos.add(entr);
		}
		return acompaniamientos;
	}
	
	
	
	
	public ArrayList<Acompaniamiento> buscarAcompaniamientosPorNombre(String name) throws SQLException, Exception {
		ArrayList<Acompaniamiento> acompaniamientos = new ArrayList<Acompaniamiento>();

		String sql = "SELECT * FROM ACOMPANIAMIENTO WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			Integer cantidad = rs.getInt("CANTIDAD");
			Boolean personalizable = rs.getBoolean("PERSONALIZABLE");
			Double precioVenta = rs.getDouble("PRECIO_VENTA");
			String restaurante = rs.getString("RESTAURANTE");
			String categoria = rs.getString("CATEGORIA");
			String descripcion = rs.getString("DESCRIPCION");
			Integer tiempoPrep = rs.getInt("TIEMPO_PREP");
			Double precioProd = rs.getDouble("PRECIO_PROD");
			String tipo = rs.getString("TIPO");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			Integer numeroVendidos = rs.getInt("NUMERO_VENDIDOS");
			String traduccion = rs.getString("TRADUCCION");
			
			
			Acompaniamiento entr = new Acompaniamiento(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			acompaniamientos.add(entr);
		
		}

		return acompaniamientos;
	}
	
	
	
	
	public Acompaniamiento buscarAcompaniamientoPorId(Long id) throws SQLException, Exception 
	{
		Acompaniamiento acompaniamiento = null;

		String sql = "SELECT * FROM ACOMPANIAMIENTO WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			Integer cantidad = rs.getInt("CANTIDAD");
			Boolean personalizable = rs.getBoolean("PERSONALIZABLE");
			Double precioVenta = rs.getDouble("PRECIO_VENTA");
			String restaurante = rs.getString("RESTAURANTE");
			String categoria = rs.getString("CATEGORIA");
			String descripcion = rs.getString("DESCRIPCION");
			Integer tiempoPrep = rs.getInt("TIEMPO_PREP");
			Double precioProd = rs.getDouble("PRECIO_PROD");
			String tipo = rs.getString("TIPO");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			Integer numeroVendidos = rs.getInt("NUMERO_VENDIDOS");
			String traduccion = rs.getString("TRADUCCION");
			
			
			acompaniamiento = new Acompaniamiento(id2, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);	
		}

		return acompaniamiento;
	}
	
	
	
	
	public void addAcompaniamiento(Acompaniamiento acompanimiento) throws SQLException, Exception {

		String sql = "INSERT INTO ACOMPANIAMIENTO VALUES (";
		sql += acompanimiento.getId() + ",'";
		sql += acompanimiento.getNombre() + "',";
		sql += acompanimiento.getCantidad() + ",'";

		if(acompanimiento.getPersonalizable() == true){
			sql += 1+ ",";
		}
		else {
			sql += 0+ ",";
		}
		
		sql += acompanimiento.getPrecioVenta() + ",'";
		sql += acompanimiento.getRestaurante() + "','";
		sql += acompanimiento.getCategoria()+ "','";
		sql += acompanimiento.getDescripcion()+ "',";
		sql += acompanimiento.getTiempoPrep()+ ",";
		sql += acompanimiento.getPrecioProd()+ ",'";
		sql += acompanimiento.getTipo()+ "','";
		sql += acompanimiento.getDescripcion()+ "',";
		sql += acompanimiento.getNumeroVendidos()+ ",'";
		sql += acompanimiento.getTraduccion()+ "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	
	
	public void updateAcompaniamiento(Acompaniamiento acompaniamiento) throws SQLException, Exception {

		String sql = "UPDATE ACOMPANIAMIENTO SET ";
		sql += "NOMBRE='" + acompaniamiento.getNombre() + "',";
		sql += "CANTIDAD=" + acompaniamiento.getCantidad()+ ",";

		if(acompaniamiento.getPersonalizable() == true){
			sql += "PERSONALIZABLE="+1+",";
		}
		else {
			sql += "PERSONALIZABLE="+0+",";
		}
		
		sql += "PRECIO_VENTA=" + acompaniamiento.getPrecioVenta()+ ",";
		sql += "RESTAURANTE='" + acompaniamiento.getRestaurante() + "',";
		sql += "CATEGORIA='" + acompaniamiento.getCategoria() + "',";
		sql += "DESCRIPCION='" + acompaniamiento.getDescripcion() + "',";
		sql += "TIEMPO_PREP=" + acompaniamiento.getTiempoPrep()+ ",";
		sql += "PRECIO_PROD=" + acompaniamiento.getPrecioProd()+ ",";
		sql += "NUMERO_VENDIDOS=" + acompaniamiento.getNumeroVendidos()+ ",";
		sql += "TRADUCCION='" + acompaniamiento.getTraduccion() + "'";
		sql += " WHERE ID = " + acompaniamiento.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	
	
	
	public void deleteAcompaniamiento(Acompaniamiento acompaniamiento) throws SQLException, Exception {

		String sql = "DELETE FROM ACOMPANIAMIENTO";
		sql += " WHERE ID = " + acompaniamiento.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
