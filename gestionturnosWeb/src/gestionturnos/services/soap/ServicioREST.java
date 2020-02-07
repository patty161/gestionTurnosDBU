package gestionturnos.services.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import gestionturnos.model.entities.Usuario;
import gestionturnos.model.manager.ManagerUsuario;



@RequestScoped
@Path("/consulta")
@Produces("application/json")
@Consumes("application/json")
public class ServicioREST {

	@EJB
	private ManagerUsuario managerUsuario;
	
	
	@GET
	public String saludar() {
		return "{\"Saludo\":\"Hola mundo\"}";
	}
	
	@GET
	@Path("/usuarios")
	public List<Usuario> consultaUsuario(){
		return managerUsuario.findAllUsuario(); 
		
	}
	
	@GET
	@Path("/{idUsuario}")
	public Usuario findUsuario(@PathParam("idUsuario") int idUsuario) {
		return managerUsuario.findUsuarioById(idUsuario);
	}
	
	@POST
	public String crearUsuario(Usuario usuario) {
		String resultado=managerUsuario.crearUsuario(usuario);
		return "{\"resultado\":\""+resultado+"\"}";
	}
	
	@DELETE
	@Path("/{nombreUsuario}")
	public String eliminarUsuario(@PathParam("nombreUsuario") int idUsuario) {
		String resultado=managerUsuario.eliminarUsuarioR(idUsuario);
		return "{\"resultado\":\""+resultado+"\"}";
	}
	
}
