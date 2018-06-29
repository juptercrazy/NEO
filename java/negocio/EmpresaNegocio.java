package negocio;

import bean.ContextoBean;
import dao.DaoFactory;
import dao.EmpresaDao;
import entity.Empresa;
import entity.TipoServico;
import util.ContextoUtil;

public class EmpresaNegocio {
private EmpresaDao empresaDao;
	
	public EmpresaNegocio() {
		this.empresaDao = DaoFactory.criarEmpresaDao();
	}
	
	public void criarEmpresa(Empresa empresa) throws Exception  {
		if (existeEmpresa(empresa))
				this.empresaDao.salvar(empresa);
	}
	
	public void adicionarTipoServico(TipoServico servico) throws Exception  {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		Empresa empresa = contextoBean.obterEmpresaPrincipalDoUsuario();
		empresa.addTipoServico(servico);
		this.empresaDao.salvar(empresa);
	}

	private boolean existeEmpresa(Empresa empresa) {
		return this.empresaDao.obterEmpresaPeloNome(empresa.getNome()) == null;
	}

	public Empresa obterEmpresaPeloCNPJ(Long cnpj) {
		return this.empresaDao.obterEmpresaPeloCnpj(cnpj);
	}
}