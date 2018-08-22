package negocio;

import java.util.List;

import dao.DaoFactory;
import entity.Empresa;
import entity.TipoServico;
import interfaceDao.TipoServicoDao;

public class TipoServicoNegocio {

	private TipoServicoDao servicoDao;

	public TipoServicoNegocio() {
		new DaoFactory();
		this.servicoDao = DaoFactory.criarTipoServicoDao();
	}

	public void salvarServico(TipoServico servico) throws Exception {

		System.out.println("Tipo de servi�o - Empresa");
		if (existeServico(servico)) {
			servicoDao.criarServico(servico);
		}
	}

	private boolean existeServico(TipoServico servico) throws Exception {

		return this.obterTipoServicoEspecificoDaEmpresa(servico.getEmpresa(), servico) == null;
	}

	public List<TipoServico> obterTipoServicoDaEmpresa(Empresa empresa) throws Exception {
		// TODO Auto-generated method stub
		List<TipoServico> listaDeServicos = servicoDao.buscarTipoServicoPorEmpresa(empresa);

		return listaDeServicos;
	}
	
	public TipoServico obterTipoServicoEspecificoDaEmpresa(Empresa empresa, TipoServico servico) throws Exception
	{
		return servicoDao.buscarTipoServicoEspecificaDaEmpresa(empresa, servico);
	}

	public TipoServico obterServicoPorID(Long id) throws Exception {
		return servicoDao.buscarPorID(id);
	}
}
