package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Tarjeta;

public class DAOTablaTarjeta {

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
	public DAOTablaTarjeta() {
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
	public ArrayList<Tarjeta> darTarjetas() throws SQLException, Exception {
		ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();

		String sql = "SELECT * FROM TARJETA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long numero = rs.getLong("NUMERO");
			Integer contrasenia = rs.getInt("CONTRASENIA");
			String fechaExpiracion = rs.getString("FECHAEXPIRACION");
			String tipo = rs.getString("TIPO");
			String nombreBanco = rs.getString("NOMBREBANCO");
			tarjetas.add(new Tarjeta(numero, contrasenia, fechaExpiracion, tipo, nombreBanco));
		}
		return tarjetas;
	}
	
	/**
	 * Metodo que busca el video con el id que entra como parametro.
	 * @param name - Id de el video a buscar
	 * @return Video encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Tarjeta buscarTarjetasPorNumero(Long id) throws SQLException, Exception 
	{
		Tarjeta tarjeta = null;

		String sql = "SELECT * FROM TARJETA WHERE NUMERO =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long numero = rs.getLong("NUMERO");
			Integer contrasenia = rs.getInt("CONTRASENIA");
			String fechaExpiracion = rs.getString("FECHAEXPIRACION");
			String tipo = rs.getString("TIPO");
			String nombreBanco = rs.getString("NOMBREBANCO");
			tarjeta = new Tarjeta(numero, contrasenia, fechaExpiracion, tipo, nombreBanco);
		}

		return tarjeta;
	}

	/**
	 * Metodo que agrega el video que entra como parametro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addTarjeta(Tarjeta tarjeta) throws SQLException, Exception {

		String sql = "INSERT INTO TARJETA VALUES (";
		sql += tarjeta.getNumero() + ",";
		sql += tarjeta.getContrasenia() + ",'";
		sql += tarjeta.getFechaExpiracion() + "','";
		sql += tarjeta.getTipo() + "','";
		sql += tarjeta.getNombreBanco() + "')";

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
	public void updateTarjeta(Tarjeta tarjeta) throws SQLException, Exception {

		String sql = "UPDATE TARJETA SET ";
		sql += "CONTRASENIA=" + tarjeta.getContrasenia() + ",";
		sql += "FECHAEXPIRACION='" + tarjeta.getFechaExpiracion() +"',";
		sql += "TIPO='" + tarjeta.getTipo() +"',";
		sql += "NOMBREBANCO='" + tarjeta.getNombreBanco() +"'";
		sql += " WHERE NUMERO = " + tarjeta.getNumero();


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
	public void deleteTarjeta(Tarjeta tarjeta) throws SQLException, Exception {

		String sql = "DELETE FROM TARJETA";
		sql += " WHERE NUMERO = " + tarjeta.getNumero();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	
	
	
	
}
