package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.entity.Venda;
import br.com.drogaria.util.HibernateUtil;

public class VendaDAO {
	
	public Long salvar(Venda venda) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		Long codigo = null;
		
		try {
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(venda);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
		return codigo;

	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> listar() {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Venda> vendas = null;
			
		try {
			Query consulta = sessao.getNamedQuery("Venda.listar");
			vendas = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		
		return vendas;

	}
	
	public Venda buscarPorCodigo(Long codigo) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Venda venda = null;
			
		try {
			Query consulta = sessao.getNamedQuery("Venda.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			venda = (Venda) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		
		return venda;

	}
	
	public void excluir(Venda venda){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(venda);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
		
	}
	
	public void excluirPorCodigo(Long codigo){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		Venda venda = null;

		try {
			transacao = sessao.beginTransaction();
			venda = buscarPorCodigo(codigo);
			sessao.delete(venda);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
		
	}
	
	public void alterar(Venda venda) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(venda);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}

}
