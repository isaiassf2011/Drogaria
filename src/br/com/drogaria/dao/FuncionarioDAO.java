package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.entity.Funcionario;
import br.com.drogaria.util.HibernateUtil;

public class FuncionarioDAO {
	
	public void salvar(Funcionario funcionario) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
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
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Funcionario> funcionarios = null;
			
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			funcionarios = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		
		return funcionarios;

	}
	
	public Funcionario buscarPorCodigo(Long codigo) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Funcionario funcionario = null;
			
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			funcionario = (Funcionario) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		
		return funcionario;

	}
	
	public void excluir(Funcionario funcionario){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
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
		Funcionario funcionario = null;

		try {
			transacao = sessao.beginTransaction();
			funcionario = buscarPorCodigo(codigo);
			sessao.delete(funcionario);
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
	
	public void alterar(Funcionario funcionario) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(funcionario);
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
	
	public Funcionario autenticar(String cpf, String senha){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Funcionario funcionario = null;
			
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.autenticar");
			consulta.setString("cpf", cpf);
			consulta.setString("senha", senha);
			funcionario = (Funcionario) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		
		return funcionario;
		
	}

}
