package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import entity.Empresa;
import entity.EmpresaUsuario;
import entity.Usuario;
import negocio.EmpresaUsuarioNegocio;
import negocio.UsuarioNegocio;

@ManagedBean
@SessionScoped
public class ContextoBean {

	private Usuario usuarioLogado = null;

	private Empresa empresaPrincipal = null;
	
	private final String url = "http://localhost:8080/NEO";

	public Usuario getUsuarioLogado() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();

		String cpf = external.getRemoteUser();
		if (this.usuarioLogado == null || !cpf.equals(this.usuarioLogado.getCpf())) {
			if (cpf != null) {
				UsuarioNegocio usuarioRN = new UsuarioNegocio();
				this.usuarioLogado = usuarioRN.getUsuarioPeloCPF(cpf);
			}
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
	}

	public Empresa obterEmpresaPrincipalDoUsuario() throws Exception {
		if (empresaPrincipal == null) {
			empresaPrincipal = buscarEmpresaPrincipalDoUsuario();
		}
		return empresaPrincipal;
	}

	private Empresa buscarEmpresaPrincipalDoUsuario() throws Exception {
		EmpresaUsuarioNegocio empresaUsuarioNegocio = new EmpresaUsuarioNegocio();
		EmpresaUsuario empUser =  empresaUsuarioNegocio.buscarEmpresaUsuarioPrincipal(usuarioLogado);

		return empUser.getEmpresa();
	}
	
	public EmpresaUsuario obterEmpresaUsuarioDoUsuario() throws Exception {
		EmpresaUsuarioNegocio empresaUsuarioNegocio = new EmpresaUsuarioNegocio();
		EmpresaUsuario empUser =  empresaUsuarioNegocio.buscarEmpresaUsuarioPrincipal(usuarioLogado);

		return empUser;
	}

	public String getUrl() {
		return url;
	}

}
