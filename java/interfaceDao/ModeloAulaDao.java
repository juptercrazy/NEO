package interfaceDao;

import java.util.List;

import entity.Empresa;
import entity.ModeloAula;

public interface ModeloAulaDao {
	
	public void criarModeloAula(ModeloAula aula) throws Exception;

	public List<ModeloAula> listar();

	public List<ModeloAula> obterModeloDeAulaDaEmpresa(Empresa empresa)  throws Exception;

	public ModeloAula buscarPorID(Long id)  throws Exception;

}
