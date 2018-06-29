package negocio;

import java.util.List;

import bean.ContextoBean;
import dao.DaoFactory;
import entity.Empresa;
import entity.EmpresaUsuario;
import entity.Role;
import entity.Usuario;
import interfaceDao.UsuarioDao;
import role.PapeisUsuarios;
import util.ContextoUtil;

public class UsuarioNegocio {
	
	private UsuarioDao usuarioDao;
	
	public UsuarioNegocio() {
		this.usuarioDao = DaoFactory.criarUsuarioDao();
	}
	
	public Usuario criarProprietario(Usuario usuario, Empresa empresa) throws Exception {
		
		System.out.println("Empresa do usuario");
		EmpresaNegocio empresaN = new EmpresaNegocio();
		empresaN.criarEmpresa(empresa);
		
		System.out.println("papel do usuario");
		Role papelPersistido = obterTipoUsuarioProprietario();	
		
		usuario.addRoles(papelPersistido);
		
		System.out.println("Salvar do usuario");
		if(existeUsuario(usuario)) {
			usuarioDao.salvar(usuario);
		}
		
		System.out.println("Empresa do usuario");
		EmpresaUsuarioNegocio empresaUsuarioN = new EmpresaUsuarioNegocio();
		empresaUsuarioN.criarEmpresaUsuario(empresa, usuario);
		
		return usuario;
	}
	
	private boolean existeUsuario(Usuario usuario) throws Exception {
		return this.usuarioDao.getUsuarioPeloCPF(usuario.getCpf()) == null;
	}

	public Usuario criarUsuarioFuncionario(Usuario usuario) throws Exception {
		
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		
		Role papelPersistido = obterTipoUsuarioFuncionario();	
		
		usuario.addRoles(papelPersistido);
		
		usuarioDao.salvar(usuario);
		
		System.out.println("Empresa do usuario");
		EmpresaUsuarioNegocio empresaUsuarioN = new EmpresaUsuarioNegocio();
		empresaUsuarioN.criarEmpresaUsuario(contextoBean.obterEmpresaPrincipalDoUsuario(), usuario);
		
		return usuario;
	}
	
	public void criarUsuarioAluno(Usuario usuario) throws Exception  {
		// TODO Auto-generated method stub
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		
		Role papelPersistido = obterTipoUsuarioAluno();	
		
		usuario.addRoles(papelPersistido);
		
		usuarioDao.salvar(usuario);
		
		System.out.println("Empresa do usuario");
		EmpresaUsuarioNegocio empresaUsuarioN = new EmpresaUsuarioNegocio();
		empresaUsuarioN.criarEmpresaUsuario(contextoBean.obterEmpresaPrincipalDoUsuario(), usuario);
		
	}

	private Role obterTipoUsuarioFuncionario() {
		RoleNegocio roleNegocio = new RoleNegocio();
		try {
			return roleNegocio.obterPermissaoPelaDescricao(PapeisUsuarios.ROLE_FUNCIONARIO.name());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Role obterTipoUsuarioProprietario() {
		RoleNegocio roleNegocio = new RoleNegocio();
		try {
			return roleNegocio.obterPermissaoPelaDescricao(PapeisUsuarios.ROLE_PROPRIETARIO.name());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Role obterTipoUsuarioAluno() {
		RoleNegocio roleNegocio = new RoleNegocio();
		try {
			return roleNegocio.obterPermissaoPelaDescricao(PapeisUsuarios.ROLE_ALUNO.name());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario getUsuarioPeloCPF(String cpf) throws Exception {
		
		return usuarioDao.getUsuarioPeloCPF(cpf);
	}

	public List<Usuario> obterProfessoresDaEmpresa() throws Exception {
		Empresa empresa = obterEmpresaPrincipalDoUsuario();
		return usuarioDao.obterUsuariosDeEmpresaPorPapel(empresa);
	}
	
	private Empresa obterEmpresaPrincipalDoUsuario() throws Exception {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		return contextoBean.obterEmpresaPrincipalDoUsuario();
	}

	public Usuario obterUsuarioPorID(Long id) throws Exception {
		return usuarioDao.buscarPorID(id);
	}
}
