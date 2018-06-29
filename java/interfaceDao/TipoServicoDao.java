package interfaceDao;

import java.util.List;

import entity.Empresa;
import entity.TipoServico;

public interface TipoServicoDao {

	public void criarServiço(TipoServico servico) throws Exception;
	
	public List<TipoServico> buscarTipoServicoPorEmpresa(Empresa empresa) throws Exception;
	
	public List<TipoServico> listar() throws Exception;

	public TipoServico buscarTipoServicoEspecificaDaEmpresa(Empresa empresa, TipoServico servico) throws Exception;
	
	public TipoServico buscarPorID(Long id) throws Exception;
}
