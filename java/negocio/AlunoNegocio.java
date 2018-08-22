package negocio;

import dao.DaoFactory;
import entity.Empresa;
import interfaceDao.AlunoDao;

public class AlunoNegocio {

	
	private AlunoDao alunoDao;
	
	public AlunoNegocio() {
		alunoDao = DaoFactory.criarAlunoDao();
	}
	
	public Integer obterNumAlunos(Empresa empresa) {
		
		try {
			return alunoDao.obterNumeroAlunos(empresa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
