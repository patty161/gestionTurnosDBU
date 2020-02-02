package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_rol database table.
 * 
 */
@Entity
@Table(name="seg_rol")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_ROL_IDROL_GENERATOR", sequenceName="SEG_ROL_ID_ROL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_ROL_IDROL_GENERATOR")
	@Column(name="id_rol", unique=true, nullable=false)
	private Integer idRol;

	@Column(name="nombre_rol", length=50)
	private String nombreRol;

	//bi-directional many-to-one association to Asignacion
	@OneToMany(mappedBy="segRol")
	private List<Asignacion> segAsignacions;

	public Rol() {
	}

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<Asignacion> getSegAsignacions() {
		return this.segAsignacions;
	}

	public void setSegAsignacions(List<Asignacion> segAsignacions) {
		this.segAsignacions = segAsignacions;
	}

	public Asignacion addSegAsignacion(Asignacion segAsignacion) {
		getSegAsignacions().add(segAsignacion);
		segAsignacion.setSegRol(this);

		return segAsignacion;
	}

	public Asignacion removeSegAsignacion(Asignacion segAsignacion) {
		getSegAsignacions().remove(segAsignacion);
		segAsignacion.setSegRol(null);

		return segAsignacion;
	}

}