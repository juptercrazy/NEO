package negocio;

import java.util.List;

import bean.ContextoBean;
import dao.DaoFactory;
import entity.Empresa;
import entity.ModeloAula;
import interfaceDao.ModeloAulaDao;
import util.ContextoUtil;

public class ModeloAulaNegocio {
	
	private ModeloAulaDao modeloAulaDao;
	
	public ModeloAulaNegocio() {
		modeloAulaDao = DaoFactory.criarModeloAulaDao();
	}
	
	public void criarModeloAula(ModeloAula modeloAula) throws Exception {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		modeloAula.setEmpresas(contextoBean.obterEmpresaPrincipalDoUsuario());
		modeloAulaDao.criarModeloAula(modeloAula);
	}

	public List<ModeloAula> obterModelosDeAulaDaEmpresa(Empresa empresa)  throws Exception {
		// TODO Auto-generated method stub
		return modeloAulaDao.obterModeloDeAulaDaEmpresa(empresa);
	}

	public ModeloAula obterServicoPorID(Long id) throws Exception {
		return modeloAulaDao.buscarPorID(id);
	}
}
