package negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.json.JSONObject;

import bean.ContextoBean;
import dao.DaoFactory;
import entity.Aula;
import entity.Empresa;
import interfaceDao.AulaDao;
import util.ContextoUtil;

public class AulaNegocio {

	private AulaDao aulaDao;

	public AulaNegocio() {
		aulaDao = DaoFactory.criarAulaDao();
	}

	public void criarAula(Aula aula) throws Exception {

		aulaDao.criarAula(aula);
	}

	public String buscarAulasPorDataInicio(DateTime inicioPeriodo) throws Exception {
		
		DateTime inicio = new DateTime(inicioPeriodo);
		
		DateTime fim = inicio.plusWeeks(1);
		
		
		
		return buscarAulasPorPeriodo(inicio, fim, obterEmpresaDoUsuarioAtivo());
	}

	/*
	 * Este processo determina como
	 */
	// public List<Aula> buscarAulasParaOPeriodo(DateTime inicioPeriodo, DateTime
	// fimPeriodo, Empresa empresa) throws Exception {
	public String buscarAulasPorPeriodo(DateTime inicioPeriodo, DateTime fimPeriodo, Empresa empresa) throws Exception {

		List<Aula> aulasDoPeriodo = aulaDao.buscarAulasParaOPeriodo(inicioPeriodo, fimPeriodo, empresa);

		return separarAulasPorHorario(inicioPeriodo, fimPeriodo, aulasDoPeriodo);
	}

	protected String separarAulasPorHorario(DateTime inicioPeriodo, DateTime fimPeriodo, List<Aula> aulasDoPeriodo) {

		StringBuilder aulasFormatoJQUERY = new StringBuilder();

		List<Aula> aulasSeparadasPorDataEHorario = new ArrayList<Aula>();

		LocalDate diaInicial = inicioPeriodo.toLocalDate();

		Hashtable<Integer, List<Aula>> semana = new Hashtable<Integer, List<Aula>>();

		int inicioSemana = 2;
		int sextaFeira = 6;

		for (int diaDaSemana = 2; diaDaSemana <= sextaFeira; diaDaSemana++) {
			List<Aula> aulasDaSemana = new ArrayList<Aula>();
			for (Aula aula : aulasDoPeriodo) {
				if (aula.getDiaDaSemana() == diaDaSemana) {
					aulasDaSemana.add(aula);
				}
			}
			semana.put(diaDaSemana, aulasDaSemana);
		}

		List<Aula> aulasDoDia = new ArrayList<Aula>();
		for (LocalDate diaCorrente = diaInicial; !diaCorrente
				.isAfter(fimPeriodo.toLocalDate()); diaCorrente = diaCorrente.plusDays(1)) {

			System.out.println(diaCorrente.toString());
			org.joda.time.LocalDate.Property dia = diaCorrente.dayOfWeek();
			System.out.println("Dia da Semana " + dia.get() + " " + dia.getAsShortText());
			aulasDoDia = semana.get(dia.get());
			if (aulasDoDia != null)
				for (Aula aula : aulasDoDia) {
					if (aula.getFimVigencia() == null || aula.getFimVigencia().toLocalDate().isAfter(diaCorrente)) {
						aulasFormatoJQUERY.append("{");
						aulasFormatoJQUERY.append("title:").append("'").append(aula.getTipoServico().getDescricao())
								.append("',");
						aulasFormatoJQUERY.append("start:").append("'")
								.append(criarDataComHora(diaCorrente, aula.getHoraInicioAula())).append("',");
						aulasFormatoJQUERY.append("end:").append("'")
								.append(criarDataComHora(diaCorrente, aula.getHoraFimAula())).append("',");
						aulasFormatoJQUERY.append("backgroundColor:").append("'").append("#f56954").append("'");
						aulasFormatoJQUERY.append("},");

						// JSONObject aulajs = new JSONObject();
						//
						// aulajs.put("title",aula.getTipoServico().toString() );
						// aulajs.put("start", criarDataComHora(diaCorrente, aula.getHoraInicioAula()));
						// aulajs.put("end", criarDataComHora(diaCorrente, aula.getHoraFimAula()));
						// aulajs.put("backgroundColor", "#f56954");
						// aulajs.put("borderColor" , "#f56954");

						// aulasSeparadasPorDataEHorario.add(aula);
					}
				}
		}

		aulasFormatoJQUERY.toString().substring(0, aulasFormatoJQUERY.length());

		System.out.println(aulasFormatoJQUERY.toString());

		return aulasFormatoJQUERY.toString();
		// return aulasSeparadasPorDataEHorario;
	}

	private String criarDataComHora(LocalDate diaCorrente, LocalTime horaInicioAula) {
		// TODO Auto-generated method stub
		return new DateTime(diaCorrente.getYear(), diaCorrente.getMonthOfYear(), diaCorrente.getDayOfMonth(),
				horaInicioAula.getHourOfDay(), horaInicioAula.getMinuteOfHour()).toString();
	}

	private Empresa obterEmpresaDoUsuarioAtivo() {

		try {

			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			return contextoBean.obterEmpresaPrincipalDoUsuario();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
