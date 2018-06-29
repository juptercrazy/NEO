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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

@Entity
public class Aula implements Serializable {
	
	public Aula() {
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AULA_ID")
	private Long id;
	
	@ManyToMany
	@JoinTable(name="ALUNOS_AULA"
	, joinColumns={@JoinColumn(name="usuario_id")},
									inverseJoinColumns=	{@JoinColumn(name="aula_id")}
	)
	private List<Usuario> alunos = new ArrayList<Usuario>();
	
	
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsString")
	private LocalTime horaInicioAula;
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsString")
	private LocalTime horaFimAula;
	
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime inicioVigencia;
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime fimVigencia;
	
	private Integer numeroAlunos;
	private Integer diaDaSemana;
	
	@ManyToOne
	@JoinColumn(name = "TIPOSERVICO_ID")
	private TipoServico tipoServico;
	
	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "PROFESSOR_ID")
	private Usuario professor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Usuario> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}
	public Integer getNumeroAlunos() {
		return numeroAlunos;
	}
	public void setNumeroAlunos(Integer numeroAlunos) {
		this.numeroAlunos = numeroAlunos;
	}
	public LocalTime getHoraInicioAula() {
		return horaInicioAula;
	}
	public void setHoraInicioAula(LocalTime horaInicioAula) {
		this.horaInicioAula = horaInicioAula;
	}
	public LocalTime getHoraFimAula() {
		return horaFimAula;
	}
	public void setHoraFimAula(LocalTime horaFimAula) {
		this.horaFimAula = horaFimAula;
	}
	public DateTime getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(DateTime inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public DateTime getFimVigencia() {
		return fimVigencia;
	}
	public void setFimVigencia(DateTime fimVigencia) {
		this.fimVigencia = fimVigencia;
	}
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Usuario getProfessor() {
		return professor;
	}
	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}
	public Integer getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(Integer diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
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
		Aula other = (Aula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}