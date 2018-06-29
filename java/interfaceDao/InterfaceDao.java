package interfaceDao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public interface InterfaceDao<E> extends Serializable {

	void salvar(E entidade) throws Exception;
	
	void persit(E entidade) throws Exception;
	
	// salva e atualiza o objeto
	void salvarOuAtualizar(E entidade) throws Exception;
	
	void update(E entidade) throws Exception;
	
	void deletar(E entidade) throws Exception;
	
//	// salva ou atualiza o objeto e retorna o objeto persistido
//	T merge(E entidade) throws Exception;
//	
//	Object procurarPorID(Class<T> obj, Long id) throws Exception;
//
//	T procurarPorID_T(Class<T> obj, Long id) throws Exception;
//
//	Object carregar(Integer codigo) throws Exception;
//	
//	List<T> listar(Class<T> obj) throws Exception;
//	
////	List<T> procularListaPorQueryDinamica(String query) throws Exception;
	
	void executarUpdateQueryDinamica(String query) throws Exception;
	
	void executarUpdateSQLDinamica(String query) throws Exception;
	
	void clearSession() throws Exception;
	
	void evict (Object obj) throws Exception;
	
	Session getSessin() throws Exception;
	
	List<?> getListSqlDinaica(String sql) throws Exception;
	
	Long totalRegistros(String table) throws Exception;
	
	Query obterQuery(String query) throws Exception;

//	List<T> procularListaPorQueryDinamica(String query, int inicioRegistro, int maxRegistros) throws Exception;	
}
