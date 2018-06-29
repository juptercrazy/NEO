package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import entity.Agenda;
import entity.Empresa;
import entity.TipoServico;
import entity.Usuario;
import interfaceDao.AgendaDao;

public class AgendaDaoHibernate extends Dao implements AgendaDao {

	private DaoGeneric<Agenda> agendaDaoGeneric = new DaoGeneric<Agenda>();

	@Override
	public Agenda criarAgenda(Agenda agenda) throws Exception {
		Agenda agnd = null;
		try {
			agnd = (Agenda) agendaDaoGeneric.merge(agenda);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar agenda " + e.getMessage(), e);
		}
		return agnd;
	}

	@Override
	public List<Agenda> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Busca agendas cujo a data de inicio de vigencia seja menor que a data de início da nova agenda
	 * e que a data fim da vigencia seja nula, vigencia com data nula significa agenda vigente; 
	 */
	@Override
	public List<Agenda> buscarAgendasAbertasParaOPeriodo(DateTime inicioVigencia, Empresa empresa, Usuario professor, TipoServico servico) throws Exception {
		Criteria criteria = Dao.getSession().createCriteria(Agenda.class);
		criteria.add(Restrictions.eq("empresa", empresa));
//		criteria.add(Restrictions.or(Restrictions.le("inicioVigencia", inicioVigencia), Restrictions.isNull("fimVigencia")));
		criteria.add(Restrictions.le("inicioVigencia", inicioVigencia));
		criteria.add(Restrictions.isNull("fimVigencia"));
		criteria.add(Restrictions.eq("tipoServico", servico));
		
		return (List<Agenda>) criteria.list();
	}
	
}
