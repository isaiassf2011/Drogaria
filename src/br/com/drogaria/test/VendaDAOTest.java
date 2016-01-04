package br.com.drogaria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.entity.Funcionario;
import br.com.drogaria.entity.Venda;
import br.com.drogaria.filter.VendaFilter;

public class VendaDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Venda venda = new Venda();
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(500));
		
		Funcionario funcionario = new FuncionarioDAO().buscarPorCodigo(4L);
		venda.setFuncionario(funcionario);
		
		Venda venda2 = new Venda();
		venda2.setHorario(new Date());
		venda2.setValorTotal(new BigDecimal(300));
		
		Funcionario funcionario2 = new FuncionarioDAO().buscarPorCodigo(5L);
		venda2.setFuncionario(funcionario2);
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
		vendaDAO.salvar(venda2);
		
	}
	
	@Test
	@Ignore
	public void lista() {

		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas = vendaDAO.listar();

		for (Venda venda : vendas) {
			System.out.println(venda);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(1L);

		System.out.println(venda);

	}

	@Test
	@Ignore
	public void excluir() {

		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(1L);

		if (venda != null) {
			vendaDAO.excluir(venda);
		}

	}

	@Test
	@Ignore
	public void excluirPorCodigo() {

		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.excluirPorCodigo(2L);

	}

	@Test
	@Ignore
	public void alterar() {

		Venda venda = new Venda();
		venda.setCodigo(2L);
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(400));
		
		Funcionario funcionario = new FuncionarioDAO().buscarPorCodigo(5L);
		venda.setFuncionario(funcionario);
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.alterar(venda);

	}
	
	@Test
	public void buscar() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		VendaFilter filtro = new VendaFilter();
		//filtro.setDataInicial(format.parse("02/09/2015"));
		//filtro.setDataFinal(format.parse("25/12/2015"));
		
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas = vendaDAO.buscar(filtro);
		
		for (Venda venda : vendas) {
			System.out.println(venda);
		}
		
	}

}
