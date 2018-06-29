package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import entity.Empresa;
import entity.EmpresaUsuario;
import entity.Role;
import entity.Usuario;
import interfaceDao.EmpresaUsuarioDao;
import role.PapeisUsuarios;

public class EmpresaUsuarioDaoHibernate extends Dao implements EmpresaUsuarioDao {

	private DaoGeneric<EmpresaUsuario> empresaUsuarioDaoGeneric = new DaoGeneric<EmpresaUsuario>();
	
	@Override
	public void salvar(EmpresaUsuario empresaUsuario) throws Exception {
		try {
			empresaUsuarioDaoGeneric.salvar(empresaUsuario);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar EmpresaUsuario " + empresaUsuario.getEmpresa().getNome() + e.getMessage(), e);
		}
	}

	@Override
	public void excluir(EmpresaUsuario empresaUsuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persist(EmpresaUsuario empresaUsuario) throws Exception {
		try {
			empresaUsuarioDaoGeneric.persist(empresaUsuario);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar EmpresaUsuario " + empresaUsuario.getEmpresa().getNome() + e.getMessage(), e);
		}
	}

	@Override
	public List<EmpresaUsuario> listar() throws Exception {
		try {   
			List<EmpresaUsuario> todasAsEmpresasDoSistema = new ArrayList<EmpresaUsuario>();
			todasAsEmpresasDoSistema = empresaUsuarioDaoGeneric.getListEntity(EmpresaUsuario.class);
			if(todasAsEmpresasDoSistema.isEmpty())
					throw new Exception("Erro ao obter EmpresaUsuario "); 
			return todasAsEmpresasDoSistema;
		} catch( HibernateException e ) 
		{    
			throw new Exception("Erro ao obter EmpresaUsuario " + e.getMessage(), e);
		}
	}

	@Override
	public EmpresaUsuario buscarEmpresaPrincipal(Usuario usuario) throws Exception {
		Criteria criteria = Dao.getSession().createCriteria(EmpresaUsuario.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.eq("principal", true));
		
		return (EmpresaUsuario) criteria.uniqueResult();
		
	}

}
