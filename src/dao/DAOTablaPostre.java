package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import vos.Postre;


/**
 * 
 * @author angeloMarcetty
 *
 */
public class DAOTablaPostre {

	
	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaPostre() {
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
	
	
	public ArrayList<Postre> darPostres() throws SQLException, Exception {
		ArrayList<Postre> postres = new ArrayList<Postre>();

		String sql = "SELECT * FROM POSTRE";

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
			Time tiempoPrep = rs.getTime("TIEMPO_PREP");
			Double precioProd = rs.getDouble("TIEMPO_PROD");
			String tipo = rs.getString("TIPO");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			Integer numeroVendidos = rs.getInt("NUMERO_VENDIDOS");
			String traduccion = rs.getString("TRADUCCION");
			
			
			Postre entr = new Postre(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			postres.add(entr);
		}
		return postres;
	}
	
	
	
	
	public ArrayList<Postre> buscarPostresPorNombre(String name) throws SQLException, Exception {
		ArrayList<Postre> postres = new ArrayList<Postre>();

		String sql = "SELECT * FROM POSTRE WHERE NOMBRE ='" + name + "'";

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
			Time tiempoPrep = rs.getTime("TIEMPO_PREP");
			Double precioProd = rs.getDouble("TIEMPO_PROD");
			String tipo = rs.getString("TIPO");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			Integer numeroVendidos = rs.getInt("NUMERO_VENDIDOS");
			String traduccion = rs.getString("TRADUCCION");
			
			
			Postre entr = new Postre(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			postres.add(entr);
		
		}

		return postres;
	}
	
	
	
	
	public Postre buscarPostrePorId(Long id) throws SQLException, Exception 
	{
		Postre postre = null;

		String sql = "SELECT * FROM POSTRE WHERE ID =" + id;

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
			Time tiempoPrep = rs.getTime("TIEMPO_PREP");
			Double precioProd = rs.getDouble("TIEMPO_PROD");
			String tipo = rs.getString("TIPO");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			Integer numeroVendidos = rs.getInt("NUMERO_VENDIDOS");
			String traduccion = rs.getString("TRADUCCION");
			
			
			postre = new Postre(id2, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);	
		}

		return postre;
	}
	
	
	
	
	public void addPostre(Postre postre) throws SQLException, Exception {

		String sql = "INSERT INTO POSTRE VALUES (";
		sql += postre.getId() + ",'";
		sql += postre.getNombre() + "',";
		sql += postre.getCantidad() + ",'";

		if(postre.getPersonalizable() == true){
			sql += 1+ ",";
		}
		else {
			sql += 0+ ",";
		}
		
		sql += postre.getPrecioVenta() + ",'";
		sql += postre.getRestaurante() + "','";
		sql += postre.getCategoria()+ "','";
		sql += postre.getDescripcion()+ "',";
		sql += postre.getTiempoPrep()+ ",";
		sql += postre.getPrecioProd()+ ",'";
		sql += postre.getTipo()+ "','";
		sql += postre.getDescripcion()+ "',";
		sql += postre.getNumeroVendidos()+ ",'";
		sql += postre.getTraduccion()+ "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	
	
	public void updatePostre(Postre postre) throws SQLException, Exception {

		String sql = "UPDATE POSTRE SET ";
		sql += "NOMBRE='" + postre.getNombre() + "',";
		sql += "CANTIDAD=" + postre.getCantidad()+ ",";

		if(postre.getPersonalizable() == true){
			sql += "PERSONALIZABLE="+1+",";
		}
		else {
			sql += "PERSONALIZABLE="+0+",";
		}
		
		sql += "PRECIO_VENTA=" + postre.getPrecioVenta()+ ",";
		sql += "RESTAURANTE='" + postre.getRestaurante() + "',";
		sql += "CATEGORIA='" + postre.getCategoria() + "',";
		sql += "DESCRIPCION='" + postre.getDescripcion() + "',";
		sql += "TIEMPO_PREP=" + postre.getTiempoPrep()+ ",";
		sql += "PRECIO_PROD=" + postre.getPrecioProd()+ ",";
		sql += "NUMERO_VENDIDOS=" + postre.getNumeroVendidos()+ ",";
		sql += "TRADUCCION='" + postre.getTraduccion() + "'";
		sql += " WHERE ID = " + postre.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	
	
	
	public void deletePostre(Postre postre) throws SQLException, Exception {

		String sql = "DELETE FROM POSTRE";
		sql += " WHERE ID = " + postre.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
