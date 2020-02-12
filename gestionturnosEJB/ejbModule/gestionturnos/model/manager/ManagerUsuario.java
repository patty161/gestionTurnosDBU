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
import gestionturnos.model.entities.Personal;
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
	public Usuario findUsuarioBycedula(String cedula) {
		return em.find(Usuario.class, cedula);
	}

	public void eliminarUsuario(int IdUsuario) {
		Usuario usuario = findUsuarioById(IdUsuario);
		if (usuario != null)
			em.remove(usuario);
	}

	public void insertarUsuario(Usuario usuario) throws Exception {
		usuario.setClave(Hash.md5(usuario.getClave()));
		em.persist(usuario);
		
		System.out.println("Usuario insertado.........");
	}
	
	public String crearUsuario(Usuario usuario) {
		Usuario p=new Usuario();
    	p.setNombres(usuario.getNombres());
    	p.setCedula(usuario.getCedula());
    	System.out.println("laaaaaaaaaaaaaaaaaaaaa clave a ingresar es");
    	p.setClave(Hash.md5(usuario.getClave()));
    	p.setApellidos(usuario.getApellidos());
    	p.setDireccion(usuario.getDireccion());
    	p.setEmail(usuario.getEmail());
    	p.setTelefono(usuario.getTelefono());
    	p.setFechanacimiento(usuario.getFechanacimiento());
    
    	em.persist(p);
    	return "usuario creado ok.";
    }
	
	 public String eliminarUsuarioR(int idusu) {
	    	Usuario p=em.find(Usuario.class, idusu);
	    	em.remove(p);
	    	return "se ha eliminado el usuario ok.";
	    }
	

	 public void actualizarUsuario(Usuario usuario) throws Exception {
			Usuario user = findUsuarioById(usuario.getIdUsuario());
			if (user == null)
				throw new Exception("No existe el Usuario con el Id especificada");
			user.setCedula(usuario.getCedula());
			user.setNombres(usuario.getNombres());
			user.setApellidos(usuario.getApellidos());
			user.setDireccion(usuario.getDireccion());
			user.setEmail(usuario.getEmail());
			user.setTelefono(usuario.getTelefono());
			user.setFechanacimiento(usuario.getFechanacimiento());
			user.setClave(Hash.md5(usuario.getClave()));
			em.merge(user);
		}


}
