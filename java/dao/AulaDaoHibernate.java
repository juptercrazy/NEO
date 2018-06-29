package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import entity.Agenda;
import entity.Aula;
import entity.Empresa;
import entity.TipoServico;
import entity.Usuario;
import interfaceDao.AulaDao;

public class AulaDaoHibernate extends Dao implements AulaDao {

	private DaoGeneric<Aula> aulaDaoGeneric = new DaoGeneric<Aula>();
	
	@Override
	public void criarAula(Aula aula) throws Exception {
		try {
			aulaDaoGeneric.salvar(aula);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar aula" + e.getMessage(), e);
		}
	}

	@Override
	public List<Aula> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aula> buscarAulaEmpresadia(Empresa empresa, Date dia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aula> buscarAulasAbertasParaOPeriodo(DateTime inicioVigencia, Empresa empresa, Usuario professor,
			TipoServico servico) throws Exception {
		Criteria criteria = Dao.getSession().createCriteria(Aula.class);
		criteria.add(Restrictions.eq("empresa", empresa));
//		criteria.add(Restrictions.or(Restrictions.le("inicioVigencia", inicioVigencia), Restrictions.isNull("fimVigencia")));
		criteria.add(Restrictions.le("inicioVigencia", inicioVigencia));
		criteria.add(Restrictions.isNull("fimVigencia"));
		criteria.add(Restrictions.eq("tipoServico", servico));
//		criteria.add(Restrictions.eq("usuario", professor));
		
		return (List<Aula>) criteria.list();
	}



}
