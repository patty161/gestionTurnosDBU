package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.dto.LoginDTO;
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
	@EJB
	private ManagerAuditoria managerAuditoria;

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

	

}