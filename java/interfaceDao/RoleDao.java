package interfaceDao;

import java.util.List;

import entity.Empresa;
import entity.Role;
import entity.Usuario;

public interface RoleDao {

	public void criarRole(Role role) throws Exception;
	
	public List<Role> obterTodasPermisspes() throws Exception;
	
	public Role obterPermissaoDoUsuario(Usuario usuario) throws Exception;
	
	public Role obterPermissaoEspecifica(String desc) throws Exception;
	
	public void consultarRolesEmpresaTipo(Empresa empresa, String tipo) throws Exception;
		
}