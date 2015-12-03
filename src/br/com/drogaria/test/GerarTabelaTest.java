package br.com.drogaria.test;

import org.junit.Test;

import br.com.drogaria.util.HibernateUtil;

public class GerarTabelaTest {

	@Test
	public void gerar() {
		HibernateUtil.getFabricaDeSessoes().openSession();
		HibernateUtil.getFabricaDeSessoes().close();
	}

}
