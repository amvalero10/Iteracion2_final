package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaEntrada;
import dao.DAOTablaRestaurante;
import vos.Entrada;
import vos.Restaurante;
import vos.AdministradorUs;
import vos.ClienteUs;
import vos.Evento;
import vos.Pedido;
import vos.RestauranteUs;
import vos.Tarjeta;
import vos.Zona;
import dao.DAOTablaAdministradorUs;
import dao.DAOTablaClienteUs;
import dao.DAOTablaEventos;
import dao.DAOTablaPedido;
import dao.DAOTablaRestauranteUs;
import dao.DAOTablaTarjeta;
import dao.DAOTablaZona;



/**
 * Transaction Manager de la aplicacion (TM)
 * @author am.valero10
 *
 */

public class RotondAndesTM {
	
	
	/**
	 * Atributo estatico que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";
	
	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private  String connectionDataPath;
	
	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;
	
	
	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;
	
	
	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;
	
	
	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;
	
	/**
	 * conexion a la base de datos
	 */
	private Connection conn;
	
	
	
	/**
	 * Metodo constructor de la clase RotondAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logica de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto VideoAndesTM, se inicializa el path absoluto del archivo de conexion y se
	 * inicializa los atributos que se usan par la conexion a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public RotondAndesTM(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	
	
	/**
	 * Metodo que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexion a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo que  retorna la conexion a la base de datos
	 * @return Connection - la conexion a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}
	
	
	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////
	

	//aqui van los metodos para traer los dao
	
	// Evento
		public List<Evento> darEventos() throws Exception {
			List<Evento> eventos;
			DAOTablaEventos daoEventos = new DAOTablaEventos();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEventos.setConn(conn);
				eventos = daoEventos.darEventos();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEventos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return eventos;
		}
		
		/**
		 * Metodo que modela la transaccion que busca el/los videos en la base de datos con el nombre entra como parametro.
		 * @param name - Nombre del video a buscar. name != null
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<Evento> buscarEventosPorName(String name) throws Exception {
			List<Evento> eventos;
			DAOTablaEventos daoEventos = new DAOTablaEventos();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEventos.setConn(conn);
				eventos = daoEventos.buscarEventosPorName(name);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEventos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return eventos;
		}

		/**
		 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como parametro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genere agregando el video
		 */
		public void addEvento(Evento evento) throws Exception {
			DAOTablaEventos daoEventos = new DAOTablaEventos();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEventos.setConn(conn);
				daoEventos.addEvento(evento);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEventos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como parametro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addEventos(List<Evento> eventos) throws Exception {
			DAOTablaEventos daoEventos = new DAOTablaEventos();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoEventos.setConn(conn);
				Iterator<Evento> it = eventos.iterator();
				while(it.hasNext())
				{
					daoEventos.addEvento(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoEventos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como parametro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updateEvento(Evento evento) throws Exception {
			DAOTablaEventos daoEventos = new DAOTablaEventos();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEventos.setConn(conn);
				daoEventos.updateEvento(evento);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEventos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}


		/**
		 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como parametro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deleteEvento(Evento evento) throws Exception {
			DAOTablaEventos daoEventos = new DAOTablaEventos();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEventos.setConn(conn);
				daoEventos.deleteEvento(evento);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEventos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		//Pedido
		
		/**
		 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<Pedido> darPedidos() throws Exception {
			List<Pedido> pedidos;
			DAOTablaPedido daoPedidos = new DAOTablaPedido();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoPedidos.setConn(conn);
				pedidos = daoPedidos.darPedidos();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoPedidos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return pedidos;
		}

		/**
		 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
		 * @param name - Id del video a buscar. name != null
		 * @return Video - Resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public Pedido buscarPedidoPorId(Long id) throws Exception {
			Pedido pedido;
			DAOTablaPedido daoPedido = new DAOTablaPedido();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoPedido.setConn(conn);
				pedido = daoPedido.buscarPedidoPorId(id);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoPedido.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return pedido;
		}
		
		/**
		 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como parametro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genere agregando el video
		 */
		public void addPedido(Pedido pedido) throws Exception {
			DAOTablaPedido daoPedido = new DAOTablaPedido();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoPedido.setConn(conn);
				daoPedido.addPedido(pedido);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoPedido.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como parametro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addPedidos(List<Pedido> pedidos) throws Exception {
			DAOTablaPedido daoPedido = new DAOTablaPedido();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoPedido.setConn(conn);
				Iterator<Pedido> it = pedidos.iterator();
				while(it.hasNext())
				{
					daoPedido.addPedido(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoPedido.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como parametro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updatePedido(Pedido pedido) throws Exception {
			DAOTablaPedido daoPedidos = new DAOTablaPedido();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoPedidos.setConn(conn);
				daoPedidos.updatePedido(pedido);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoPedidos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		/**
		 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como parametro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deletePedido(Pedido pedido) throws Exception {
			DAOTablaPedido daoPedidos = new DAOTablaPedido();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoPedidos.setConn(conn);
				daoPedidos.deletePedido(pedido);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoPedidos.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		//Tarjeta
		/**
		 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<Tarjeta> darTarjetas() throws Exception {
			List<Tarjeta> tarjetas;
			DAOTablaTarjeta daoTarjetas = new DAOTablaTarjeta();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoTarjetas.setConn(conn);
				tarjetas = daoTarjetas.darTarjetas();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoTarjetas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return tarjetas;
		}

			
		/**
		 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
		 * @param name - Id del video a buscar. name != null
		 * @return Video - Resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public Tarjeta buscarTarjetaPorNumero(Long id) throws Exception {
			Tarjeta tarjeta;
			DAOTablaTarjeta daoTarjetas = new DAOTablaTarjeta();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoTarjetas.setConn(conn);
				tarjeta = daoTarjetas.buscarTarjetasPorNumero(id);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoTarjetas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return tarjeta;
		}
		
		/**
		 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como parametro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genere agregando el video
		 */
		public void addTarjeta(Tarjeta tarjeta) throws Exception {
			DAOTablaTarjeta daoTarjeta = new DAOTablaTarjeta();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoTarjeta.setConn(conn);
				daoTarjeta.addTarjeta(tarjeta);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoTarjeta.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como parametro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addTarjetas(List<Tarjeta> tarjetas) throws Exception {
			DAOTablaTarjeta daoTarjetas = new DAOTablaTarjeta();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoTarjetas.setConn(conn);
				Iterator<Tarjeta> it = tarjetas.iterator();
				while(it.hasNext())
				{
					daoTarjetas.addTarjeta(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoTarjetas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como parametro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updateTarjeta(Tarjeta tarjeta) throws Exception {
			DAOTablaTarjeta daoTarjetas = new DAOTablaTarjeta();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoTarjetas.setConn(conn);
				daoTarjetas.updateTarjeta(tarjeta);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoTarjetas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		/**
		 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como parametro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deleteTarjeta(Tarjeta tarjeta) throws Exception {
			DAOTablaTarjeta daoTarjeta = new DAOTablaTarjeta();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoTarjeta.setConn(conn);
				daoTarjeta.deleteTarjeta(tarjeta);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoTarjeta.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		//Zona
		
		/**
		 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<Zona> darZonas() throws Exception {
			List<Zona> zonas;
			DAOTablaZona daoZonas = new DAOTablaZona();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoZonas.setConn(conn);
				zonas = daoZonas.darZonas();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoZonas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return zonas;
		}

		/**
		 * Metodo que modela la transaccion que busca el/los videos en la base de datos con el nombre entra como parametro.
		 * @param name - Nombre del video a buscar. name != null
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<Zona> buscarZonasPorName(String name) throws Exception {
			List<Zona> zonas;
			DAOTablaZona daoZonas = new DAOTablaZona();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoZonas.setConn(conn);
				zonas = daoZonas.buscarZonasPorName(name);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoZonas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return zonas;
		}
		
			/**
		 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como parametro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genere agregando el video
		 */
		public void addZona(Zona zona) throws Exception {
			DAOTablaZona daoZonas = new DAOTablaZona();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoZonas.setConn(conn);
				daoZonas.addZona(zona);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoZonas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como parametro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addZonas(List<Zona> zonas) throws Exception {
			DAOTablaZona daoZonas = new DAOTablaZona();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoZonas.setConn(conn);
				Iterator<Zona> it = zonas.iterator();
				while(it.hasNext())
				{
					daoZonas.addZona(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoZonas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como parametro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updateZona(Zona zona) throws Exception {
			DAOTablaZona daoZona = new DAOTablaZona();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoZona.setConn(conn);
				daoZona.updateZona(zona);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoZona.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		/**
		 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como parametro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deleteZona(Zona zona) throws Exception {
			DAOTablaZona daoZona = new DAOTablaZona();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoZona.setConn(conn);
				daoZona.deleteVideo(zona);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoZona.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		//AdministradorUs
		/**
		 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<AdministradorUs> darAdministradoresUs() throws Exception {
			List<AdministradorUs> administradores;
			DAOTablaAdministradorUs daoAdministradorUs = new DAOTablaAdministradorUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoAdministradorUs.setConn(conn);
				administradores = daoAdministradorUs.darAdministradores();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoAdministradorUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return administradores;
		}

		/**
		 * Metodo que modela la transaccion que busca el/los videos en la base de datos con el nombre entra como parametro.
		 * @param name - Nombre del video a buscar. name != null
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<AdministradorUs> buscarAdministradoresUsPorName(String name) throws Exception {
			List<AdministradorUs> administradores;
			DAOTablaAdministradorUs daoAdministradoresUs = new DAOTablaAdministradorUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoAdministradoresUs.setConn(conn);
				administradores = daoAdministradoresUs.buscarAdministradorPorName(name);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoAdministradoresUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return administradores;
		}
		
		/**
		 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
		 * @param name - Id del video a buscar. name != null
		 * @return Video - Resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public AdministradorUs buscarAdministradorUsPorId(Long id) throws Exception {
			AdministradorUs administrador;
			DAOTablaAdministradorUs daoAdministradoresUs = new DAOTablaAdministradorUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoAdministradoresUs.setConn(conn);
				administrador = daoAdministradoresUs.buscarAdministradorPorId(id);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoAdministradoresUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return administrador;
		}
		
		/**
		 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como parametro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genere agregando el video
		 */
		public void addAdministradorUs(AdministradorUs administrador) throws Exception {
			DAOTablaAdministradorUs daoAdministradorUs = new DAOTablaAdministradorUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoAdministradorUs.setConn(conn);
				daoAdministradorUs.addAdministrador(administrador);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoAdministradorUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como parametro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addAdministradoresUs(List<AdministradorUs> administradores) throws Exception {
			DAOTablaAdministradorUs daoAdministradorUs = new DAOTablaAdministradorUs();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoAdministradorUs.setConn(conn);
				Iterator<AdministradorUs> it = administradores.iterator();
				while(it.hasNext())
				{
					daoAdministradorUs.addAdministrador(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoAdministradorUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como parametro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updateAdministradorUs(AdministradorUs administradorUs) throws Exception {
			DAOTablaAdministradorUs daoAdministradorUs = new DAOTablaAdministradorUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoAdministradorUs.setConn(conn);
				daoAdministradorUs.updateAdministrador(administradorUs);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoAdministradorUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		/**
		 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como parametro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deleteAdministradorUs(AdministradorUs administrador) throws Exception {
			DAOTablaAdministradorUs daoAdministradorUs = new DAOTablaAdministradorUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoAdministradorUs.setConn(conn);
				daoAdministradorUs.deleteAdministrador(administrador);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoAdministradorUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		//ClienteUs
		
		/**
		 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<ClienteUs> darClientes() throws Exception {
			List<ClienteUs> clientes;
			DAOTablaClienteUs daoclientesUs = new DAOTablaClienteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoclientesUs.setConn(conn);
				clientes = daoclientesUs.darClientes();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoclientesUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return clientes;
		}

		/**
		 * Metodo que modela la transaccion que busca el/los videos en la base de datos con el nombre entra como parametro.
		 * @param name - Nombre del video a buscar. name != null
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<ClienteUs> buscarClientesUsPorName(String name) throws Exception {
			List<ClienteUs> clientes;
			DAOTablaClienteUs daoClienteUs = new DAOTablaClienteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoClienteUs.setConn(conn);
				clientes = daoClienteUs.buscarClientePorName(name);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoClienteUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return clientes;
		}
		
		/**
		 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
		 * @param name - Id del video a buscar. name != null
		 * @return Video - Resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public ClienteUs buscarClienteUsPorId(Long id) throws Exception {
			ClienteUs cliente;
			DAOTablaClienteUs daoClienteUs = new DAOTablaClienteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoClienteUs.setConn(conn);
				cliente = daoClienteUs.buscarClientePorId(id);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoClienteUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return cliente;
		}
		
		/**
		 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como parametro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genere agregando el video
		 */
		public void addClienteUs(ClienteUs cliente) throws Exception {
			DAOTablaClienteUs daoClienteUs = new DAOTablaClienteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoClienteUs.setConn(conn);
				daoClienteUs.addCliente(cliente);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoClienteUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como parametro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addClientesUs(List<ClienteUs> clientes) throws Exception {
			DAOTablaClienteUs daoClientesUs = new DAOTablaClienteUs();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoClientesUs.setConn(conn);
				Iterator<ClienteUs> it = clientes.iterator();
				while(it.hasNext())
				{
					daoClientesUs.addCliente(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoClientesUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como parametro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updateClienteUs(ClienteUs cliente) throws Exception {
			DAOTablaClienteUs daoClientes = new DAOTablaClienteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoClientes.setConn(conn);
				daoClientes.updateCliente(cliente);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoClientes.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		/**
		 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como parametro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deleteClienteUs(ClienteUs cliente) throws Exception {
			DAOTablaClienteUs daoClientes = new DAOTablaClienteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoClientes.setConn(conn);
				daoClientes.deleteCliente(cliente);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoClientes.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		//RestauranteUs
		
		/**
		 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<RestauranteUs> darRestaurantesUs() throws Exception {
			List<RestauranteUs> restaurantesUs;
			DAOTablaRestauranteUs daoRestaurantes = new DAOTablaRestauranteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurantes.setConn(conn);
				restaurantesUs = daoRestaurantes.darRestaurantesUs();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurantes.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return restaurantesUs;
		}

		/**
		 * Metodo que modela la transaccion que busca el/los videos en la base de datos con el nombre entra como parametro.
		 * @param name - Nombre del video a buscar. name != null
		 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<RestauranteUs> buscarRestauranteUsPorName(String name) throws Exception {
			List<RestauranteUs> restaurantesUs;
			DAOTablaRestauranteUs daoRestaurantesUs = new DAOTablaRestauranteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurantesUs.setConn(conn);
				restaurantesUs = daoRestaurantesUs.buscarRestauranteUsPorName(name);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurantesUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return restaurantesUs;
		}
		
		/**
		 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
		 * @param name - Id del video a buscar. name != null
		 * @return Video - Resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public RestauranteUs buscarRestauranteUsPorId(Long id) throws Exception {
			RestauranteUs restauranteUs;
			DAOTablaRestauranteUs daoRestauranteUs = new DAOTablaRestauranteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestauranteUs.setConn(conn);
				restauranteUs = daoRestauranteUs.buscarRestauranteUsPorId(id);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestauranteUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return restauranteUs;
		}
		
		/**
		 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
		 * <b> post: </b> se ha agregado el video que entra como parametro
		 * @param video - el video a agregar. video != null
		 * @throws Exception - cualquier error que se genere agregando el video
		 */
		public void addRestauranteUs(RestauranteUs restauranteUs) throws Exception {
			DAOTablaRestauranteUs daoRestauranteUs = new DAOTablaRestauranteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestauranteUs.setConn(conn);
				daoRestauranteUs.addRestauranteUs(restauranteUs);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestauranteUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
		 * <b> post: </b> se han agregado los videos que entran como parametro
		 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void addRestaurantesUs(List<RestauranteUs> restaurantes) throws Exception {
			DAOTablaRestauranteUs daoRestaurantesUs = new DAOTablaRestauranteUs();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoRestaurantesUs.setConn(conn);
				Iterator<RestauranteUs> it = restaurantes.iterator();
				while(it.hasNext())
				{
					daoRestaurantesUs.addRestauranteUs(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoRestaurantesUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		/**
		 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha actualizado el video que entra como parametro
		 * @param video - Video a actualizar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void updateRestauranteUs(RestauranteUs restauranteUs) throws Exception {
			DAOTablaRestauranteUs daoRestaurantesUs = new DAOTablaRestauranteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurantesUs.setConn(conn);
				daoRestaurantesUs.updateRestauranteUs(restauranteUs);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurantesUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		/**
		 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
		 * <b> post: </b> se ha eliminado el video que entra como parametro
		 * @param video - Video a eliminar. video != null
		 * @throws Exception - cualquier error que se genera actualizando los videos
		 */
		public void deleteRestauranteUs(RestauranteUs restaurante) throws Exception {
			DAOTablaRestauranteUs daoRestauranteUs = new DAOTablaRestauranteUs();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestauranteUs.setConn(conn);
				daoRestauranteUs.deleteRestauranteUs(restaurante);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestauranteUs.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/**
		 * Metodo que modela la transaccion que retorna todos los restaurantes de la base de datos.
		 * @return ListaRestaurantes - objeto que modela  un arreglo de restaurantes. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<Restaurante> darRestaurantes() throws Exception {
			List<Restaurante> restaurantes;
			DAOTablaRestaurante daoRestaurante = new DAOTablaRestaurante();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurante.setConn(conn);
				restaurantes = daoRestaurante.darRestaurantes();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurante.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return restaurantes;
		}
		
		
		
		/**
		 * Metodo que modela la transaccion que busca el/los restaurantes en la base de datos con el nombre entra como parametro.
		 * @param name - Nombre del restaurante a buscar. name != null
		 * @return ListaRestaurantes - objeto que modela  un arreglo de restaurantes. este arreglo contiene el resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public List<Restaurante> buscarRestaurantePorName(String name) throws Exception {
			List<Restaurante> restaurantes;
			DAOTablaRestaurante daoRestaurantes = new DAOTablaRestaurante();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurantes.setConn(conn);
				restaurantes = daoRestaurantes.buscarRestauratePorName(name);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurantes.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return restaurantes;
		}
		
		
		
		
		/**
		 * Metodo que modela la transaccion que busca el restaurante en la base de datos con el id que entra como parametro.
		 * @param name - Id del restaurante a buscar. name != null
		 * @return restaurante - Resultado de la busqueda
		 * @throws Exception -  cualquier error que se genere durante la transaccion
		 */
		public Restaurante buscarRestaurantePorId(Long id) throws Exception {
			Restaurante restaurante;
			DAOTablaRestaurante daoRestaurantes = new DAOTablaRestaurante();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurantes.setConn(conn);
				restaurante = daoRestaurantes.buscarRestaurantePorId(id);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurantes.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return restaurante;
		}
		
		
		
		
		/**
		 * Metodo que modela la transaccion que agrega un solo Restaurante a la base de datos.
		 * <b> post: </b> se ha agregado el Restaurante que entra como parametro
		 * @param Restaurante - el Restaurante a agregar. Restaurante != null
		 * @throws Exception - cualquier error que se genere agregando el Restaurante
		 */
		public void addRestaurante(Restaurante restaurante) throws Exception {
			DAOTablaRestaurante daoRestaurantes = new DAOTablaRestaurante();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurantes.setConn(conn);
				daoRestaurantes.addRestaurante(restaurante);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurantes.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		/**
		 * Metodo que modela la transaccion que agrega los Restaurantes que entran como parametro a la base de datos.
		 * <b> post: </b> se han agregado los Restaurantes que entran como parametro
		 * @param Restaurante - objeto que modela una lista de Restaurantes y se estos se pretenden agregar. Restaurantes != null
		 * @throws Exception - cualquier error que se genera agregando los videos
		 */
		public void  addRestaurantes(List<Restaurante> restaurantes) throws Exception {
			DAOTablaRestaurante daoRestaurantes = new DAOTablaRestaurante();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoRestaurantes.setConn(conn);
				Iterator<Restaurante> it = restaurantes.iterator();
				while(it.hasNext())
				{
					daoRestaurantes.addRestaurante(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoRestaurantes.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		
		
		
		
		/**
		 * Metodo que modela la transaccion que actualiza el Restaurante que entra como parametro a la base de datos.
		 * <b> post: </b> se ha actualizado el Restaurante que entra como parametro
		 * @param Restaurante - Restaurante a actualizar. Restaurante != null
		 * @throws Exception - cualquier error que se genera actualizando
		 */
		public void updateRestaurante(Restaurante restaurante) throws Exception {
			DAOTablaRestaurante daoRestaurante = new DAOTablaRestaurante();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurante.setConn(conn);
				daoRestaurante.updateRestaurante(restaurante);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurante.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		
		
		
		
		
		/**
		 * Metodo que modela la transaccion que elimina el Restaurante que entra como parametro a la base de datos.
		 * <b> post: </b> se ha eliminado el Restaurante que entra como parametro
		 * @param Restaurante - Restaurante a eliminar. Restaurante != null
		 * @throws Exception - cualquier error que se genera.
		 */
		public void deleteRestaurante(Restaurante restaurante) throws Exception {
			DAOTablaRestaurante daoRestaurante = new DAOTablaRestaurante();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoRestaurante.setConn(conn);
				daoRestaurante.deleteRestaurante(restaurante);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoRestaurante.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		public List<Entrada> darEntradas() throws Exception {
			List<Entrada> entradas;
			DAOTablaEntrada daoEntrada = new DAOTablaEntrada();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEntrada.setConn(conn);
				entradas = daoEntrada.darEntradas();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEntrada.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return entradas;
		}
		
		
		
		
		public List<Entrada> buscarEntradaPorName(String name) throws Exception {
			List<Entrada> entradas;
			DAOTablaEntrada daoEntrada = new DAOTablaEntrada();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEntrada.setConn(conn);
				entradas = daoEntrada.buscarEntradasPorNombre(name);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEntrada.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return entradas;
		}
		
		
		
		
		
		
		public Entrada buscarEntradaPorId(Long id) throws Exception {
			Entrada entrada;
			DAOTablaEntrada daoEntrada = new DAOTablaEntrada();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEntrada.setConn(conn);
				entrada = daoEntrada.buscarEntradaPorId(id);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEntrada.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return entrada;
		}
		
		
		
		
		
		
		
		
		
		
		
		public void addEntrada(Entrada entrada) throws Exception {
			DAOTablaEntrada daoEntrada = new DAOTablaEntrada();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEntrada.setConn(conn);
				daoEntrada.addEntrada(entrada);
				conn.commit();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEntrada.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		
		
		
		
		
		public void addEntradas(List<Entrada> entradas) throws Exception {
			DAOTablaEntrada daoEntradas = new DAOTablaEntrada();
			try 
			{
				//////transaccion - ACID Example
				this.conn = darConexion();
				conn.setAutoCommit(false);
				daoEntradas.setConn(conn);
				Iterator<Entrada> it = entradas.iterator();
				while(it.hasNext())
				{
					daoEntradas.addEntrada(it.next());
				}
				
				conn.commit();
			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				try {
					daoEntradas.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		
		
		
		
		public void updateEntrada(Entrada entrada) throws Exception {
			DAOTablaEntrada daoEntrada = new DAOTablaEntrada();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEntrada.setConn(conn);
				daoEntrada.updateEntrada(entrada);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEntrada.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		
		
		
		
		
		
		public void deleteEntrada(Entrada entrada) throws Exception {
			DAOTablaEntrada daoEntrada = new DAOTablaEntrada();
			try 
			{
				//////transaccion
				this.conn = darConexion();
				daoEntrada.setConn(conn);
				daoEntrada.deleteEntrada(entrada);

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoEntrada.cerrarRecursos();
					if(this.conn!=null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}
	
	
	
}
