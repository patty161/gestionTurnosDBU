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
	@SequenceGenerator(name="TUR_TURNOS_IDTURNOS_GENERATOR", sequenceName="TUR_TURNOS_ID_TURNOS_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TUR_TURNOS_IDTURNOS_GENERATOR")
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

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado turEstado;

	//bi-directional many-to-one association to Asignacion
	@ManyToOne
	@JoinColumn(name="id_asignacion")
	private Asignacion segAsignacion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

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

	public Estado getTurEstado() {
		return this.turEstado;
	}

	public void setTurEstado(Estado turEstado) {
		this.turEstado = turEstado;
	}

	public Asignacion getSegAsignacion() {
		return this.segAsignacion;
	}

	public void setSegAsignacion(Asignacion segAsignacion) {
		this.segAsignacion = segAsignacion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}