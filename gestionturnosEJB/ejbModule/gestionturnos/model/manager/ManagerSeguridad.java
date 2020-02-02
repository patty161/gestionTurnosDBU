package gestionturnos.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import gestionturnos.model.dto.LoginDTO;
import gestionturnos.model.entities.Usuario;

/**
 * Implementa la logica de manejo de usuarios y autenticacion
 */
@Stateless
@LocalBean
public class ManagerSeguridad {
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerAuditoria managerAuditoria;
	@EJB
	private ManagerUsuario managerUsuario;

	/**
	 * Default constructor.
	 */
	public ManagerSeguridad() {

	}

	/**
	 * Metodo que le permite a un usuario acceder al sistema.
	 * 
	 * @param codigoUsuario Identificador del usuario.
	 * @param clave         Clave de acceso.
	 * @return Objeto LoginDTO con informacion del usuario para la sesion.
	 * @throws Exception Cuando no coincide la clave proporcionada o si ocurrio un
	 *                   error con la consulta a la base de datos.
	 */

	public LoginDTO accederSistema(String codigoUsuario, String clave) throws Exception {
		Usuario usuario = (Usuario) managerDAO.findById(Usuario.class, codigoUsuario);

		if (usuario == null)
			throw new Exception("Error en usuario y/o clave.");

		if (!usuario.getClave().equals(clave))
			throw new Exception("Error en usuario y/o clave.");

		LoginDTO loginDTO = new LoginDTO();

		loginDTO.setUsuario(usuario.getNombres());
//		loginDTO.setTipoUsuario(usuario.getTipoUsuario());
//		loginDTO.setCodigoUsuario(usuario.getIdUsuario()());

		// dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags
		// web:
//		if(usuario.getTipoUsuario().equals("VD"))
//			loginDTO.setRutaAcceso("/vendedor/index.xhtml");
//		else if(usuario.getTipoUsuario().equals("SP"))
//			loginDTO.setRutaAcceso("/supervisor/index.xhtml");
//		managerAuditoria.crearEvento(usuario.getCodigoUsuario(), ManagerSeguridad.class, "accederSistema", "Acceso al sistema para usuarios.");
		return loginDTO;
	}

	/**
	 * Metodo para que los clientes puedan acceder al sistema.
	 * 
	 * @param cedula Cedula del cliente.
	 * @param clave  Clave del cliente.
	 * @return Objeto LoginDTO con informacion del cliente para el control de
	 *         sesion.
	 * @throws Exception
	 */
	public LoginDTO accederSistemaClientes(int cedula, String clave) throws Exception {
		Usuario usuario = managerUsuario.findUsuarioById(cedula);
		if (usuario == null)
			return null;// el cliente no existe, debe registrarse.
		if (!usuario.getClave().equals(clave))
			throw new Exception("Error en cedula/clave");

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setCodigoUsuario(usuario.getCedula());
		loginDTO.setUsuario(usuario.getNombres() + " " + usuario.getApellidos());
		
		loginDTO.setTipoUsuario("Normal");
		loginDTO.setRutaAcceso("/usuario/inicio.xhtml");
		managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(), ManagerSeguridad.class, "accederSistemaClientes",
				"Acceso al sistema para clientes.");
		return loginDTO;
	}

	public Usuario findUsuarioById(String codigoUsuario) throws Exception {
		Usuario usuario = (Usuario) managerDAO.findById(Usuario.class, codigoUsuario);
		return usuario;
	}

}
