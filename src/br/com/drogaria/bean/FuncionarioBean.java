package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.entity.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionario;

	public Funcionario getFuncionario() {
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.salvar(funcionario);
			limpar();
			FacesUtil.addMsgInfo("Funcionario salvo com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar incluir funcionario: "+ ex.getMessage());
		}
	}

	public void limpar() {
		funcionario = new Funcionario();
	}

}
