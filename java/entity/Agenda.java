package entity;

import java.io.Serializable;

import javax.validation.constraints.Future;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class Agenda implements Serializable {
	
	private static final long serialVersionUID = 1L;


	public Agenda() {
	}
	
	private TipoServico tipoServico;
	private Empresa empresa;
	
	private Usuario professor;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	
	@Future
	private DateTime inicioVigencia;
	private DateTime fimVigencia;
	

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	public DateTime getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(DateTime inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public DateTime getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(DateTime fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

}