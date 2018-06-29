package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.DaoFactory;
import entity.TipoEmpresa;
import interfaceDao.TipoEmpresaDao;

public class TipoEmpresaNegocio {

	private TipoEmpresaDao tipoEmpresaDao;
	private List<TipoEmpresa> listaTipoEmpresa = new ArrayList<TipoEmpresa>();

	public TipoEmpresaNegocio() {
		tipoEmpresaDao = DaoFactory.criarTipoEmpresaDao();
	}
	
	public void criarTipoEmpresa(TipoEmpresa tipoEmpresa) throws Exception {

		if(buscarPorDescricao(tipoEmpresa.getDescricao()) == null ) {
			tipoEmpresaDao.create(tipoEmpresa);
		}
	}
	
	public TipoEmpresa findById(Long id) throws Exception {

		TipoEmpresa tipoEmpresa =  tipoEmpresaDao.find(id);

		return tipoEmpresa;
	}
	
	public TipoEmpresa buscarPorDescricao(String descricao) throws Exception {

		TipoEmpresa tipoEmpresa =  tipoEmpresaDao.buscarTipoEmpresaPorDescricao(descricao);

		return tipoEmpresa;
	}

	public List<TipoEmpresa> popularLista() throws Exception {

		if(this.listaTipoEmpresa.isEmpty())
		 listaTipoEmpresa =  tipoEmpresaDao.obterTodosTiposEmpresa();

		return listaTipoEmpresa;
	}
}
