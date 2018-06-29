package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

@Entity
public class ModeloAula implements Serializable {

	
	public ModeloAula() {
		
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AULA_ID")
	private Long id;
	private String nome;
	private Integer maxAlunosAula;
	private Integer duracao;
	
	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID")
	private Empresa empresas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getMaxAlunosAula() {
		return maxAlunosAula;
	}
	public void setMaxAlunosAula(Integer maxAlunosAula) {
		this.maxAlunosAula = maxAlunosAula;
	}
	public Empresa getEmpresas() {
		return empresas;
	}
	public void setEmpresas(Empresa empresas) {
		this.empresas = empresas;
	}
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloAula other = (ModeloAula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
