package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_IDUSUARIO_GENERATOR", sequenceName="USUARIO_ID_USUARIO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="id_usuario", unique=true, nullable=false)
	private Integer idUsuario;

	@Column(nullable=false, length=50)
	private String apellidos;

	@Column(nullable=false, length=10)
	private String cedula;

	@Column(length=50)
	private String clave;

	@Column(length=50)
	private String direccion;

	@Column(length=30)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(nullable=false, length=50)
	private String nombres;

	//bi-directional many-to-one association to Bitacora
	@OneToMany(mappedBy="usuario")
	private List<Bitacora> audBitacoras;

	//bi-directional many-to-one association to Personal
	@OneToMany(mappedBy="usuario")
	private List<Personal> espPersonals;

	//bi-directional many-to-one association to Asignacion
	@OneToMany(mappedBy="usuario")
	private List<Asignacion> segAsignacions;

	public Usuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public List<Bitacora> getAudBitacoras() {
		return this.audBitacoras;
	}

	public void setAudBitacoras(List<Bitacora> audBitacoras) {
		this.audBitacoras = audBitacoras;
	}

	public Bitacora addAudBitacora(Bitacora audBitacora) {
		getAudBitacoras().add(audBitacora);
		audBitacora.setUsuario(this);

		return audBitacora;
	}

	public Bitacora removeAudBitacora(Bitacora audBitacora) {
		getAudBitacoras().remove(audBitacora);
		audBitacora.setUsuario(null);

		return audBitacora;
	}

	public List<Personal> getEspPersonals() {
		return this.espPersonals;
	}

	public void setEspPersonals(List<Personal> espPersonals) {
		this.espPersonals = espPersonals;
	}

	public Personal addEspPersonal(Personal espPersonal) {
		getEspPersonals().add(espPersonal);
		espPersonal.setUsuario(this);

		return espPersonal;
	}

	public Personal removeEspPersonal(Personal espPersonal) {
		getEspPersonals().remove(espPersonal);
		espPersonal.setUsuario(null);

		return espPersonal;
	}

	public List<Asignacion> getSegAsignacions() {
		return this.segAsignacions;
	}

	public void setSegAsignacions(List<Asignacion> segAsignacions) {
		this.segAsignacions = segAsignacions;
	}

	public Asignacion addSegAsignacion(Asignacion segAsignacion) {
		getSegAsignacions().add(segAsignacion);
		segAsignacion.setUsuario(this);

		return segAsignacion;
	}

	public Asignacion removeSegAsignacion(Asignacion segAsignacion) {
		getSegAsignacions().remove(segAsignacion);
		segAsignacion.setUsuario(null);

		return segAsignacion;
	}

}