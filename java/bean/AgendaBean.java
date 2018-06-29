package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Future;

import entity.Agenda;
import entity.Aula;
import entity.Empresa;
import entity.ModeloAula;
import negocio.AgendaNegocio;
import util.ContextoUtil;

@ManagedBean
public class AgendaBean {

	private Agenda agenda = new Agenda();
	
	@Future
	private Date inicioVigencia;
	
	private ModeloAula modeloAula = new ModeloAula();
	
	private List<Integer> diasDaSemana;
	
	private List<Aula> aulasEmConflito = new ArrayList<Aula>();
	
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

}
