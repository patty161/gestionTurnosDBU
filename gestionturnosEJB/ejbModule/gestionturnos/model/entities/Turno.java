package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tur_turnos database table.
 * 
 */
@Entity
@Table(name="tur_turnos")
@NamedQuery(name="Turno.findAll", query="SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_turnos", unique=true, nullable=false)
	private Integer idTurnos;

	@Column(nullable=false)
	private Timestamp fecha;

	@Column(name="nro_turno")
	private Integer nroTurno;

	//bi-directional many-to-one association to Area
	@ManyToOne
	@JoinColumn(name="id_area")
	private Area espArea;

	//bi-directional many-to-one association to Personal
	@ManyToOne
	@JoinColumn(name="id_personal")
	private Personal espPersonal;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Rol segRol;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado turEstado;

	public Turno() {
	}

	public Integer getIdTurnos() {
		return this.idTurnos;
	}

	public void setIdTurnos(Integer idTurnos) {
		this.idTurnos = idTurnos;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Integer getNroTurno() {
		return this.nroTurno;
	}

	public void setNroTurno(Integer nroTurno) {
		this.nroTurno = nroTurno;
	}

	public Area getEspArea() {
		return this.espArea;
	}

	public void setEspArea(Area espArea) {
		this.espArea = espArea;
	}

	public Personal getEspPersonal() {
		return this.espPersonal;
	}

	public void setEspPersonal(Personal espPersonal) {
		this.espPersonal = espPersonal;
	}

	public Rol getSegRol() {
		return this.segRol;
	}

	public void setSegRol(Rol segRol) {
		this.segRol = segRol;
	}

	public Estado getTurEstado() {
		return this.turEstado;
	}

	public void setTurEstado(Estado turEstado) {
		this.turEstado = turEstado;
	}

}