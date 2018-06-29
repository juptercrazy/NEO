package bean;

import javax.faces.bean.ManagedBean;

import entity.TipoServico;
import negocio.TipoServicoNegocio;
import util.ContextoUtil;

@ManagedBean
public class ProprietarioBean {

	private TipoServico servico = new TipoServico();

	public String criarTipoServico() {
		TipoServicoNegocio negocio = new TipoServicoNegocio();

		try {

			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			servico.setEmpresa(contextoBean.obterEmpresaPrincipalDoUsuario());
			negocio.salvarServico(servico);

			return "/proprietario/template_home.xhtml";

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public TipoServico getServico() {
		return servico;
	}

	public void setServico(TipoServico servico) {
		this.servico = servico;
	}
}
