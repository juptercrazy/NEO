package interfaceDao;

import java.util.List;

import entity.Empresa;
import entity.Role;
import entity.Usuario;

public interface UsuarioDao {

	public void create(Usuario usuario) throws Exception;

	public Usuario getUsuarioPeloNome(String username) throws Exception;

	public Usuario getUsuarioPeloCPF(String cpf_login) throws Exception;

	public void salvar(Usuario usuario) throws Exception;

	public Usuario carregar(Integer codigo) throws Exception;
	
	public List<Usuario> listar() throws Exception;
	
	public List<Usuario> obterUsuariosDeEmpresaPorPapel(Empresa empresa) throws Exception;

	public Usuario buscarPorID(Long id) throws Exception;
	
	public List<Usuario> obterAlunosPorEmpresa(Empresa empresa) throws Exception;
}
