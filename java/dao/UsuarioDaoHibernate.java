package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import entity.Empresa;
import entity.Usuario;
import interfaceDao.UsuarioDao;
import role.PapeisUsuarios;

public class UsuarioDaoHibernate extends Dao implements UsuarioDao {
	
	private DaoGeneric<Usuario> usuarioDaoGeneric = new DaoGeneric<Usuario>();
	
	public void create(Usuario usuario) throws Exception {
		try {
			usuarioDaoGeneric.salvar(usuario);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar usuario " + usuario.getNome() + e.getMessage(), e);
		}
	}

	public Usuario getUsuarioPeloNome(String username) {
		try {    

			begin();    
			Query q = getSession().createQuery("from Usuario where nome = :username");
			q.setString("username",username);
			Usuario user = (Usuario)q.uniqueResult();
			commit(); 
			return user; 
		} catch( HibernateException e ) 
		{    
			rollback(); 
		}
		return null;
	}

	public Usuario getUsuarioPeloCPF(String cpf_login)  {
		try {    

			begin();    
			Query q = getSession().createQuery("from Usuario where cpf = :cpf");
			q.setString("cpf",cpf_login);
			Usuario user = (Usuario)q.uniqueResult();
			commit(); 
			return user; 
		} catch( HibernateException e ) 
		{    
			rollback(); 
		}
		return null;
	}

	public void salvar(Usuario usuario) {
		usuarioDaoGeneric.salvar(usuario);
	}

	public Usuario carregar(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Usuario> listar() {
		try {    
			begin();    
			List<Usuario> usuarios = getSession().createQuery("from Usuario").list();
			close(); 
			return usuarios; 
		} catch( HibernateException e )
		{    
			rollback(); 
		}
		return null;
	}

	@Override
	public List<Usuario> obterUsuariosDeEmpresaPorPapel(Empresa empresa) {
		Criteria criteria = Dao.getSession().createCriteria(Usuario.class);
		criteria.createAlias("roles", "rolesAlias");
		criteria.add(Restrictions.eq("rolesAlias.descricao", PapeisUsuarios.ROLE_FUNCIONARIO.name()));
		criteria.createAlias("empresaUsuarios", "empUsersAlias");
		criteria.add(Restrictions.eq("empUsersAlias.empresa", empresa));
		
		return (List<Usuario>) criteria.list();
	}

	@Override
	public Usuario buscarPorID(Long id) throws Exception {
		try {
			return (Usuario) usuarioDaoGeneric.getObjetoPorID(Usuario.class, id);
		} catch (HibernateException e) {
			throw new Exception("Erro ao consultar objeto Usuario pelo id " +  e.getMessage(), e);
		}
	}

}
