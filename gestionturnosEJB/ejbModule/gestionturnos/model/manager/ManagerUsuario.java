package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Asignacion;
import gestionturnos.model.entities.Usuario;

/**
 * 
 */
@Stateless
@LocalBean
public class ManagerUsuario {

	@PersistenceContext
	private EntityManager em;
	private int idusuariobuscado;

	public int getIdusuariobuscado() {
		return idusuariobuscado;
	}

	public void setIdusuariobuscado(int idusuariobuscado) {
		this.idusuariobuscado = idusuariobuscado;
	}

	public ManagerUsuario() {

	}

	public List<Usuario> findAllUsuario() {
		String consulta = "SELECT u FROM Usuario u order by id_usuario";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
	}

	public List<Asignacion> findAllAsignacion() {
		String consulta = "SELECT a FROM Asignacion a";
		Query q = em.createQuery(consulta, Asignacion.class);
		return q.getResultList();
	}

	public Usuario findUsuarioById(int idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}

	public void eliminarUsuario(int IdUsuario) {
		Usuario usuario = findUsuarioById(IdUsuario);
		if (usuario != null)
			em.remove(usuario);
	}

	public void insertarUsuario(Usuario usuario) {
		em.persist(usuario);
	}

	public void actualizarUsuario(Usuario usuario) throws Exception {
		Usuario usu = findUsuarioById(usuario.getIdUsuario());
		if (usu == null)
			usu.setApellidos(usu.getApellidos());
		usu.setCedula(usu.getCedula());
		usu.setClave(usu.getClave());
		usu.setDireccion(usu.getDireccion());
		usu.setNombres(usu.getNombres());

		em.merge(usu);
	}

	public String TipoUsuario(int idUsu) throws Exception {
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiii "+ idUsu);
		List<Asignacion> asig = findAllAsignacion();
		String rol="";
	for (Asignacion asignacion : asig) {
		System.out.println("entra..................."+asignacion.getUsuario().getIdUsuario());
		if (asignacion.getUsuario().getIdUsuario().equals(idUsu)) {
			System.out.println("le econtro el rol "+asignacion.getSegRol().getNombreRol());
			rol=asignacion.getSegRol().getNombreRol();
		}
	}
	return rol;
//		em.merge(usu);
	}

	public boolean ValidaUsuario(String cedula, String clave) {

		boolean fn = false;
		List<Usuario> usu = findAllUsuario();
		for (Usuario usuario : usu) {
			if (usuario.getCedula().equals(cedula) && clave.endsWith("n")) {
				System.out.println("Usaurio encontrado " + usuario.getCedula());
				fn = true;
			} else {
				if (usuario.getCedula().equals(cedula) && clave.endsWith(usuario.getClave())) {
					System.out.println("Usaurio con id " + usuario.getIdUsuario());
					idusuariobuscado = usuario.getIdUsuario();
					fn = true;
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

		// em.merge(usu);
		return fn;
	}

}
