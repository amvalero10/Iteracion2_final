package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaClientePreferencia {
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

	public DAOTablaClientePreferencia(){
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

	public void addClienteEntrada(Long id, Long id2) throws SQLException, Exception{
		String sql = "INSERT INTO CLIENTE_ENTRADA VALUES (";
		sql += id + ",";
		sql += id2 + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void addClienteBebida(Long id, Long id2) throws SQLException, Exception{
		String sql = "INSERT INTO CLIENTE_BEBIDA VALUES (";
		sql += id + ",";
		sql += id2 + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void addClientePostre(Long id, Long id2) throws SQLException, Exception{
		String sql = "INSERT INTO CLIENTE_POSTRE VALUES (";
		sql += id + ",";
		sql += id2 + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void addClienteAcomp(Long id, Long id2) throws SQLException, Exception{
		String sql = "INSERT INTO CLIENTE_ACOMP VALUES (";
		sql += id + ",";
		sql += id2 + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void addClientePlato(Long id, Long id2) throws SQLException, Exception{
		String sql = "INSERT INTO CLIENTE_PLATO VALUES (";
		sql += id + ",";
		sql += id2 + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void deleteClienteEntrada(Long id, Long id2)throws SQLException, Exception{
		String sql = "DELETE FROM CLIENTE_ENTRADA";
		sql += " WHERE COD_CLIENTE = " + id; 
		sql +=" AND COD_ENTRADA = " + id2 ;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void deleteClienteBebida(Long id, Long id2)throws SQLException, Exception{
		String sql = "DELETE FROM CLIENTE_BEBIDA";
		sql += " WHERE COD_CLIENTE = " + id; 
		sql +=" AND COD_BEBIDA = " + id2 ;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void deleteClientePostre(Long id, Long id2)throws SQLException, Exception{
		String sql = "DELETE FROM CLIENTE_POSTRE";
		sql += " WHERE COD_CLIENTE = " + id; 
		sql +=" AND COD_POSTRE = " + id2 ;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void deleteClienteAcompaniamiento(Long id, Long id2)throws SQLException, Exception{
		String sql = "DELETE FROM CLIENTE_ACOMP";
		sql += " WHERE COD_CLIENTE = " + id; 
		sql +=" AND COD_ACOMP = " + id2 ;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void deleteClientePlatoFuerte(Long id, Long id2)throws SQLException, Exception{
		String sql = "DELETE FROM CLIENTE_PLATO";
		sql += " WHERE COD_CLIENTE = " + id; 
		sql +=" AND COD_PLATO = " + id2 ;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	
	
}
