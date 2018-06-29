package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import entity.Empresa;
import entity.Role;
import entity.Usuario;
import interfaceDao.RoleDao;

public class RoleDaoHibernate extends Dao implements RoleDao {

	@Override
	public void criarRole(Role role) throws Exception {
		try {
			begin();
			getSession().save(role);
			commit();
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar tipo de Usuário " + role.getDescricao() + e.getMessage(), e);
		}
	}

	@Override
	public List<Role> obterTodasPermisspes() throws Exception {
		begin();
		@SuppressWarnings("unchecked")
		List<Role> roles = getSession().createQuery("from Role").list();
		close();
		return roles;
	}

	@Override
	public Role obterPermissaoDoUsuario(Usuario usuario) throws Exception {
		
		return null;
	}

	

	@Override
	public Role obterPermissaoEspecifica(String desc) throws Exception {
		begin();
		Query q = getSession().createQuery("from Role where descricao = :desc");
		q.setString("desc", desc);
		Role papel = (Role) q.uniqueResult();
		close();
		return papel;
	}

	@Override
	public void consultarRolesEmpresaTipo(Empresa empresa, String tipo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
