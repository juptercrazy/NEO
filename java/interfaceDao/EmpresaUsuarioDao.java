package interfaceDao;

import java.util.List;

import entity.Empresa;
import entity.EmpresaUsuario;
import entity.Role;
import entity.Usuario;

public interface EmpresaUsuarioDao {

	public void salvar(EmpresaUsuario empresaUsuario) throws Exception;
	public void persist(EmpresaUsuario empresaUsuario) throws Exception;
	public void excluir(EmpresaUsuario empresaUsuario) throws Exception;
	public List<EmpresaUsuario> listar() throws Exception;
	public EmpresaUsuario  buscarEmpresaPrincipal(Usuario usuario) throws Exception;
}
