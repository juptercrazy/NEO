package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import entity.Empresa;
import entity.TipoServico;
import entity.Usuario;
import negocio.UsuarioNegocio;
import util.ContextoUtil;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1094801825228386363L;

	private Usuario usuario = new Usuario();

	private Empresa empresa = new Empresa();

	private TipoServico tipoServico = new TipoServico();

	private String url = ContextoUtil.getContextoBean().getUrl();

	public String novo() {
		setNovoUsuario();
		StringBuilder endereco = new StringBuilder().append(url)
				.append("/publico/cadastro/proprietario/cadastrar_novo_usuario_template.xhtml");

		return endereco.toString();
	}

	public String getNovoFuncionario() {
		setNovoUsuario();

		StringBuilder endereco = new StringBuilder().append(url)
				.append("/proprietario/cadastro/funcionario/template_cadastrar_funcionario.xhtml");

		return endereco.toString();

	}

	public String getNovoAluno() {
		setNovoUsuario();
		StringBuilder endereco = new StringBuilder().append(url)
				.append("/proprietario/cadastro/aluno/template_cadastrar_aluno.xhtml");

		return endereco.toString();
	}

	private void setNovoUsuario() {
		this.usuario = new Usuario();
		this.usuario.setValido(true);
	}

	public String criarUsuarioProprietario() {

		UsuarioNegocio usuarioRN = new UsuarioNegocio();
		try {

			usuarioRN.criarProprietario(this.usuario, empresa);

			return "/publico/login";

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public String criarUsuarioFuncionario() {

		UsuarioNegocio usuarioRN = new UsuarioNegocio();
		try {

			this.getUsuario().addTipoServico(this.getTipoServico());
			usuarioRN.criarUsuarioFuncionario(this.usuario);

			return "/proprietario/template_home.xhtml";

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public String criarUsuarioAluno() {
		UsuarioNegocio usuarioRN = new UsuarioNegocio();
		try {

			usuarioRN.criarUsuarioAluno(this.usuario);

			return "/proprietario/template_home.xhtml";

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public void validateEmail(FacesContext context, UIComponent toValidate, Object value) {
		String email = (String) value;

		if (email.indexOf('@') == -1) {
			((UIInput) toValidate).setValid(false);

			FacesMessage message = new FacesMessage("Email inválido");
			context.addMessage(toValidate.getClientId(context), message);
		}
	}

}