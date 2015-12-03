package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.entity.Fabricante;

public class FabricanteDAOTest {

	@Test
	@Ignore
	public void salvar() {

		Fabricante f1 = new Fabricante();
		f1.setDescricao("Descricao A");

		Fabricante f2 = new Fabricante();
		f2.setDescricao("Descricao B");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(f1);
		fabricanteDAO.salvar(f2);

	}

	@Test
	@Ignore
	public void lista() {

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar();

		for (Fabricante fabricante : fabricantes) {
			System.out.println(fabricante);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(1L);

		System.out.println(fabricante);

	}

	@Test
	@Ignore
	public void excluir() {

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(1L);

		if (fabricante != null) {
			fabricanteDAO.excluir(fabricante);
		}

	}

	@Test
	@Ignore
	public void excluirPorCodigo() {

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.excluirPorCodigo(2L);

	}

	@Test
	public void alterar() {

		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(3L);
		fabricante.setDescricao("Descricao X");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.alterar(fabricante);

	}

}
