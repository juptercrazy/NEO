package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import entity.Empresa;
import entity.TipoEmpresa;

public class EmpresaDao extends Dao {
	
	private DaoGeneric<Empresa> empresaDaoGeneric = new DaoGeneric<Empresa>();
	
	public void salvar(Empresa newempresa) throws Exception {
		try {
			System.out.println("Salvar Empresa !!");
			empresaDaoGeneric.salvar(newempresa);
		} catch (HibernateException e) {
			throw new Exception("Erro ao criar Empresa " + newempresa.getNome() + e.getMessage(), e);
		}
	}

	public Empresa obterEmpresaPeloNome(String nomeEmpresa) {
		try {    
			begin();    
			Query q = getSession().createQuery("from Empresa where nome = :nome");
			q.setString("nome",nomeEmpresa);
			Empresa empresa = (Empresa)q.uniqueResult();
//			System.out.println(empresa.getNome());
			commit(); 
			return empresa; 
		} catch( HibernateException e ) 
		{    
			rollback(); 
		}
		return null;
	}
	
	public Empresa obterEmpresaPeloCnpj(Long cnpj) {
		try {    
			begin();    
			Query q = getSession().createQuery("from Empresa where cnpj = :cnpj");
			q.setLong("cnpj", cnpj);
			Empresa empresa = (Empresa)q.uniqueResult();
			commit(); 
			return empresa; 
		} catch( HibernateException e ) 
		{    
			rollback(); 
		}
		return null;
	}
	
	public List<Empresa> obterEmpresas() {
		try {    
			begin();    
			List<Empresa> empresa = getSession().createQuery("from Empresa").list();
			close(); 
			return empresa; 
		} catch( HibernateException e ) 
		{    
			rollback(); 
		}
		return null;
	}

}
