package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.entity.Funcionario;
import br.com.drogaria.entity.Item;
import br.com.drogaria.entity.Produto;
import br.com.drogaria.entity.Venda;

public class ItemDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Item item = new Item();
		item.setQuantidade(10);
		item.setValorParcial(new BigDecimal(250));
		
		Venda venda = new VendaDAO().buscarPorCodigo(4L);
		item.setVenda(venda);
		
		Produto produto = new ProdutoDAO().buscarPorCodigo(3L);
		item.setProduto(produto);
		
		Item item2 = new Item();
		item2.setQuantidade(15);
		item2.setValorParcial(new BigDecimal(356));
		
		Venda venda2 = new VendaDAO().buscarPorCodigo(4L);
		item2.setVenda(venda2);
		
		Produto produto2 = new ProdutoDAO().buscarPorCodigo(3L);
		item2.setProduto(produto2);
		
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.salvar(item);
		itemDAO.salvar(item2);
		
	}
	
	@Test
	public void lista() {

		ItemDAO itemDAO = new ItemDAO();
		List<Item> items = itemDAO.listar();

		for (Item item : items) {
			System.out.println(item);
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


}
