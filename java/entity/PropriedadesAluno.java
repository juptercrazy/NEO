package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class PropriedadesAluno  implements Serializable {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROPRIEDADE_ID")
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private TipoServico tipoAula;
	
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime dataNascimento;
	
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime dataInscricao;
	
	private Integer vezesPorSemana;
	
	@ManyToMany
	private List<Aula> aula = new ArrayList<Aula>();
	
	public PropriedadesAluno() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoServico getTipoAula() {
		return tipoAula;
	}

	public void setTipoAula(TipoServico tipoAula) {
		this.tipoAula = tipoAula;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(DateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public DateTime getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(DateTime dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Integer getVezesPorSemana() {
		return vezesPorSemana;
	}

	public void setVezesPorSemana(Integer vezesPorSemana) {
		this.vezesPorSemana = vezesPorSemana;
	}

	public List<Aula> getAula() {
		return aula;
	}

	public void setAula(List<Aula> aula) {
		this.aula = aula;
	}

}
