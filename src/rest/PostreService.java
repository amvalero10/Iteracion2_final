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
import vos.Postre;

/**
 * 
 * @author angeloMarcetty
 *
 */
@Path("restaurantesus/{idRestaurantesus: \\d+}/postres")
public class PostreService {
	
	
	
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
	public Response getPostres(@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		List<Postre> postres;
		try {
			postres = tm.darPostres();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(postres).build();
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");
		}
	}
	
	
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getPostre( @PathParam( "id" ) Long id,@PathParam("idRestaurantesus") Long idRestaurantesus ) throws Exception
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try
		{
			Postre e = tm.buscarPostreId(id);
			return Response.status( 200 ).entity( e ).build( );			
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
	public Response getPostreName( @QueryParam("nombre") String name,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		List<Postre> postres;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Postre no valido");
			postres = tm.buscarPostrePorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(postres).build();
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");
		}
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPostre(Postre postre,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try {
			tm.addPostre(postre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(postre).build();
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
	public Response addPostres(List<Postre> postres,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try {
			tm.addPostres(postres);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(postres).build();
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");
		}
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePostre(Postre postre,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try {
			tm.updatePostre(postre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(postre).build();
		}
		else
		{
			throw new Exception("No tiene permisos para acceder a estos recursos");
		}
	}
	
	
	
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePostre(Postre postre,@PathParam("idRestaurantesus") Long idRestaurantesus) throws Exception {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if (tm.buscarRestauranteUsPorId(idRestaurantesus) != null) 
		{
		try {
			tm.deletePostre(postre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(postre).build();
	}
	else
	{
		throw new Exception("No tiene permisos para acceder a estos recursos");
	}
	}
	
	
	
	
	
	
	
	
	
	

}
