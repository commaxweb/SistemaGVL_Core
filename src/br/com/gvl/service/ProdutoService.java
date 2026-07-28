package br.com.gvl.service;

import br.com.gvl.model.Produto;

public class ProdutoService {

    public boolean validarCadastroProduto(Produto produto) throws IllegalArgumentException {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        // Regra de Negócio 1: O produto deve estar vinculado a um fornecedor existente
        if (produto.getFornecedor() == null || produto.getFornecedor().getNomeEmpresa() == null || produto.getFornecedor().getNomeEmpresa().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O produto deve obrigatoriamente estar vinculado a um fornecedor válido.");
        }

        if (produto.getPrecoProduto() <= 0) {
            throw new IllegalArgumentException("Erro: O preço do produto deve ser maior que zero.");
        }

        return true;
    }
}