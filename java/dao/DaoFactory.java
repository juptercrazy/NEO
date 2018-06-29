package dao;

import interfaceDao.AgendaDao;
import interfaceDao.AulaDao;
import interfaceDao.EmpresaUsuarioDao;
import interfaceDao.ModeloAulaDao;
import interfaceDao.TipoEmpresaDao;
import interfaceDao.TipoServicoDao;
import interfaceDao.UsuarioDao;

public class DaoFactory {

	public static UsuarioDao criarUsuarioDao() {
		UsuarioDaoHibernate usuarioDao = new UsuarioDaoHibernate();
		
		return usuarioDao;
	}
	
	public static EmpresaDao criarEmpresaDao() {
		EmpresaDao empresaDao = new EmpresaDao();
		
		return empresaDao;
	}
	
	public static RoleDaoHibernate criarRoleDao() {
		RoleDaoHibernate roleDao = new RoleDaoHibernate();
		
		return roleDao;
	}
	
	public static EmpresaUsuarioDao criarEmpresaUsuarioDao() {
		EmpresaUsuarioDaoHibernate empresaUsuarioDao = new EmpresaUsuarioDaoHibernate();
		
		return empresaUsuarioDao;
	}
	
	public static TipoServicoDao criarTipoServicoDao() {
		TipoServicoDaoHibernate servicoDao = new TipoServicoDaoHibernate();
		
		return servicoDao;
	}
	
	public static TipoEmpresaDao criarTipoEmpresaDao() {
		TipoEmpresaDaoHibernate tipoEmpresaDao = new TipoEmpresaDaoHibernate();
		
		return tipoEmpresaDao;
	}
	
	public static AgendaDao criarAgendaDao() {
		AgendaDaoHibernate agendaDao = new AgendaDaoHibernate();
		
		return agendaDao;
	}
	
	public static AulaDao criarAulaDao() {
		AulaDaoHibernate aulaDao = new AulaDaoHibernate();
		
		return aulaDao;
	}
	
	public static ModeloAulaDao criarModeloAulaDao() {
		ModeloAulaDaoHibernate modeloAulaDao = new ModeloAulaDaoHibernate();
		
		return modeloAulaDao;
	}
}