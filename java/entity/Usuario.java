package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Usuario implements Serializable {


	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USUARIO_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String nome;

	@Column(name = "PASSWORD")
	private String senha;
	
	@Column(name = "enabled")
	private Boolean valido;

	@Column (unique=true)
	private String cpf;

	private String email;

	@ManyToMany
	@JoinTable(name="tipo_usuario"
	, joinColumns={@JoinColumn(name="usuario_id")},
									inverseJoinColumns=	{@JoinColumn(name="role_id")}
	)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> roles = new ArrayList<Role>();
	
	@OneToMany(mappedBy="usuario")     
	private List<EmpresaUsuario> empresaUsuarios = new ArrayList<EmpresaUsuario>();

	private byte [] foto;

	@ManyToMany
	@JoinTable(name="TIPOSERVICO_USUARIO"
	, joinColumns={@JoinColumn(name="USUARIO_ID")},
									inverseJoinColumns=	{@JoinColumn(name="TIPO_SERVICO_ID")}
	)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<TipoServico> tipoServicos = new ArrayList<TipoServico>();
	
	@OneToOne(cascade=CascadeType.ALL)
	private PropriedadesAluno propriedadesAluno;
	
	@OneToMany(mappedBy="professor", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Aula> aula = new ArrayList<Aula>();

	public Usuario(){
		 propriedadesAluno = new PropriedadesAluno();
	}

	public Usuario(String novoNome, String novaSenha, String novoEmail, String novoCPF){
		setNome(novoNome);
		setSenha(novaSenha);
		setEmail(novoEmail);
		setCpf(novoCPF);
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the foto
	 */
	public byte[] getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRoles(Role role) {
		getRoles().add(role);
	}

	public List<EmpresaUsuario> getEmpresaUsuarios() {
		return empresaUsuarios;
	}

	public void setEmpresaUsuarios(List<EmpresaUsuario> empresaUsuarios) {
		this.empresaUsuarios = empresaUsuarios;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<TipoServico> getTipoServicos() {
		return tipoServicos;
	}

	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}
	
	public void addTipoServico(TipoServico tipoServico) {
		this.getTipoServicos().add(tipoServico);
	}


	public PropriedadesAluno getPropriedadesAluno() {
		return propriedadesAluno;
	}

	public void setPropriedadesAluno(PropriedadesAluno propriedadesAluno) {
		this.propriedadesAluno = propriedadesAluno;
	}

	public List<Aula> getAula() {
		return aula;
	}

	public void setAula(List<Aula> aula) {
		this.aula = aula;
	}
}
