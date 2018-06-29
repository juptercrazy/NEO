package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import entity.TipoEmpresa;
import entity.Usuario;
import interfaceDao.TipoEmpresaDao;

public class TipoEmpresaDaoHibernate extends Dao implements TipoEmpresaDao {

	private DaoGeneric<TipoEmpresa> tipoEmpresaDaoGeneric = new DaoGeneric<TipoEmpresa>();
	
	public List<TipoEmpresa> obterTodosTiposEmpresa() {
		begin();    
		List<TipoEmpresa> tiposEmpresa = getSession().createQuery("from TipoEmpresa").list();
		close(); 
		return tiposEmpresa; 
	}

	public void create(TipoEmpresa newTipoEmpresa)  throws Exception {
		try {
			tipoEmpresaDaoGeneric.salvar(newTipoEmpresa);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar tipoEmpresa " + newTipoEmpresa.getDescricao() + e.getMessage(), e);
		}
	}

	public TipoEmpresa find( Long id)  throws Exception {

		try {
			begin();    

			Query q = getSession().createQuery("from TipoEmpresa where id = :id");
			q.setLong("id",id);
			TipoEmpresa tipoEmp = (TipoEmpresa)q.uniqueResult();
			System.out.println(tipoEmp.getDescricao());
//			commit(); 
			close();
			return tipoEmp; 
		} catch (HibernateException e) {
			throw new Exception("Erro ao procurar Tipo Empresa " + id + e.getMessage(), e);
		}
	}

	@Override
	public TipoEmpresa buscarTipoEmpresaPorDescricao(String desc) throws Exception {
		try {
			begin();    

			Query q = getSession().createQuery("from TipoEmpresa where descricao = :desc");
			q.setString("desc",desc);
			TipoEmpresa tipoEmp = (TipoEmpresa)q.uniqueResult();
			close();
			return tipoEmp; 
		} catch (HibernateException e) {
			throw new Exception("Erro ao procurar Tipo Empresa " + desc + e.getMessage(), e);
		}
	}

}
