package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import vos.Bebida;

/**
 * 
 * @author angeloMarcetty
 *
 */
public class DAOTablaBebida {
	
	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaBebida() {
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
	
	
	public ArrayList<Bebida> darBebidas() throws SQLException, Exception {
		ArrayList<Bebida> bebidas = new ArrayList<Bebida>();

		String sql = "SELECT * FROM BEBIDA";

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
			
			
			Bebida entr = new Bebida(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			bebidas.add(entr);
		}
		return bebidas;
	}
	
	
	
	
	public ArrayList<Bebida> buscarBebidasPorNombre(String name) throws SQLException, Exception {
		ArrayList<Bebida> bebidas = new ArrayList<Bebida>();

		String sql = "SELECT * FROM BEBIDA WHERE NOMBRE ='" + name + "'";

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
			
			
			Bebida entr = new Bebida(id, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);
			bebidas.add(entr);
		
		}

		return bebidas;
	}
	
	
	
	
	public Bebida buscarBebidaPorId(Long id) throws SQLException, Exception 
	{
		Bebida bebida = null;

		String sql = "SELECT * FROM BEBIDA WHERE ID =" + id;

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
			
			
			bebida = new Bebida(id2, nombre, cantidad, personalizable, precioVenta, restaurante, categoria, descripcion, tiempoPrep, precioProd, tipo, descripcionIng, numeroVendidos, traduccion);	
		}

		return bebida;
	}
	
	
	
	
	public void addBebida(Bebida bebida) throws SQLException, Exception {

		String sql = "INSERT INTO BEBIDA VALUES (";
		sql += bebida.getId() + ",'";
		sql += bebida.getNombre() + "',";
		sql += bebida.getCantidad() + ",'";

		if(bebida.getPersonalizable() == true){
			sql += 1+ ",";
		}
		else {
			sql += 0+ ",";
		}
		
		sql += bebida.getPrecioVenta() + ",'";
		sql += bebida.getRestaurante() + "','";
		sql += bebida.getCategoria()+ "','";
		sql += bebida.getDescripcion()+ "',";
		sql += bebida.getTiempoPrep()+ ",";
		sql += bebida.getPrecioProd()+ ",'";
		sql += bebida.getTipo()+ "','";
		sql += bebida.getDescripcion()+ "',";
		sql += bebida.getNumeroVendidos()+ ",'";
		sql += bebida.getTraduccion()+ "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	
	
	public void updateBebida(Bebida bebida) throws SQLException, Exception {

		String sql = "UPDATE BEBIDA SET ";
		sql += "NOMBRE='" + bebida.getNombre() + "',";
		sql += "CANTIDAD=" + bebida.getCantidad()+ ",";

		if(bebida.getPersonalizable() == true){
			sql += "PERSONALIZABLE="+1+",";
		}
		else {
			sql += "PERSONALIZABLE="+0+",";
		}
		
		sql += "PRECIO_VENTA=" + bebida.getPrecioVenta()+ ",";
		sql += "RESTAURANTE='" + bebida.getRestaurante() + "',";
		sql += "CATEGORIA='" + bebida.getCategoria() + "',";
		sql += "DESCRIPCION='" + bebida.getDescripcion() + "',";
		sql += "TIEMPO_PREP=" + bebida.getTiempoPrep()+ ",";
		sql += "PRECIO_PROD=" + bebida.getPrecioProd()+ ",";
		sql += "NUMERO_VENDIDOS=" + bebida.getNumeroVendidos()+ ",";
		sql += "TRADUCCION='" + bebida.getTraduccion() + "'";
		sql += " WHERE ID = " + bebida.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	
	
	
	public void deleteBebida(Bebida bebida) throws SQLException, Exception {

		String sql = "DELETE FROM BEBIDA";
		sql += " WHERE ID = " + bebida.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
