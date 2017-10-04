package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import vos.Entrada;

/**
 * 
 * @author angeloMarcetty
 *
 */
public  class DAOTablaEntrada {
	
	
	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaEntrada() {
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
	
	
	public ArrayList<Entrada> darEntradas() throws SQLException, Exception {
		ArrayList<Entrada> entradas = new ArrayList<Entrada>();

		String sql = "SELECT * FROM ENTRADA";

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
			
			
			Entrada entr = new Entrada(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			entradas.add(entr);
		}
		return entradas;
	}
	
	
	
	
	public ArrayList<Entrada> buscarEntradasPorNombre(String name) throws SQLException, Exception {
		ArrayList<Entrada> entradas = new ArrayList<Entrada>();

		String sql = "SELECT * FROM ENTRADA WHERE NOMBRE ='" + name + "'";

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
			
			
			Entrada entr = new Entrada(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			entradas.add(entr);
		
		}

		return entradas;
	}
	
	
	
	
	public Entrada buscarEntradaPorId(Long id) throws SQLException, Exception 
	{
		Entrada entrada = null;

		String sql = "SELECT * FROM ENTRADA WHERE ID =" + id;

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
			
			
			entrada = new Entrada(id2, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);	
		}

		return entrada;
	}
	
	
	
	
	public void addEntrada(Entrada entrada) throws SQLException, Exception {

		String sql = "INSERT INTO ENTRADA VALUES (";
		sql += entrada.getId() + ",'";
		sql += entrada.getNombre() + "',";
		sql += entrada.getCantidad() + ",";

		if(entrada.getPersonalizable() == true){
			sql += 1+ ",";
		}
		else {
			sql += 0+ ",";
		}
		
		sql += entrada.getPrecioVenta() + ",'";
		sql += entrada.getRestaurante() + "','";
		sql += entrada.getCategoria()+ "','";
		sql += entrada.getDescripcion()+ "',";
		sql += entrada.getTiempoPrep()+ ",";
		sql += entrada.getPrecioProd()+ ",'";
		sql += entrada.getTipo()+ "','";
		sql += entrada.getDescripcion()+ "',";
		sql += entrada.getNumeroVendidos()+ ",'";
		sql += entrada.getTraduccion()+ "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	
	
	public void updateEntrada(Entrada entrada) throws SQLException, Exception {

		String sql = "UPDATE ENTRADA SET ";
		sql += "NOMBRE='" + entrada.getNombre() + "',";
		sql += "CANTIDAD=" + entrada.getCantidad()+ ",";

		if(entrada.getPersonalizable() == true){
			sql += "PERSONALIZABLE="+1+",";
		}
		else {
			sql += "PERSONALIZABLE="+0+",";
		}
		
		sql += "PRECIO_VENTA=" + entrada.getPrecioVenta()+ ",";
		sql += "RESTAURANTE='" + entrada.getRestaurante() + "',";
		sql += "CATEGORIA='" + entrada.getCategoria() + "',";
		sql += "DESCRIPCION='" + entrada.getDescripcion() + "',";
		sql += "TIEMPO_PREP=" + entrada.getTiempoPrep()+ ",";
		sql += "PRECIO_PROD=" + entrada.getPrecioProd()+ ",";
		sql += "NUMERO_VENDIDOS=" + entrada.getNumeroVendidos()+ ",";
		sql += "TRADUCCION='" + entrada.getTraduccion() + "'";
		sql += " WHERE ID = " + entrada.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	
	
	
	public void deleteEntrada(Entrada entrada) throws SQLException, Exception {

		String sql = "DELETE FROM ENTRADA";
		sql += " WHERE ID = " + entrada.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	

}
