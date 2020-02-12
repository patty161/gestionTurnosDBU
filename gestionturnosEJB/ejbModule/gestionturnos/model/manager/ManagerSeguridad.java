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
	@EJB
	private ManagerTurnos managerTurnos;

	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	Hash hash = new Hash();
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
			System.out.println("entra..................." + asignacion.getIdAsignacion());

			if (asignacion.getUsuario().getIdUsuario().equals(idUsu)) {
				System.out.println("le econtro el rol " + asignacion.getSegRol().getNombreRol());
				rol = asignacion.getSegRol().getNombreRol();
			}
		}
		return rol;
//		em.merge(usu);
	}

	public String BusquedaActualizaContra(String correo, String clavenueva, String codigo) throws Exception {
//		correo="ajvallejosm@utn.edu.ec";
		System.out.println("correo  " + correo);
		System.out.println("codigoa  ingresar" + this.codigo);
		List<Usuario> usu = managerUsuario.findAllUsuario();
		String rol = "";

		for (Usuario usuario : usu) {

			System.out.println("entra..................." + usuario.getEmail().toString() + " con el correo " + correo
					+ " cidog " + this.codigo + "=" + codigo);

//			if (usuario.getEmail().equals(correo) && codigo==this.codigo) {
			if (usuario.getEmail().equals(correo)) {
				System.out.println("usuarios iguales");
				System.out.println("ingresa al if con la clave" + clavenueva + "y se pasa a " + hash.md5(clavenueva));
				usuario.setClave(clavenueva);

				managerUsuario.actualizarUsuario(usuario);

				System.out.println("funciono");
			} else {
				System.out.println("usuarios nooooooooooooo iguales");

			}
		}
		return rol;
//		em.merge(usu);
	}

	public String getIdASI(int idUsu) throws Exception {
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiii " + idUsu);
		List<Asignacion> asig = managerUsuario.findAllAsignacion();
		String ID = "";
		for (Asignacion asignacion : asig) {
			System.out.println("entra..................." + asignacion.getIdAsignacion());

			if (asignacion.getUsuario().getIdUsuario().equals(idUsu)) {

				System.out.println("le econtro el rol " + asignacion.getSegRol().getNombreRol());
				ID = asignacion.getIdAsignacion().toString();
			}
		}
		return ID;
//		em.merge(usu);
	}

	public LoginDTO ValidaUsuario(String cedula, String clave) throws Exception {
		System.out.println("" + clave);
		LoginDTO loginDTO = new LoginDTO();

		List<Usuario> usu = managerUsuario.findAllUsuario();
		for (Usuario usuario : usu) {
			System.out.println("" + usuario.getCedula() + "=" + cedula + "la calve es n:" + clave);
//			if (usuario.getCedula().equals(cedula) ) {
			if (usuario.getCedula().equals(cedula) && clave.equals("n")) {
				loginDTO.setIdUsuario(usuario.getIdUsuario());
				System.out.println("Usaurio encontrado " + usuario.getCedula());
				loginDTO.setRutaAcceso("/usuario/inicio.xhtml");

			}
		}
		System.out.println("llllllllllllllllllllllllllllllllllllllllllll");

		return loginDTO;

	}

	public LoginDTO ValidaUsuarioA(String cedula, String clave) throws Exception {
		System.out.println("" + clave);
		LoginDTO loginDTO = new LoginDTO();
		clave = hash.md5(clave);
		List<Usuario> usu = managerUsuario.findAllUsuario();
		for (Usuario usuario : usu) {
			System.out.println("" + usuario.getCedula() + "=" + cedula +usuario.getClave()+"la calve es n:" + clave);
//			if (usuario.getCedula().equals(cedula) ) {

			
			if (usuario.getCedula().equals(cedula) && clave.endsWith(usuario.getClave())) {
//					if (usuario.getCedula().equals(cedula) && clave.endsWith(usuario.getClave())) {
				System.out.println("Usaurio con id " + usuario.getIdUsuario());
				idusuariobuscado = usuario.getIdUsuario();
				loginDTO.setIdUsuario(usuario.getIdUsuario());
				loginDTO.setCodigoUsuario(usuario.getCedula());
				loginDTO.setUsuario(usuario.getNombres() + " " + usuario.getApellidos());
				loginDTO.setDireccion(usuario.getDireccion());
				loginDTO.setTipoUsuario("" + TipoUsuario(idusuariobuscado));
				if (TipoUsuario(idusuariobuscado).equals("Administrativo"))
					loginDTO.setRutaAcceso("/administrativo/indexPrincipal.xhtml");
				else if (TipoUsuario(idusuariobuscado).equals("Especialista")) {
					System.out.println(
							"laaaaaaaaaaaaaaaaa idasigna del usuario que ingreso es..." + getIdASI(idusuariobuscado));
					managerTurnos.setIdasi(getIdASI(idusuariobuscado));
					loginDTO.setRutaAcceso("/personal/inicio.xhtml");
				}
//					managerAuditoria.crearEvento(usuario.getIdUsuario().toString(), ManagerUsuario.class, "Acesde",
//							"INgreso al sistemas ");
//					;

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
		loginDTO.setIdUsuario(usuario.getIdUsuario());
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
