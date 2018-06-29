package interfaceDao;

import java.util.List;

import org.joda.time.DateTime;

import entity.Agenda;
import entity.Empresa;
import entity.TipoServico;
import entity.Usuario;

public interface AgendaDao {

	public Agenda criarAgenda(Agenda agenda) throws Exception;
	
	public List<Agenda> listar();

	public List<Agenda> buscarAgendasAbertasParaOPeriodo(DateTime inicioVigencia, Empresa empresa, Usuario professor, TipoServico servico) throws Exception;
}
