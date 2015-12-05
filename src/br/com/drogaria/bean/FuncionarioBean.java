package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.entity.Fabricante;
import br.com.drogaria.entity.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionario;
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;
	private String acao;
	private Long codigo;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(
			List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	
	public void editar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.alterar(funcionario);
			
			FacesUtil.addMsgInfo("Funcionario alterado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar alterar funcionario: "+ ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);
			
			FacesUtil.addMsgInfo("Funcionario excluido com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar excluir funcionario: "+ ex.getMessage());
		}
	}
	
	public void carregarPesquisa(){
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listaFuncionarios = funcionarioDAO.listar();
			
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar funcionarios: "+ ex.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {
			if(codigo != null){
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionario = funcionarioDAO.buscarPorCodigo(codigo);
			}else{
				limpar();
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados do funcionario: "+ ex.getMessage());
		}
	}

	public void limpar() {
		funcionario = new Funcionario();
	}

}
