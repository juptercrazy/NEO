package negocio;

import java.util.Date;
import java.util.List;

import bean.ContextoBean;
import dao.DaoFactory;
import entity.Empresa;
import entity.EmpresaUsuario;
import entity.Role;
import entity.Usuario;
import interfaceDao.EmpresaUsuarioDao;
import role.PapeisUsuarios;
import util.ContextoUtil;

public class EmpresaUsuarioNegocio {
	
	private EmpresaUsuarioDao empresaUsuarioDao;
	
	public EmpresaUsuarioNegocio() {
		this.empresaUsuarioDao = DaoFactory.criarEmpresaUsuarioDao();
	}

	public void criarEmpresaUsuario(Empresa empresa, Usuario usuario) throws Exception {

		EmpresaUsuario empresaUsuario = criarNewEmpresaUsuario(empresa, usuario);
		if (existeEmpresaDoUsuario(empresa, usuario)) {
			empresaUsuarioDao.salvar(empresaUsuario);
		}
	}
	
	private boolean existeEmpresaDoUsuario(Empresa empresa, Usuario usuario) throws Exception {
		
		EmpresaUsuario empresaUsuario = this.buscarEmpresaUsuarioPrincipal(usuario);
		
		if (empresaUsuario == null)
		{
			return true;
		}
		return empresaUsuario.getPrincipal().equals(empresa);
	}

	public EmpresaUsuario buscarEmpresaUsuarioPrincipal(Usuario usuario) throws Exception {
		return this.empresaUsuarioDao.buscarEmpresaPrincipal(usuario);
	}

	private EmpresaUsuario criarNewEmpresaUsuario(Empresa empresa, Usuario usuario) {
		EmpresaUsuario empresaUsuario = new EmpresaUsuario();
		
		empresaUsuario.setEmpresa(empresa);
		empresaUsuario.setUsuario(usuario);
		empresaUsuario.setPrincipal(true);
		empresaUsuario.setDataRegistro(new Date());
		return empresaUsuario;
	}

	private Empresa obterEmpresaPrincipalDoUsuario() throws Exception {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		return contextoBean.obterEmpresaPrincipalDoUsuario();
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
}
