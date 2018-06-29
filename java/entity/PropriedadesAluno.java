package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PropriedadesAluno  implements Serializable {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROPRIEDADE_ID")
	private Long id;
	
	private Integer vezesPorSemana;
	
	@OneToOne(cascade=CascadeType.ALL)
	private TipoServico tipoAula;
	
//	private List<Date> diasDaSemana = new ArrayList<Date>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVezesPorSemana() {
		return vezesPorSemana;
	}

	public void setVezesPorSemana(Integer vezesPorSemana) {
		this.vezesPorSemana = vezesPorSemana;
	}

	public TipoServico getTipoAula() {
		return tipoAula;
	}

	public void setTipoAula(TipoServico tipoAula) {
		this.tipoAula = tipoAula;
	}

//	public List<Date> getDiasDaSemana() {
//		return diasDaSemana;
//	}
//
//	public void setDiasDaSemana(List<Date> diasDaSemana) {
//		this.diasDaSemana = diasDaSemana;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
}
