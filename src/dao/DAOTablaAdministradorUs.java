package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.AdministradorUs;

public class DAOTablaAdministradorUs {
	
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOAdministrador
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaAdministradorUs() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los admin de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM ADMINISTRADORES;
	 * @return Arraylist con los admin de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<AdministradorUs> darAdministradores() throws SQLException, Exception {
		ArrayList<AdministradorUs> administradores = new ArrayList<AdministradorUs>();

		String sql = "SELECT * FROM ADMINISTRADORUS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String tipoId = rs.getString("TIPOID");
			String nombre = rs.getString("NOMBRE");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			administradores.add(new AdministradorUs(id, tipoId, nombre, correo, rol));
		}
		return administradores;
	}

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los admin a buscar
	 * @return ArrayList con los admin encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<AdministradorUs> buscarAdministradorPorName(String name) throws SQLException, Exception {
		ArrayList<AdministradorUs> administradores = new ArrayList<AdministradorUs>();

		String sql = "SELECT * FROM ADMINISTRADORUS WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			String tipoId = rs.getString("TIPOID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			administradores.add(new AdministradorUs(id, tipoId, nombre, correo, rol));
		}
		return administradores;
	}
	
	/**
	 * Metodo que busca el admin con el id que entra como parametro.
	 * @param name - Id de el admin a buscar
	 * @return AdministradorUs encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public AdministradorUs buscarAdministradorPorId(Long id) throws SQLException, Exception 
	{
		AdministradorUs administrador = null;

		String sql = "SELECT * FROM ADMINISTRADORUS WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String nombre = rs.getString("NOMBRE");
			Long id2 = rs.getLong("ID");
			String tipoId = rs.getString("TIPOID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			administrador = new AdministradorUs(id2, tipoId, nombre, correo, rol);
		}

		return administrador;
	}

	/**
	 * Metodo que agrega el admin que entra como parametro a la base de datos.
	 * @param video - el admin a agregar. video !=  null
	 * <b> post: </b> se ha agregado el admin a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el admin baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el admin a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAdministrador(AdministradorUs administradorUs) throws SQLException, Exception {

		String sql = "INSERT INTO ADMINISTRADORUS VALUES (";
		sql += administradorUs.getId() + ",'";
		sql += administradorUs.getTipoId() + "','";
		sql += administradorUs.getNombre() + "','";
		sql += administradorUs.getCorreo() + "','";
		sql += administradorUs.getRol() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	/**
	 * Metodo que actualiza el admin que entra como parametro en la base de datos.
	 * @param admin - el admin a actualizar. video !=  null
	 * <b> post: </b> se ha actualizado el admin en la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el admin.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAdministrador(AdministradorUs administrador) throws SQLException, Exception {

		String sql = "UPDATE CLIENTEUS SET ";
		sql += "TIPOID='" + administrador.getTipoId() + "',";
		sql += "NOMBRE='" + administrador.getNombre() + "',";
		sql += "CORREO='" + administrador.getCorreo() + "',";
		sql += "ROL='" + administrador.getRol() + "'";
		sql += " WHERE ID = " + administrador.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el admin que entra como parametro en la base de datos.
	 * @param admin - el admin a borrar. video !=  null
	 * <b> post: </b> se ha borrado el admin en la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el video.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAdministrador(AdministradorUs administrador) throws SQLException, Exception {

		String sql = "DELETE FROM ADMINISTRADORUS";
		sql += " WHERE ID = " + administrador.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}


	
	
}
