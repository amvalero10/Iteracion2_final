package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Ingrediente;

/**
 * 
 * @author angeloMarcetty
 *
 */
public class DAOTablaIngrediente {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaIngrediente() {
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
	
	
	public ArrayList<Ingrediente> darIngredientes() throws SQLException, Exception {
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

		String sql = "SELECT * FROM INGREDIENTE";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			String tipo = rs.getString("TIPO");
			String descripcionEsp = rs.getString("DESCRIPCION_ESP");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			String traduccion = rs.getString("TRADUCCION");


			
			
			Ingrediente entr = new Ingrediente(id, nombre, tipo, descripcionEsp, descripcionIng,traduccion);
			ingredientes.add(entr);
		}
		return ingredientes;
	}
	
	
	
	
	public ArrayList<Ingrediente> buscarIngredientePorNombre(String name) throws SQLException, Exception {
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

		String sql = "SELECT * FROM INGREDIENTE WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			String tipo = rs.getString("TIPO");
			String descripcionEsp = rs.getString("DESCRIPCION_ESP");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			String traduccion = rs.getString("TRADUCCION");
			
			
			Ingrediente entr = new Ingrediente(id, nombre, tipo, descripcionEsp, descripcionIng, traduccion);
			ingredientes.add(entr);
		
		}

		return ingredientes;
	}
	
	
	
	
	public Ingrediente buscarIngredientePorId(Long id) throws SQLException, Exception 
	{
		Ingrediente ingrediente = null;

		String sql = "SELECT * FROM INGREDIENTE WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			String tipo = rs.getString("TIPO");
			String descripcionEsp = rs.getString("DESCRIPCION_ESP");
			String descripcionIng = rs.getString("DESCRIPCION_ING");
			String traduccion = rs.getString("TRADUCCION");
			
			ingrediente = new Ingrediente(id2, nombre, tipo, descripcionEsp, descripcionIng,traduccion);
			
		}

		return ingrediente;
	}
	
	
	
	
	public void addIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

		String sql = "INSERT INTO INGREDIENTE VALUES (";
		sql += ingrediente.getId() + ",'";
		sql += ingrediente.getNombre() + "','";
		sql += ingrediente.getTipo() + "','";
		sql += ingrediente.getDescripcionEsp() + "','";
		sql += ingrediente.getDescripcionIng() + "','";
		sql += ingrediente.getTraduccion() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	
	
	public void updateIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

		String sql = "UPDATE INGREDIENTE SET ";
		sql += "NOMBRE='" + ingrediente.getNombre() + "',";
		sql += "TIPO='" + ingrediente.getTipo() + "',";
		sql += "DESCRIPCION_ESP='" + ingrediente.getDescripcionEsp() + "',";
		sql += "DESCRIPCION_ING='" + ingrediente.getDescripcionIng() + "',";
		sql += "TRADUCCION='" + ingrediente.getTraduccion()+ "',";

		sql += " WHERE ID = " + ingrediente.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	
	
	
	public void deleteIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

		String sql = "DELETE FROM INGREDIENTE";
		sql += " WHERE ID = " + ingrediente.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	

}
