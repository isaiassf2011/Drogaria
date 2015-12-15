package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.entity.Item;
import br.com.drogaria.entity.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {
	
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	
	private List<Item> listaItens;
	
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
	
	public List<Item> getListaItens() {
		if(listaItens == null){
			listaItens = new ArrayList<>();
		}
		return listaItens;
	}
	
	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
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
	}
	
	public void remover(Item item){
		
		for (int i = 0; i < listaItens.size(); i++) {
			if(listaItens.get(i).getProduto().equals(item.getProduto())){
				listaItens.remove(i);
				break;
			}
		}
	}

}
