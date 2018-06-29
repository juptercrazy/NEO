package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
public class MenuBean {

	private String linkAbsoluto;
	
	private String novoModeloAula;
	
	private String novoServico;
	
	private String home;
	
	public String getLinkAbsoluto() {
		return "http://localhost:8080/NEO";
	}

	public String getNovoModeloAula() {
		return new StringBuilder().append(getLinkAbsoluto()).append("/proprietario/cadastro/modeloaula/template_cadastrar_modelo_de_aula.xhtml").toString();
	}
	
	public String getNovoServico() {
		return new StringBuilder().append(getLinkAbsoluto()).append("/proprietario/cadastro/servico/template_cadastrar_servico.xhtml").toString();
	}
	
	public String getHome() {
		return new StringBuilder().append(getLinkAbsoluto()).append("/proprietario/template_home.xhtml").toString();
	}
}