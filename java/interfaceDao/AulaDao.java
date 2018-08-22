package interfaceDao;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import entity.Agenda;
import entity.Aula;
import entity.Empresa;
import entity.TipoServico;
import entity.Usuario;

public interface AulaDao {
	public void criarAula(Aula aula) throws Exception;

	public List<Aula> listar();

	public List<Aula> buscarAulaEmpresadia(Empresa empresa, Date dia);
	
	public List<Aula> buscarAulasAbertasParaOPeriodo(DateTime inicioVigencia, Empresa empresa, Usuario professor, TipoServico servico) throws Exception;
	
	public List<Aula> buscarAulasParaOPeriodo(DateTime inicioPeriodo, DateTime fimPeriodo, Empresa empresa) throws Exception;
}
