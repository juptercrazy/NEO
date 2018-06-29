package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import entity.Empresa;
import entity.ModeloAula;
import interfaceDao.ModeloAulaDao;

public class ModeloAulaDaoHibernate extends Dao implements ModeloAulaDao {

	private DaoGeneric<ModeloAula> modeloAulaDaoGeneric = new DaoGeneric<ModeloAula>();

	@Override
	public void criarModeloAula(ModeloAula modeloAula) throws Exception {
		try {
			modeloAulaDaoGeneric.salvar(modeloAula);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar Modelo de Aula" + e.getMessage(), e);
		}		
	}

	@Override
	public List<ModeloAula> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModeloAula> obterModeloDeAulaDaEmpresa(Empresa empresa) {
		Criteria criteria = Dao.getSession().createCriteria(ModeloAula.class);
		criteria.add(Restrictions.eq("empresas", empresa));
		
		return (List<ModeloAula>) criteria.list();
	}

	@Override
	public ModeloAula buscarPorID(Long id) throws Exception {
		try {
			return (ModeloAula) modeloAulaDaoGeneric.getObjetoPorID(ModeloAula.class, id);
		} catch (HibernateException e) {
			throw new Exception("Erro ao consultar objeto modelo Aula pelo id " +  e.getMessage(), e);
		}
	}
	
}