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
@Path("restaurantesus/{idRestaurantesus: \\d+}/acompaniamientos")
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
	public Response getAcompaniamientos(@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
			
		
		List<Acompaniamiento> acompaniamientos;
		try {
			acompaniamientos = tm.darAcompaniamientos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamientos).build();
		
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");

			
		}
			
	}
	
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAcompaniamiento( @PathParam( "id" ) Long id,@PathParam("idRestaurantesus") Long idRestaurantesus ) throws Exception
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );

		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
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
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");			
		}
		
	}
	
	
	
	
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAcompaniamientoName( @QueryParam("nombre") String name,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
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
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");			
		}
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcompaniamiento(Acompaniamiento acompaniamiento,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try {
			tm.addAcompaniamiento(acompaniamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamiento).build();
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");			
		}
	}
	
	
	
	
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcompaniamientos(List<Acompaniamiento> acompaniamientos,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try {
			tm.addAcompaniamientos(acompaniamientos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamientos).build();
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");			
		}
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAcompaniamiento(Acompaniamiento acompaniamiento,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try {
			tm.updateAcompaniamiento(acompaniamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamiento).build();
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");			
		}
	}
	
	
	
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAcompaniamiento(Acompaniamiento acompaniamiento,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try {
			tm.deleteAcompaniamiento(acompaniamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(acompaniamiento).build();
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");			
		}
	}
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
