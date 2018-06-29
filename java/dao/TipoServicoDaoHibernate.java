package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import entity.Empresa;
import entity.EmpresaUsuario;
import entity.TipoServico;
import entity.Usuario;
import interfaceDao.TipoServicoDao;

public class TipoServicoDaoHibernate implements TipoServicoDao {

	private DaoGeneric<TipoServico> servicoDaoGeneric = new DaoGeneric<TipoServico>();
	
	@Override
	public void criarServiço(TipoServico servico) throws Exception {
		try {
			servicoDaoGeneric.salvar(servico);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar Tipo de serviço para a empresa " + servico.getEmpresa().getNome() + e.getMessage(), e);
		}
	}

	@Override
	public List<TipoServico> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoServico> buscarTipoServicoPorEmpresa(Empresa empresa) throws Exception {
		Criteria criteria = Dao.getSession().createCriteria(TipoServico.class);
		criteria.add(Restrictions.eq("empresa", empresa));
				
		return criteria.list();
		
	}

	@Override
	public TipoServico buscarTipoServicoEspecificaDaEmpresa(Empresa empresa, TipoServico servico) throws Exception {
		Criteria criteria = Dao.getSession().createCriteria(TipoServico.class);
		criteria.add(Restrictions.eq("empresa", empresa));
		criteria.add(Restrictions.eq("descricao", servico.getDescricao()));
		
		return (TipoServico) criteria.uniqueResult();
	}

	@Override
	public TipoServico buscarPorID(Long id) throws Exception {
		try {
			return (TipoServico) servicoDaoGeneric.getObjetoPorID(TipoServico.class, id);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar Tipo de serviço para a empresa " +  e.getMessage(), e);
		}
	}

}
