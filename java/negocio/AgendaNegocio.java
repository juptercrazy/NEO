package negocio;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import dao.DaoFactory;
import entity.Agenda;
import entity.Aula;
import entity.Empresa;
import entity.ModeloAula;
import entity.TipoServico;
import entity.Usuario;
import interfaceDao.AgendaDao;
import interfaceDao.AulaDao;

public class AgendaNegocio {
	
	private AulaDao aulaDao;
	private List<Aula> aulasEmConflito = new ArrayList<Aula>();
	
	public AgendaNegocio() {
		aulaDao =  DaoFactory.criarAulaDao();
	}
	
	public void salvarAgenda(Agenda agenda, ModeloAula modeloAula, List<Integer> diasSemana) throws Exception {

//		Agenda agendaPersistida = agendaDao.criarAgenda(agenda);
		for(Integer dia: diasSemana) 
		{
			List<Aula> aulas = criarAulasDaAgenda(agenda, agenda.getHoraInicio(), agenda.getHoraFim(), modeloAula, dia);
		}
	}
	
	public List<Aula> validarInsercaoAgenda(Agenda agenda, ModeloAula modeloAula, List<Integer> diasSemana) throws Exception {

		List<Aula> aulas = new ArrayList<Aula>();
		for(Integer dia: diasSemana) 
		{
			aulas = criarAulasDaAgenda(agenda, agenda.getHoraInicio(), agenda.getHoraFim(), modeloAula, dia);
		}
		
		return aulas;
	}

	private List<Aula> criarAulasDaAgenda(Agenda agenda, LocalTime horaInicio, LocalTime horafim, ModeloAula modeloAula, Integer dia) throws Exception {
		List<Aula> aulas = new ArrayList<Aula>();
		
		
		
		Period duracaoAgenda = calcularDuracaoAgenda(horaInicio, horafim);
		
		int numeroAulas = calcularQuantidadeAulasdaAgenda(modeloAula.getDuracao(), (duracaoAgenda.getHours() * 60));
		
		LocalTime horaInicioAgenda = horaInicio;
		
		LocalTime horaFimAula = null;
		
		LocalTime horaInicioAula = horaInicio;
		
		for (int i = 0; i < numeroAulas; i++) {
			horaFimAula = horaInicioAgenda.plusMinutes(modeloAula.getDuracao()  * (i+1));
			Aula novaAula = criarObjetoAula(agenda, horaInicioAula, horaFimAula, dia, modeloAula.getMaxAlunosAula());

			//Validar se existe aula para o mesmo professor no mesmo horário e mesmo dia
			aulasEmConflito.addAll(validarConflitoDeAula(novaAula));
			
			salvarAula(novaAula);
			horaInicioAula = horaFimAula;
			aulas.add(novaAula);
		}

		return aulas;
	}

	private Aula criarObjetoAula(Agenda agenda, LocalTime horaInicioAula, LocalTime horaFimAula, Integer dia, Integer maxAlunos) {
		Aula novaAula = new Aula();
		novaAula.setHoraInicioAula(horaInicioAula);
		novaAula.setHoraFimAula(horaFimAula);
		novaAula.setNumeroAlunos(maxAlunos);
//		novaAula.setAgenda(agendaPersist);
		novaAula.setDiaDaSemana(dia);
		novaAula.setInicioVigencia(agenda.getInicioVigencia());
		novaAula.setFimVigencia(agenda.getFimVigencia());
		novaAula.setEmpresa(agenda.getEmpresa());
		novaAula.setProfessor(agenda.getProfessor());
		novaAula.setTipoServico(agenda.getTipoServico());
		
		return novaAula;
	}
	
	private void salvarAula(Aula novaAula) throws Exception {
		// TODO Auto-generated method stub
		AulaNegocio negocio = new AulaNegocio();
		negocio.criarAula(novaAula);
	}

	/**
	 * @param modeloAula
	 * @param duracaoAgenda
	 * @return
	 */
	private int calcularQuantidadeAulasdaAgenda(int duracaoEmHoras, int duracaoAgendaEmHoras) {
		return duracaoAgendaEmHoras/duracaoEmHoras;
	}

	/**
	 * @param agenda
	 * @return
	 */
	private Period calcularDuracaoAgenda(LocalTime horaInicioAgenda, LocalTime horaFimAgenda) {
		return new Period(horaInicioAgenda, horaFimAgenda);
	}
	
	private List<Aula> validarConflitoDeAula(Aula aula) throws Exception {
		List<Aula> aulasAbertas = buscarAulasAberta(aula.getInicioVigencia(), aula.getEmpresa(), aula.getProfessor(), aula.getTipoServico());
		List<Aula> aulasEmConflito = new ArrayList<Aula>();
		for (Aula aulaAberta: aulasAbertas) {
				aulasEmConflito.add(validarAulasDaAgendaContidaEmAgendaAberta(aulaAberta, aula));
				
				//Fechar as aulas existentes em conflito
//				encerrarAulasEmConflito(aulasEmConflito);
		}
		
		return aulasEmConflito;
	}
	
	

	/**
	 * Validar se as aulas da agendas, horário início e fim, e dia da semana estão em conflito com a nova agenda 
	 * @param aulaAberta
	 * @param agenda
	 */
	private Aula validarAulasDaAgendaContidaEmAgendaAberta(Aula aulaAberta, Aula novaAula) {

		return compararAulacomAula(aulaAberta, novaAula)? aulaAberta: null;
	}

	protected boolean compararAulacomAula(Aula aulaAgendaAberta, Aula aula) {
		if (validarDiadaAula(aulaAgendaAberta.getDiaDaSemana(), aula.getDiaDaSemana()))
		{
			if (validarHorarioDasAulas(aulaAgendaAberta.getHoraInicioAula(), aulaAgendaAberta.getHoraFimAula(), aula.getHoraInicioAula(), aula.getHoraFimAula())) {
				encerrarAulaEmConflito(aulaAgendaAberta, aula.getInicioVigencia());
				return true;
			}
		}
		return false;
	}

	boolean validarHorarioDasAulas(LocalTime horaInicioAulaExistente, LocalTime horaFimAulaExistente, LocalTime horaInicioNovaAula,
			LocalTime horaFimNovaAula) {
		if ((horaInicioAulaExistente.isAfter(horaInicioNovaAula) ||  horaInicioAulaExistente.isEqual(horaInicioNovaAula)) && horaInicioAulaExistente.isBefore(horaFimNovaAula))
				return true;
		if (horaFimAulaExistente.isAfter(horaInicioNovaAula) && horaFimAulaExistente.isBefore(horaFimNovaAula))
			return true;
		return false;
	}

	boolean validarDiadaAula(Integer diaDaSemana, Integer diaDaSemana2) {
		return diaDaSemana == diaDaSemana2;
	}

	private List<Aula> buscarAulasAberta(DateTime inicioVigencia, Empresa empresa, Usuario professor, TipoServico servico ) throws Exception {
		
		List<Aula> aulasAbertas = aulaDao.buscarAulasAbertasParaOPeriodo(inicioVigencia, empresa, professor, servico);
		
		return aulasAbertas;
	}
	
	private void encerrarAulaEmConflito(Aula aulasEmConflito, DateTime fimVigencia) {
		
		aulasEmConflito.setFimVigencia(fimVigencia);
		// por enquanto o encerramento esta sendo feito sem confirmação do usuário no próprio método que identifica o conflito
	}

}