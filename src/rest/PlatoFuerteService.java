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
import vos.PlatoFuerte;

/**
 * 
 * @author angeloMarcetty
 *
 */
@Path("platofuertes")
public class PlatoFuerteService {
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
	public Response getPlatoFuertes() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PlatoFuerte> platoFuertes;
		try {
			platoFuertes = tm.darPlatoFuertes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(platoFuertes).build();
	}
	
	
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getPlatoFuerte( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			PlatoFuerte e = tm.buscarPlatoFuerteId(id);
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
	public Response getPlatoFuerteName( @QueryParam("nombre") String name) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PlatoFuerte> platoFuertes;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del PlatoFuerte no valido");
			platoFuertes = tm.buscarplatoFuertesPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(platoFuertes).build();
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPlatoFuerte(PlatoFuerte platoFuerte) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addPlatoFuerte(platoFuerte);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(platoFuerte).build();
	}
	
	
	
	
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPlatoFuertes(List<PlatoFuerte> platoFuertes) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addPlatoFuertes(platoFuertes);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(platoFuertes).build();
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePlatoFuerte(PlatoFuerte platoFuerte) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updatePlatoFuerte(platoFuerte);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(platoFuerte).build();
	}
	
	
	
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlatoFuerte(PlatoFuerte platoFuerte) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deletePlatoFuerte(platoFuerte);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(platoFuerte).build();
	}
	

	
}
