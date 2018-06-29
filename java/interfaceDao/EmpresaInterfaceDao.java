package interfaceDao;

import java.util.List;

import entity.Empresa;

public interface EmpresaInterfaceDao {

	public void salvar(Empresa empresa);
	public Empresa carregar(Integer codigo);
	public List<Empresa> listar();
}
