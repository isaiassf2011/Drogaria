package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.entity.Fabricante;
import br.com.drogaria.entity.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Produto produto = new Produto();
		produto.setDescricao("PC");
		produto.setPreco(new BigDecimal(800));
		produto.setQuantidade(8);
		
		Fabricante fabricante = new FabricanteDAO().buscarPorCodigo(3L);
		produto.setFabricante(fabricante);
		
		Produto produto2 = new Produto();
		produto2.setDescricao("Celular");
		produto2.setPreco(new BigDecimal(500));
		produto2.setQuantidade(25);
		
		Fabricante fabricante2 = new FabricanteDAO().buscarPorCodigo(5L);
		produto2.setFabricante(fabricante2);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		produtoDAO.salvar(produto2);
		
	}
	
	@Test
	@Ignore
	public void lista() {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listar();

		for (Produto produto : produtos) {
			System.out.println(produto);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);

		System.out.println(produto);

	}

	@Test
	@Ignore
	public void excluir() {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);

		if (produto != null) {
			produtoDAO.excluir(produto);
		}

	}

	@Test
	@Ignore
	public void excluirPorCodigo() {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.excluirPorCodigo(2L);

	}

	@Test
	public void alterar() {

		Produto produto = new Produto();
		produto.setCodigo(2L);
		produto.setDescricao("TV - LED");
		produto.setPreco(new BigDecimal(480));
		produto.setQuantidade(15);
		
		Fabricante fabricante = new FabricanteDAO().buscarPorCodigo(4L);
		produto.setFabricante(fabricante);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.alterar(produto);

	}

}
