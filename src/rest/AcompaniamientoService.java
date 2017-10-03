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
import vos.Acompaniamiento;

/**
 * 
 * @author angeloMarcetty
 *
 */
@Path("acompaniamiento")
public class AcompaniamientoService
{
	
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
	public Response getAcompaniamientos() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Acompaniamiento> acompaniamientos;
		try {
			acompaniamientos = tm.darAcompaniamientos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamientos).build();
	}
	
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAcompaniamiento( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Acompaniamiento a = tm.buscarAcompaniamientoPorId( id );
			return Response.status( 200 ).entity( a ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	
	
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAcompaniamientoName( @QueryParam("nombre") String name) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Acompaniamiento> acompaniamientos;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Acompaniamiento no valido");
			acompaniamientos = tm.buscarAcompaniamientoPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamientos).build();
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcompaniamiento(Acompaniamiento acompaniamiento) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addAcompaniamiento(acompaniamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamiento).build();
	}
	
	
	
	
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcompaniamientos(List<Acompaniamiento> acompaniamientos) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addAcompaniamientos(acompaniamientos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamientos).build();
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAcompaniamiento(Acompaniamiento acompaniamiento) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateAcompaniamiento(acompaniamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamiento).build();
	}
	
	
	
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAcompaniamiento(Acompaniamiento acompaniamiento) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteAcompaniamiento(acompaniamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamiento).build();
	}
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
