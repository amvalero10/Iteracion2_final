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
import vos.Entrada;

/**
 * 
 * @author angeloMarcetty
 *
 */
@Path("entradas")
public class EntradaServices {
	
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
	public Response getEntradas() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Entrada> entradas;
		try {
			entradas = tm.darEntradas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(entradas).build();
	}
	
	
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getEntrada( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Entrada e = tm.buscarEntradaPorId( id );
			return Response.status( 200 ).entity( e ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	
	
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getEntradaName( @QueryParam("nombre") String name) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Entrada> entradas;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre de la Entrada no valido");
			entradas = tm.buscarEntradaPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(entradas).build();
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEntrada(Entrada entrada) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addEntrada(entrada);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(entrada).build();
	}
	
	
	
	
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEntradas(List<Entrada> entradas) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addEntradas(entradas);;
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(entradas).build();
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEntrada(Entrada entrada) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateEntrada(entrada);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(entrada).build();
	}
	
	
	
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEntrada(Entrada entrada) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteEntrada(entrada);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(entrada).build();
	}
	

}
