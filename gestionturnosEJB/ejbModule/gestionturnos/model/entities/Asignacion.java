package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the seg_asignacion database table.
 * 
 */
@Entity
@Table(name="seg_asignacion")
@NamedQuery(name="Asignacion.findAll", query="SELECT a FROM Asignacion a")
public class Asignacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_ASIGNACION_IDASIGNACION_GENERATOR", sequenceName="SEG_ASIGNACION_ID_ASIGNACION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_ASIGNACION_IDASIGNACION_GENERATOR")
	@Column(name="id_asignacion", unique=true, nullable=false)
	private Integer idAsignacion;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Rol segRol;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Asignacion() {
	}

	public Integer getIdAsignacion() {
		return this.idAsignacion;
	}

	public void setIdAsignacion(Integer idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public Rol getSegRol() {
		return this.segRol;
	}

	public void setSegRol(Rol segRol) {
		this.segRol = segRol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}