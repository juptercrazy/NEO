package dao;

import entity.Empresa;
import entity.Usuario;
import interfaceDao.AlunoDao;

public class AlunoDaoHibernate  extends Dao implements AlunoDao {

	private DaoGeneric<Usuario> alunoDaoGeneric = new DaoGeneric<Usuario>();
			
	@Override
	public Integer obterNumeroAlunos(Empresa empresa) throws Exception {
		
		return Integer.valueOf( ((Long)getSession().
			createQuery("select count(*) from Usuario u join u.roles as role with role.descricao LIKE 'ROLE_ALUNO'").
			uniqueResult()).intValue()) ;
	}

}
