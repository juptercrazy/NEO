package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import entity.ModeloAula;
import negocio.ModeloAulaNegocio;

@ManagedBean
public class ModeloAulaBean {

	private ModeloAula modeloAula = new ModeloAula();
	
	@ManagedProperty(value = "#{listBean}")
	private ListBean bean; 
	
	public String criarModeloAula() {
		ModeloAulaNegocio modleoAulaRN = new ModeloAulaNegocio();
		try {
			
			modleoAulaRN.criarModeloAula(this.getModeloAula());

			limparList();

			return "/proprietario/template_home.xhtml";

		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	/**
	 * 
	 */
	private void limparList() {
		getBean().limparModeloAula();
	}

	public ModeloAula getModeloAula() {
		return modeloAula;
	}

	public void setModeloAula(ModeloAula modeloAula) {
		this.modeloAula = modeloAula;
	}

	public ListBean getBean() {
		return bean;
	}

	public void setBean(ListBean bean) {
		this.bean = bean;
	}
	
}
