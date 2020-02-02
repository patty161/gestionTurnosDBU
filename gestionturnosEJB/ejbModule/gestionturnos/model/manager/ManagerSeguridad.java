package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import gestionturnos.model.dto.LoginDTO;
import gestionturnos.model.entities.Asignacion;
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
	private int idusuariobuscado;

	public int getIdusuariobuscado() {
		return idusuariobuscado;
	}

	public void setIdusuariobuscado(int idusuariobuscado) {
		this.idusuariobuscado = idusuariobuscado;
	}

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

	public String TipoUsuario(int idUsu) throws Exception {
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiii " + idUsu);
		List<Asignacion> asig = managerUsuario.findAllAsignacion();
		String rol = "";
		for (Asignacion asignacion : asig) {
			System.out.println("entra..................." + asignacion.getUsuario().getIdUsuario());
			if (asignacion.getUsuario().getIdUsuario().equals(idUsu)) {
				System.out.println("le econtro el rol " + asignacion.getSegRol().getNombreRol());
				rol = asignacion.getSegRol().getNombreRol();
			}
		}
		return rol;
//		em.merge(usu);
	}

	public LoginDTO ValidaUsuario(String cedula, String clave) throws Exception {
		LoginDTO loginDTO = new LoginDTO();

		List<Usuario> usu = managerUsuario.findAllUsuario();
		for (Usuario usuario : usu) {
			if (usuario.getCedula().equals(cedula) && clave.endsWith("n")) {
				System.out.println("Usaurio encontrado " + usuario.getCedula());

			} else {
				if (usuario.getCedula().equals(cedula) && clave.endsWith(usuario.getClave())) {
					System.out.println("Usaurio con id " + usuario.getIdUsuario());
					idusuariobuscado = usuario.getIdUsuario();
					loginDTO.setCodigoUsuario(usuario.getCedula());
					loginDTO.setUsuario(usuario.getNombres() + " " + usuario.getApellidos());
					loginDTO.setDireccion(usuario.getDireccion());
					loginDTO.setTipoUsuario("" + TipoUsuario(idusuariobuscado));
					if (TipoUsuario(idusuariobuscado).equals("Administrativo"))
						loginDTO.setRutaAcceso("/administrativo/indexPrincipal.xhtml");
					else if (TipoUsuario(idusuariobuscado).equals("Especilista"))
						loginDTO.setRutaAcceso("/Especilista/index.xhtml");
					managerAuditoria.crearEvento(usuario.getIdUsuario().toString(), ManagerUsuario.class, "Acesde",
							"INgreso al sistemas ");
					;

				}

			}
//			}else {
//			
//				System.out.println("no hay ese usuario porque esta el "+usuario.getCedula());
//				
//			
//
//			}

		}
		System.out.println("llllllllllllllllllllllllllllllllllllllllllll");

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
