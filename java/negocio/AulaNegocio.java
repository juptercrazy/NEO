package negocio;

import dao.DaoFactory;
import entity.Aula;
import interfaceDao.AulaDao;

public class AulaNegocio {
	
	private AulaDao aulaDao;
	
	public AulaNegocio() {
		aulaDao = DaoFactory.criarAulaDao();
	}
	
	public void criarAula(Aula aula) throws Exception {
		
		aulaDao.criarAula(aula);
	}
}
