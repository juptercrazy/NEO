package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import entity.Empresa;
import entity.ModeloAula;
import entity.Role;
import entity.TipoEmpresa;
import entity.TipoServico;
import entity.Usuario;
import negocio.EmpresaUsuarioNegocio;
import negocio.ModeloAulaNegocio;
import negocio.RoleNegocio;
import negocio.TipoEmpresaNegocio;
import negocio.TipoServicoNegocio;
import negocio.UsuarioNegocio;
import util.ContextoUtil;

@ManagedBean(name="listBean")
public class ListBean {

	private List<TipoServico> tiposServico = new ArrayList<TipoServico>();

	private List<TipoEmpresa> tiposEmpresa = new ArrayList<TipoEmpresa>();
	
	private List<Role> papeisUsuario = new ArrayList<Role>();
	
	private List<ModeloAula> modelosAula = new ArrayList<ModeloAula>();
	
	private List<Usuario> professores = new ArrayList<Usuario>();
	
	public List<TipoServico> getTiposServicos() {
		if (tiposServico.isEmpty()) {
			carregarTipoServico();
		}
		return tiposServico;
	}
	
	public void setTiposServicos(List<TipoServico> tiposServicos) {
		this.tiposServico = tiposServicos;
	}
	
	private void carregarTipoServico() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		try {
			TipoServicoNegocio tipoServicoRN = new TipoServicoNegocio();	
			this.setTiposServicos(tipoServicoRN.obterTipoServicoDaEmpresa(contextoBean.obterEmpresaPrincipalDoUsuario()));
		
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	
	// TIPO DE EMPRESA
	public List<TipoEmpresa> getTiposEmpresa() {
		carregarTipoEmpresa();
		return tiposEmpresa;
	}


	public void setTiposEmpresa(List<TipoEmpresa> tiposEmpresa) {
		this.tiposEmpresa = tiposEmpresa;
	}
	
	private void carregarTipoEmpresa() {
		TipoEmpresaNegocio tipoEmpresaRN = new TipoEmpresaNegocio();
		try {
			
			this.tiposEmpresa  = tipoEmpresaRN.popularLista();
		
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	
	// TIPO DE USUÁRIO - ROLE
	public List<Role> getTiposUsuario() {
		carregarTipoUsuario();
		return papeisUsuario;
	}


	public void setTiposUsuario(List<Role> tiposUsuario) {
		this.papeisUsuario = tiposUsuario;
	}
	
	private void carregarTipoUsuario() {
		RoleNegocio roleRN = new RoleNegocio();
		try {
			
			this.papeisUsuario  = roleRN.popularLista();
		
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	
	// MODELOS DE AULAS
	
	public List<ModeloAula> getModelosAula() {
		if (modelosAula.isEmpty())
			carregarTipoAula();
		return modelosAula;
	}

	public void setModelosAula(List<ModeloAula> tiposAulas) {
		this.modelosAula = tiposAulas;
	}
	
	private void carregarTipoAula() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		
		try {
			ModeloAulaNegocio modeloRN = new ModeloAulaNegocio ();	
			this.setModelosAula(modeloRN.obterModelosDeAulaDaEmpresa(contextoBean.obterEmpresaPrincipalDoUsuario()));
		
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void limparModeloAula() {
		this.modelosAula = new ArrayList<ModeloAula>();
	}
	
	// PROFESSORES
	public List<Usuario> getProfessores() {
		if (professores.isEmpty())
			carregarProfessores();
		return professores;
	}

	public void setProfessores(List<Usuario> professores) {
		this.professores = professores;
	}
	
	public void carregarProfessores() {
		try {
			UsuarioNegocio professorRN = new UsuarioNegocio();	
			this.setProfessores(professorRN.obterProfessoresDaEmpresa());
		
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
