package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.RestauranteUs;

public class DAOTablaRestauranteUs {
	
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
	public DAOTablaRestauranteUs() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los videos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM VIDEOS;
	 * @return Arraylist con los videos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<RestauranteUs> darRestaurantesUs() throws SQLException, Exception {
		ArrayList<RestauranteUs> restaurantesUs = new ArrayList<RestauranteUs>();

		String sql = "SELECT * FROM RESTAURANTEUS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String tipoId = rs.getString("TIPOID");
			String nombre = rs.getString("NOMBRE");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			restaurantesUs.add(new RestauranteUs(id, tipoId, nombre, correo, rol));
		}
		return restaurantesUs;
	}

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<RestauranteUs> buscarRestauranteUsPorName(String name) throws SQLException, Exception {
		ArrayList<RestauranteUs> restaurantesUs = new ArrayList<RestauranteUs>();

		String sql = "SELECT * FROM RESTAURANTEUS WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			String tipoId = rs.getString("TIPOID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			restaurantesUs.add(new RestauranteUs(id, tipoId, nombre, correo, rol));
		}

		return restaurantesUs;
	}


	/**
	 * Metodo que busca el video con el id que entra como parametro.
	 * @param name - Id de el video a buscar
	 * @return Video encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public RestauranteUs buscarRestauranteUsPorId(Long id) throws SQLException, Exception 
	{
		RestauranteUs restauranteUs = null;

		String sql = "SELECT * FROM CLIENTEUS WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String nombre = rs.getString("NOMBRE");
			Long id2 = rs.getLong("ID");
			String tipoId = rs.getString("TIPOID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			restauranteUs = new RestauranteUs(id2, tipoId, nombre, correo, rol);
		}

		return restauranteUs;
	}

	/**
	 * Metodo que agrega el video que entra como parametro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addRestauranteUs(RestauranteUs restauranteUs) throws SQLException, Exception {

		String sql = "INSERT INTO RESTAURANTEUS VALUES (";
		sql += restauranteUs.getId() + ",'";
		sql += restauranteUs.getTipoId() + "','";
		sql += restauranteUs.getNombre() + "','";
		sql += restauranteUs.getCorreo() + "','";
		sql += restauranteUs.getRol() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	/**
	 * Metodo que actualiza el video que entra como parametro en la base de datos.
	 * @param video - el video a actualizar. video !=  null
	 * <b> post: </b> se ha actualizado el video en la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el video.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateRestauranteUs(RestauranteUs restauranteUs) throws SQLException, Exception {

		String sql = "UPDATE RESTAURANTEUS SET ";
		sql += "TIPOID='" + restauranteUs.getTipoId() + "',";
		sql += "NOMBRE='" + restauranteUs.getNombre()+ "',";
		sql += "CORREO='" + restauranteUs.getCorreo()+ "',";
		sql += "ROL='" + restauranteUs.getRol() + "'";
		sql += " WHERE ID = " + restauranteUs.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el video que entra como parametro en la base de datos.
	 * @param video - el video a borrar. video !=  null
	 * <b> post: </b> se ha borrado el video en la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el video.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteRestauranteUs(RestauranteUs restauranteUs) throws SQLException, Exception {

		String sql = "DELETE FROM RESTAURANTEUS";
		sql += " WHERE ID = " + restauranteUs.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	
	
	
	
	
	
}
