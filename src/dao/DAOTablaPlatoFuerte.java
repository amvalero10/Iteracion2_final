package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import vos.PlatoFuerte;
/**
 * 
 * @author angeloMarcetty
 *
 */
public class DAOTablaPlatoFuerte {
	
	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaPlatoFuerte() {
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
	
	
	
	public ArrayList<PlatoFuerte> darPlatosFuertes() throws SQLException, Exception {
		ArrayList<PlatoFuerte> platosFuertes = new ArrayList<PlatoFuerte>();

		String sql = "SELECT * FROM PLATO_FUERTE";

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
			
			
			PlatoFuerte plf = new PlatoFuerte(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			platosFuertes.add(plf);
		}
		return platosFuertes;
	}
	
	
	
	
	public ArrayList<PlatoFuerte> buscarPlatoFuertePorNombre(String name) throws SQLException, Exception {
		ArrayList<PlatoFuerte> platosFuertes = new ArrayList<PlatoFuerte>();

		String sql = "SELECT * FROM PLATO_FUERTE WHERE NOMBRE ='" + name + "'";

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
			
			
			PlatoFuerte plf = new PlatoFuerte(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			platosFuertes.add(plf);
		
		}

		return platosFuertes;
	}
	
	
	
	
	public PlatoFuerte buscarPlatoFuertePorId(Long id) throws SQLException, Exception 
	{
		PlatoFuerte platoFuerte = null;

		String sql = "SELECT * FROM PLATO_FUERTE WHERE ID =" + id;

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
			
			
			platoFuerte = new PlatoFuerte(id2, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);	
		}

		return platoFuerte;
	}
	
	
	
	
	public void addPlatoFuerte(PlatoFuerte platoFuerte) throws SQLException, Exception {

		String sql = "INSERT INTO PLATO_FUERTE VALUES (";
		sql += platoFuerte.getId() + ",'";
		sql += platoFuerte.getNombre() + "',";
		sql += platoFuerte.getCantidad() + ",'";

		if(platoFuerte.getPersonalizable() == true){
			sql += 1+ ",";
		}
		else {
			sql += 0+ ",";
		}
		
		sql += platoFuerte.getPrecioVenta() + ",'";
		sql += platoFuerte.getRestaurante() + "','";
		sql += platoFuerte.getCategoria()+ "','";
		sql += platoFuerte.getDescripcion()+ "',";
		sql += platoFuerte.getTiempoPrep()+ ",";
		sql += platoFuerte.getPrecioProd()+ ",'";
		sql += platoFuerte.getTipo()+ "','";
		sql += platoFuerte.getDescripcion()+ "',";
		sql += platoFuerte.getNumeroVendidos()+ ",'";
		sql += platoFuerte.getTraduccion()+ "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	
	
	public void updatePlatoFuerte(PlatoFuerte platoFuerte) throws SQLException, Exception {

		String sql = "UPDATE PLATO_FUERTE SET ";
		sql += "NOMBRE='" + platoFuerte.getNombre() + "',";
		sql += "CANTIDAD=" + platoFuerte.getCantidad()+ ",";

		if(platoFuerte.getPersonalizable() == true){
			sql += "PERSONALIZABLE="+1+",";
		}
		else {
			sql += "PERSONALIZABLE="+0+",";
		}
		
		sql += "PRECIO_VENTA=" + platoFuerte.getPrecioVenta()+ ",";
		sql += "RESTAURANTE='" + platoFuerte.getRestaurante() + "',";
		sql += "CATEGORIA='" + platoFuerte.getCategoria() + "',";
		sql += "DESCRIPCION='" + platoFuerte.getDescripcion() + "',";
		sql += "TIEMPO_PREP=" + platoFuerte.getTiempoPrep()+ ",";
		sql += "PRECIO_PROD=" + platoFuerte.getPrecioProd()+ ",";
		sql += "NUMERO_VENDIDOS=" + platoFuerte.getNumeroVendidos()+ ",";
		sql += "TRADUCCION='" + platoFuerte.getTraduccion() + "'";
		sql += " WHERE ID = " + platoFuerte.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	
	
	
	public void deletePlatoFuerte(PlatoFuerte platoFuerte) throws SQLException, Exception {

		String sql = "DELETE FROM PLATO_FUERTE";
		sql += " WHERE ID = " + platoFuerte.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	


}
