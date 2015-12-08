package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.entity.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	
	private Produto produtoCadastro;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private String acao;
	private Long codigo;

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}
	
	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}
	
	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}
	
	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
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
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);
			limpar();
			FacesUtil.addMsgInfo("Produto salvo com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar salvar produto: "
					+ ex.getMessage());
		}
	}
	
	public void excluir() {

		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoCadastro);
			
			FacesUtil.addMsgInfo("Produto excluido com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar excluir produto: "
					+ ex.getMessage());
		}
	}
	
	public void editar() {

		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.alterar(produtoCadastro);
			
			FacesUtil.addMsgInfo("Produto alterado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar alterar produto: "
					+ ex.getMessage());
		}
	}
	
	public void carregar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
			
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar produtos: "+ ex.getMessage());
		}
	}

	public void limpar() {
		produtoCadastro = new Produto();
	}
	
	public void carregarCadastro(){
		try {
			if(codigo != null){
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoCadastro = produtoDAO.buscarPorCodigo(codigo);
			}else{
				limpar();
			}
			
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados do produto: "+ ex.getMessage());
		}
	}

}
