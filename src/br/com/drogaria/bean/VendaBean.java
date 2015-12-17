package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.entity.Funcionario;
import br.com.drogaria.entity.Item;
import br.com.drogaria.entity.Produto;
import br.com.drogaria.entity.Venda;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {
	
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	
	private Venda vendaCadastro;
	private List<Item> listaItens;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
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
	
	public Venda getVendaCadastro() {
		if(vendaCadastro == null){
			vendaCadastro = new Venda();
			vendaCadastro.setValorTotal(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}
	
	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}
	
	public List<Item> getListaItens() {
		if(listaItens == null){
			listaItens = new ArrayList<>();
		}
		return listaItens;
	}
	
	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
	
	public void carregarProdutos(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
			
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar produtos: "+ ex.getMessage());
		}
	}
	
	public void adicionar(Produto produto){
		int posicaoEncontrada = -1;
		
		for (int i = 0; i < listaItens.size() && posicaoEncontrada < 0; i++) {
			if(listaItens.get(i).getProduto().equals(produto)){
				posicaoEncontrada = i;
			}
		}
		
		Item item = new Item();
		item.setProduto(produto);
		
		if(posicaoEncontrada < 0){
			item.setQuantidade(1);
			item.setValorParcial(produto.getPreco());
			listaItens.add(item);
		}else{
			Item itemTemp = listaItens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade()+1);
			item.setValorParcial(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			listaItens.set(posicaoEncontrada, item);
		}
		
		vendaCadastro.setValorTotal(vendaCadastro.getValorTotal().add(produto.getPreco()));
	}
	
	public void remover(Item item){
		
		for (int i = 0; i < listaItens.size(); i++) {
			if(listaItens.get(i).getProduto().equals(item.getProduto())){
				vendaCadastro.setValorTotal(vendaCadastro.getValorTotal().subtract(item.getValorParcial()));
				listaItens.remove(i);
				break;
			}
		}
	}
	
	public void carregarDadosVenda(){
		vendaCadastro.setHorario(new Date());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo());
		vendaCadastro.setFuncionario(funcionario);
	}
	
	public void salvar(){
		try {
			VendaDAO vendaDAO = new VendaDAO();
			Long codigoVenda = vendaDAO.salvar(vendaCadastro);
			Venda vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);
			
			for (Item item : listaItens) {
				item.setVenda(vendaFK);
				
				ItemDAO itemDAO = new ItemDAO();
				itemDAO.salvar(item);
			}
			vendaCadastro = new Venda();
			vendaCadastro.setValorTotal(new BigDecimal("0.00"));
			
			listaItens = new ArrayList<>();
			FacesUtil.addMsgInfo("Venda salva com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar salvar venda: "+ ex.getMessage());
		}
	}

}
