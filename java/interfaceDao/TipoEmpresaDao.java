package interfaceDao;

import java.util.List;

import entity.TipoEmpresa;

public interface TipoEmpresaDao {
	
	public List<TipoEmpresa> obterTodosTiposEmpresa() throws Exception;
		
	public void create(TipoEmpresa newTipoEmpresa)  throws Exception;
	
	public TipoEmpresa find( Long id)  throws Exception;
	
	public TipoEmpresa buscarTipoEmpresaPorDescricao( String desc)  throws Exception;
}
