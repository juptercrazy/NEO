package dao;

import java.util.List;

import entity.TipoEmpresa;

public class DaoGeneric<E> extends Dao {

	public void salvar(E entidade) {
		
		begin();
		getSession().flush();
		getSession().save(entidade);
		System.out.println(" entidade generica salva	");
		commit();
	}
	
    public void persist(E entidade) {
		
		begin();
		getSession().persist(entidade);
		System.out.println(" entidade generico salvo	");
		commit();
	}
    
    
    
    public Object merge(E entidade) {
		
		begin();
		Object obj = getSession().merge(entidade);
		System.out.println(" entidade generico  MERGE salvo	");
		commit();
		
		return obj;
	}
    
    public void salvarOuAtualizar(E entidade) {
		
		begin();
		getSession().saveOrUpdate(entidade);
		System.out.println(" entidade generico salvo	");
		commit();
	}
	
	public List<E> getListEntity(Class<E> entidade) throws Exception {
		System.out.println("Get List!!");
		begin();
		StringBuilder query = new StringBuilder();
		query.append(" from ").append(entidade.getSimpleName());
		List<E> resultados = getSession().createQuery(query.toString()).list();
		close();
		return resultados;
	}
	
	public Object getObjetoPorID(Class<E> entidade, Long id) throws Exception {
		begin();
		Object resultados = getSession().get(entidade, id);
		close();
		
		return resultados;
	}
	
}
