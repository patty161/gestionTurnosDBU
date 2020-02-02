package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the esp_personal database table.
 * 
 */
@Entity
@Table(name="esp_personal")
@NamedQuery(name="Personal.findAll", query="SELECT p FROM Personal p")
public class Personal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESP_PERSONAL_IDPERSONAL_GENERATOR", sequenceName="ESP_PERSONAL_ID_PERSONAL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESP_PERSONAL_IDPERSONAL_GENERATOR")
	@Column(name="id_personal", unique=true, nullable=false)
	private Integer idPersonal;

	@Column(name="horas_laborables")
	private Integer horasLaborables;

	//bi-directional many-to-one association to Area
	@ManyToOne
	@JoinColumn(name="id_area")
	private Area espArea;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Personal() {
	}

	public Integer getIdPersonal() {
		return this.idPersonal;
	}

	public void setIdPersonal(Integer idPersonal) {
		this.idPersonal = idPersonal;
	}

	public Integer getHorasLaborables() {
		return this.horasLaborables;
	}

	public void setHorasLaborables(Integer horasLaborables) {
		this.horasLaborables = horasLaborables;
	}

	public Area getEspArea() {
		return this.espArea;
	}

	public void setEspArea(Area espArea) {
		this.espArea = espArea;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}