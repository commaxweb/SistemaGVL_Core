package br.com.gvl.service;

import br.com.gvl.model.Cliente;
import br.com.gvl.model.Produto;

public class VendaService {

    public boolean realizarVenda(Cliente cliente, Produto produto, int quantidadeDesejada) throws IllegalArgumentException {
        // Validação de dados de entrada
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente inválido para a venda.");
        }
        if (produto == null) {
            throw new IllegalArgumentException("Produto não selecionado.");
        }
        
        // Regra de Negócio 2: Impedir venda de produto sem estoque ou com quantidade insuficiente
        if (produto.getQuantidadeProduto() <= 0) {
            throw new IllegalArgumentException("Venda recusada: Produto fora de estoque!");
        }
        
        if (quantidadeDesejada > produto.getQuantidadeProduto()) {
            throw new IllegalArgumentException("Venda recusada: Quantidade solicitada maior que o estoque disponível (" + produto.getQuantidadeProduto() + ").");
        }

        // Se passar nas regras, deduz do estoque
        int novoEstoque = produto.getQuantidadeProduto() - quantidadeDesejada;
        produto.setQuantidadeProduto(novoEstoque);
        
        return true;
    }
}