package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tur_estado database table.
 * 
 */
@Entity
@Table(name="tur_estado")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TUR_ESTADO_IDESTADO_GENERATOR", sequenceName="TUR_ESTADO_ID_ESTADO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TUR_ESTADO_IDESTADO_GENERATOR")
	@Column(name="id_estado", unique=true, nullable=false)
	private Integer idEstado;

	@Column(name="descripcion_estado", length=100)
	private String descripcionEstado;

	//bi-directional many-to-one association to Turno
	@OneToMany(mappedBy="turEstado")
	private List<Turno> turTurnos;

	public Estado() {
	}

	public Integer getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcionEstado() {
		return this.descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public List<Turno> getTurTurnos() {
		return this.turTurnos;
	}

	public void setTurTurnos(List<Turno> turTurnos) {
		this.turTurnos = turTurnos;
	}

	public Turno addTurTurno(Turno turTurno) {
		getTurTurnos().add(turTurno);
		turTurno.setTurEstado(this);

		return turTurno;
	}

	public Turno removeTurTurno(Turno turTurno) {
		getTurTurnos().remove(turTurno);
		turTurno.setTurEstado(null);

		return turTurno;
	}

}