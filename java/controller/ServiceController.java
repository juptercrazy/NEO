package controller;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.joda.time.DateTime;

import entity.Empresa;
import negocio.AulaNegocio;

/**
 * Essa classe vai expor os nossos métodos para serem acessasdos via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/service")
public class ServiceController {

	
	/**
	 * Esse método busca as Aulas de uma Empresa
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getAulas/{inicioPeriodo}")
//	public String getAulas(){
	public String getAulas(@PathParam("inicioPeriodo") String inicioPeriodo){
 
		AulaNegocio negocio = new AulaNegocio();
		
		DateTime dataInicioPeriodo =  new DateTime(inicioPeriodo);
		System.out.println("Data inicio periodo :" + dataInicioPeriodo);
		try {
//			return negocio.buscarAulasPorDataInicio(new DateTime().now());
			return negocio.buscarAulasPorDataInicio(dataInicioPeriodo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
//		if(negocio != null)
//			return new AulaCalendario(aulas.get(0).getTipoServico().getDescricao(), aulas.get(0).getHoraInicioAula().toString(), aulas.get(0).getHoraFimAula().toString(), "","");

		return null;
	}
	
	@GET
	@Produces("text/plain")
	public String exibirtexto(){
 
		return " Texto livre";
	}

	private Empresa obterEmpresa(Long idEmpresa) {
		// TODO Auto-generated method stub
		return null;
	}
}
