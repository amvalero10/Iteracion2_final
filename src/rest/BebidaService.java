package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.Bebida;
/**
 * 
 * @author angeloMarcetty
 *
 */
@Path("bebidas")
public class BebidaService {
	
	
	
	@Context
	private ServletContext context;
	
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBebidas() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Bebida> bebidas;
		try {
			bebidas = tm.darBebidas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(bebidas).build();
	}
	
	
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getBebida( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Bebida b = tm.buscarBebidaPorId(id);
			return Response.status( 200 ).entity( b ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	
	
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getBebidaName( @QueryParam("nombre") String name) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Bebida> bebidas;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre de la Bebida no valido");
			bebidas = tm.buscarBebidaPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(bebidas).build();
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBebida(Bebida bebida) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addBebida(bebida);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(bebida).build();
	}
	
	
	
	
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBebidas(List<Bebida> bebidas) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addBebidas(bebidas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(bebidas).build();
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBebida(Bebida bebida) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateBebida(bebida);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(bebida).build();
	}
	
	
	
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBebida(Bebida bebida) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteBebida(bebida);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(bebida).build();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

