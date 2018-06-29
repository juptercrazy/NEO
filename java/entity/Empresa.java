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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empresa implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPRESA_ID")
	private Long id;
	private String nome;
	@Column (unique=true)
	private Long cnpj;
	@OneToOne(cascade=CascadeType.ALL)
	private TipoEmpresa tipoEmpresa;
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;
	@OneToMany(mappedBy="empresa")
	private List<TipoServico> tipoServicos = new ArrayList<TipoServico>();
	@OneToMany(mappedBy="empresa", cascade=CascadeType.ALL)
	private List<Aula> aulas = new ArrayList<Aula>();
	@OneToMany(mappedBy="empresa") 
	private List<EmpresaUsuario> empresaUsuarios = new ArrayList<EmpresaUsuario>();

	public Empresa() {
		super();
	}
	
	
	public Empresa(String newnome, Long newcnpj) {
		super();
		this.setNome(newnome);
		this.setCnpj(newcnpj); 
	}


	/**
	 * @return the id
	 */
	
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cNPJ
	 */
	public Long getCnpj() {
		return cnpj;
	}

	/**
	 * @param cNPJ the cNPJ to set
	 */
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}


	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}


	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public List<EmpresaUsuario> getEmpresaUsuarios() {
		return empresaUsuarios;
	}


	public void setEmpresaUsuarios(List<EmpresaUsuario> empresaUsuarios) {
		this.empresaUsuarios = empresaUsuarios;
	}


	public List<TipoServico> getTipoServicos() {
		return tipoServicos;
	}


	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}
	
	public void addTipoServico(TipoServico servico) {
		getTipoServicos().add(servico);
	}
	

	public List<Aula> getAulas() {
		return aulas;
	}


	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
