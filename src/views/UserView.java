package views;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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
import javax.ws.rs.core.UriInfo;
import org.apache.naming.NamingContext;
import resources.*;
import controllers.*;
@Path("user")
public class UserView {
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String sayPlain() {
//		return "MAX LA CHUPA";
//	}
	//Crear nuevo usuario
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User_resource user) throws SQLException {
		User_controller controller= new User_controller();
		String location=controller.createUser(user);
		if(location!="") {
			return Response.status(Response.Status.CREATED).entity(user).
				header("Location", location).header("Content-Location", location).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo crear el garaje").build();
		
		
		
	}
	
}
