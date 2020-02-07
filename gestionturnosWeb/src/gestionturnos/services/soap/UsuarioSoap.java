package gestionturnos.services.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import gestionturnos.model.entities.Usuario;
import gestionturnos.model.manager.ManagerUsuario;



@WebService(serviceName = "UsuarioSoap")
public class UsuarioSoap {
	@EJB
	private ManagerUsuario mUsuario;
	
	@WebMethod(operationName = "findAllUsuario")
	public List<Usuario> findAllUsuario(){
		return mUsuario.findAllUsuario();
	}
	
	@WebMethod(operationName = "findAllUsuario2")
	public List<Usuario> findAllUsuario(@WebParam(name = "codigo") int id){
		return mUsuario.findAllUsuario();
	}
	
// 	@WebMethod(operationName = "crearUsuario")
//	public String crearUsuario(@WebParam(name = "usuario") Usuario usuario) {
//		String resultado=mUsuario.crearUsuario(usuario);
//		return resultado;
//	}
// 	
 	@WebMethod(operationName = "eliminarUsuarioR")
	public String eliminarUsuarioR(@WebParam(name = "usuario") int idusu) {
		String resultado=mUsuario.eliminarUsuarioR(idusu);
		return resultado;
	}
}
