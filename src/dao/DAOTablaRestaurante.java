package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.Restaurante;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 * @author angeloMarcetty
 *
 */
public class DAOTablaRestaurante {
	
	
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;
	
	
	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	
	
	/**
	 * Metodo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaRestaurante() {	
		recursos = new ArrayList<Object>();
	}
	
	/**
	 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
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
	
	
	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}
	
	
	
	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los restaurantes de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM RESTAURANTES;
	 * @return Arraylist con los restaurantes de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Restaurante> darRestaurantes() throws SQLException, Exception {
		ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();

		String sql = "SELECT * FROM RESTAURANTES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			Long cuentaBancaria = rs.getLong("CUENTA_BANCARIA");
			Boolean personalizable = rs.getBoolean("PERSONALIZABLE");
			String descripcion = rs.getString("DESCRIPCION");
			String tipo = rs.getString("TIPO");
			String paginaWeb = rs.getString("PAGINA_WEB");
			
			restaurantes.add(new Restaurante(id, nombre, cuentaBancaria, personalizable, descripcion, tipo, paginaWeb));		
		}
		return restaurantes;
	}
	
	
	
	
	/**
	 * Metodo que busca el/los restaurantes con el nombre que entra como parametro.
	 * @param name - Nombre de el/los restaurantes a buscar
	 * @return ArrayList con los restaurantes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Restaurante> buscarRestauratePorName(String name) throws SQLException, Exception {
		ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();

		String sql = "SELECT * FROM RESTAURANTES WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			Long cuentaBancaria = rs.getLong("CUENTA_BANCARIA");
			Boolean personalizable = rs.getBoolean("PERSONALIZABLE");
			String descripcion = rs.getString("DESCRIPCION");
			String tipo = rs.getString("TIPO");
			String paginaWeb = rs.getString("PAGINA_WEB");
			restaurantes.add(new Restaurante(id, nombre, cuentaBancaria, personalizable, descripcion, tipo, paginaWeb));		

		}

		return restaurantes;
	}
	
	
	
	
	/**
	 * Metodo que busca el restaurante con el id que entra como parametro.
	 * @param id - Id de el video a buscar
	 * @return restaurante encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Restaurante buscarRestaurantePorId(Long id) throws SQLException, Exception 
	{
		Restaurante restaurante = null;

		String sql = "SELECT * FROM RESTAURANTES WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			
			Long id2 = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			Long cuentaBancaria = rs.getLong("CUENTA_BANCARIA");
			Boolean personalizable = rs.getBoolean("PERSONALIZABLE");
			String descripcion = rs.getString("DESCRIPCION");
			String tipo = rs.getString("TIPO");
			String paginaWeb = rs.getString("PAGINA_WEB");
			restaurante = new Restaurante(id2, nombre, cuentaBancaria, personalizable, descripcion, tipo, paginaWeb);		
		}
		return restaurante;
	}
	
	
	/**
	 * Metodo que agrega el restaurante que entra como parametro a la base de datos.
	 * @param restaurante - el restaurante a agregar. restaurante !=  null
	 * <b> post: </b> se ha agregado el restaurante a la base de datos en la transaction actual. pendiente que el restaurante master
	 * haga commit para que el restaurante baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el restaurante a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addRestaurante(Restaurante restaurante) throws SQLException, Exception {

		String sql = "INSERT INTO RESTAURANTES VALUES (";
		sql += restaurante.getId() + ",'";
		sql += restaurante.getNombre() + "',";
		sql += restaurante.getCuentaBancaria() + ",";
		
		if(restaurante.getPersonalizable() == true){
			sql += 1+ ",'";
		}
		else {
			sql += 0+ ",'";
		}
		
		sql += restaurante.getDescripcion()+ "','";
		sql += restaurante.getTipo()+ "','";
		sql += restaurante.getPaginaWeb()+ "')";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	/**
	 * Metodo que actualiza el restaurante que entra como parametro en la base de datos.
	 * @param restaurante - el restaurante a actualizar. restaurante !=  null
	 * <b> post: </b> se ha actualizado el restaurante en la base de datos en la transaction actual. pendiente que el restaurante master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el restaurante.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateRestaurante(Restaurante restaurante) throws SQLException, Exception {

		String sql = "UPDATE RESTAURANTES SET ";
		sql += "NOMBRE='" + restaurante.getNombre() + "',";
		sql += "CUENTA_BANCARIA=" + restaurante.getCuentaBancaria() + ",";

		if(restaurante.getPersonalizable() == true){
			sql += "CUENTA_BANCARIA=" + 1 + ",";
		}
		else {
			sql += "CUENTA_BANCARIA=" + 0 + ",";
		}

		sql += "DESCRIPCION='" + restaurante.getDescripcion() + "',";
		sql += "TIPO='" + restaurante.getTipo() + "',";
		sql += "PAGINA_WEB='" + restaurante.getPaginaWeb() + "'";

		sql += " WHERE ID = " + restaurante.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	/**
	 * Metodo que elimina el restaurante que entra como parametro en la base de datos.
	 * @param restaurante - el restaurante a borrar. restaurante !=  null
	 * <b> post: </b> se ha borrado el restaurante en la base de datos en la transaction actual. pendiente que el restaurante master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteRestaurante(Restaurante restaurante) throws SQLException, Exception {

		String sql = "DELETE FROM RESTAURANTES";
		sql += " WHERE ID = " + restaurante.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	
	
	
	

}
