package bean;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Future;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;

import entity.Agenda;
import entity.Aula;
import entity.Empresa;
import entity.ModeloAula;
import negocio.AgendaNegocio;
import negocio.AulaNegocio;
import util.ContextoUtil;

@ManagedBean
public class AgendaBean {

	private Agenda agenda = new Agenda();
	
	@Future
	private Date inicioVigencia;
	
	private Date fimVigencia;
	
	private Date diaInicioConsulta;
	
	private ModeloAula modeloAula = new ModeloAula();
	
	private List<Integer> diasDaSemana;
	
	private Boolean mostrarDias;
	
	private Boolean  diaDaSemanaSelecionado = Boolean.FALSE;
	
	private List<Aula> aulasEmConflito = new ArrayList<Aula>();

	private List<Aula> aulasAgenda = new ArrayList<Aula>();
	
	private Empresa empresa = new Empresa();
	
	private String fakeAulas = new String();
	
//	private String aulasdaSemana = new String();
	
	public AgendaBean() {
		mostrarDias = Boolean.TRUE;
	}
	
	public String criarAgenda() {
		AgendaNegocio agendaRN = new AgendaNegocio();
		try {
			Empresa empresa = obterEmpresaDoUsuarioAtivo();
			
			this.getAgenda().setEmpresa(empresa);
//			empresa.addAgenda(agenda);		
			agendaRN.salvarAgenda(this.getAgenda(), this.getModeloAula(), this.getDiasDaSemana());
			
			return "/proprietario/cadastro/agenda/template_agenda";

		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	public String getFakeAulas() {
		
		AulaNegocio negocio  = new AulaNegocio();
		
		Property dia = new DateTime().dayOfWeek();
		
		int diaInicioSemana = dia.get() - 1;
		int diaFimSemana = 6 - dia.get();
		
		
//		
//		new DateTime().minusDays(diaInicioSemana);
		
		try {
			return negocio.buscarAulasPorPeriodo(new DateTime().minusDays(diaInicioSemana), new DateTime().plusMonths(4), obterEmpresaDoUsuarioAtivo());
//			return negocio.buscarAulasParaOPeriodo(new DateTime(inicioVigencia.getTime()), new DateTime(fimVigencia.getTime()), empresa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
//	public String getAulasdaSemana() {
//		
//		AulaNegocio negocio  = new AulaNegocio();
//		
//		Property dia = new DateTime(diaInicioConsulta).dayOfWeek();
//		
//		int diaInicioSemana = dia.get() - 1;
//		int diaFimSemana = 6 - dia.get();
//		
//		
////		
////		new DateTime().minusDays(diaInicioSemana);
//		
//		try {
//			return negocio.buscarAulasPorPeriodo(new DateTime().minusDays(diaInicioSemana), new DateTime().plusDays(diaFimSemana), obterEmpresaDoUsuarioAtivo());
////			return negocio.buscarAulasParaOPeriodo(new DateTime(inicioVigencia.getTime()), new DateTime(fimVigencia.getTime()), empresa);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return "";
//	}
	
//	public String getFakeAulas() {
//		return "{ title          : 'All Day Event', start : new Date(y, m, 1), backgroundColor: '#f56954', borderColor    : '#f56954'}";
//	}
	
	public void diaDaSemanaSelecionado() {
		
		mostrarDias = !mostrarDias;
	}
	
	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public ModeloAula getModeloAula() {
		return modeloAula;
	}

	public void setModeloAula(ModeloAula modeloAula) {
		this.modeloAula = modeloAula;
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

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public List<Integer> getDiasDaSemana() {
		return diasDaSemana;
	}

	public void setDiasDaSemana(List<Integer> diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}

	public List<Aula> getAulasEmConflito() {
		return aulasEmConflito;
	}

	public void setAulasEmConflito(List<Aula> aulasEmConflito) {
		this.aulasEmConflito = aulasEmConflito;
	}

	public List<Aula> getAulasAgenda() {
		return aulasAgenda;
	}

	public void setAulasAgenda(List<Aula> aulasAgenda) {
		this.aulasAgenda = aulasAgenda;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getDiaInicioConsulta() {
		return diaInicioConsulta;
	}

	public void setDiaInicioConsulta(Date diaInicioConsulta) {
		this.diaInicioConsulta = diaInicioConsulta;
	}

	public Boolean getMostrarDias() {
		return mostrarDias;
	}

	public void setMostrarDias(Boolean mostrarDias) {
		this.mostrarDias = mostrarDias;
	}

	public Boolean getDiaDaSemanaSelecionado() {
		
		mostrarDias = !mostrarDias == Boolean.TRUE ? Boolean.FALSE : Boolean.TRUE;
		System.out.println(mostrarDias);
		
		return diaDaSemanaSelecionado ;
	}

	public void setDiaDaSemanaSelecionado(Boolean diaDaSemanaSelecionado) {
		this.diaDaSemanaSelecionado = diaDaSemanaSelecionado;
	}

	public void setFakeAulas(String fakeAulas) {
		this.fakeAulas = fakeAulas;
	}

}
