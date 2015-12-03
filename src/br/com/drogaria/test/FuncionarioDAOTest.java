package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.entity.Funcionario;

public class FuncionarioDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf("10012531911");
		funcionario.setNome("Isaias");
		funcionario.setSenha("123");
		funcionario.setFuncao("Programador");
		
		Funcionario funcionario2 = new Funcionario();
		funcionario2.setCpf("10012531921");
		funcionario2.setNome("João");
		funcionario2.setSenha("12345");
		funcionario2.setFuncao("Programador 1");
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
		funcionarioDAO.salvar(funcionario2);
		
	}
	
	@Test
	@Ignore
	public void lista() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> funcionarios = funcionarioDAO.listar();

		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(1L);

		System.out.println(funcionario);

	}

	@Test
	@Ignore
	public void excluir() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(1L);

		if (funcionario != null) {
			funcionarioDAO.excluir(funcionario);
		}

	}

	@Test
	@Ignore
	public void excluirPorCodigo() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.excluirPorCodigo(2L);

	}

	@Test
	public void alterar() {

		Funcionario funcionario = new Funcionario();
		funcionario.setCodigo(4L);
		funcionario.setCpf("12345678910");
		funcionario.setNome("Maria");
		funcionario.setSenha("1234");
		funcionario.setFuncao("Analista");

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.alterar(funcionario);

	}

}
