package negocio;

import java.util.List;

import dao.RoleDaoHibernate;
import entity.Empresa;
import entity.Role;
import interfaceDao.RoleDao;

public class RoleNegocio {
	
	RoleDao dao;

	public RoleNegocio() {
		this.dao = new RoleDaoHibernate();
	}
	
	public void criarRole(Role role) throws Exception {
		if (existeRole(role))
			this.dao.criarRole(role);
	}
	
	private boolean existeRole(Role role) throws Exception {
		return this.obterPermissaoPelaDescricao(role.getDescricao()) == null;
	}

	public Role obterPermissaoPelaDescricao(String descricao ) throws Exception {
		
		return dao.obterPermissaoEspecifica(descricao);	
	}

	public List<Role> popularLista() throws Exception {
		
		return dao.obterTodasPermisspes();
	}

	public void consultarRolesEmpresaTipo(Empresa empresa, String tipo) throws Exception {
		// TODO Auto-generated method stub
		dao.consultarRolesEmpresaTipo(empresa, tipo);
	}
	

}
