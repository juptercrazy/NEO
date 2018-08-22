package bean;

import javax.faces.bean.ManagedBean;

import entity.Empresa;
import entity.PropriedadesAluno;
import negocio.AlunoNegocio;
import util.ContextoUtil;

@ManagedBean
public class AlunoBean {

	private Integer numAlunos;
	
	

	public Integer getNumAlunos() {
		AlunoNegocio negocio = new AlunoNegocio();
		
		numAlunos = negocio.obterNumAlunos(obterEmpresaDoUsuarioAtivo());
		
		return numAlunos;
	}

	public void setNumAlunos(Integer numAlunos) {
		this.numAlunos = numAlunos;
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
