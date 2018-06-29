package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.Usuario;
import negocio.UsuarioNegocio;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private Usuario usuario = new Usuario();

	private UsuarioNegocio usuarioNegocio;

	public LoginBean() {
		usuarioNegocio = new UsuarioNegocio();
	}

	public String validarUser() {
		try {
			Usuario usuarioPersistido = usuarioNegocio.getUsuarioPeloCPF(usuario.getCpf());

			System.out.println(usuarioPersistido.getNome());

			if(usuarioPersistido != null) 
				return "/proprietario/template_home.xhtml";
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String link_criar_usuario() {
		return "/publico/cadastro/proprietario/cadastrar_novo_usuario_template.xhtml";

	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
