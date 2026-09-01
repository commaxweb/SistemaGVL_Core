package br.com.gvl.service;

import br.com.gvl.model.Produto;

public class ProdutoServiceTest {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTE UNITÁRIO: ProdutoServiceTest ===");
        
        ProdutoServiceTest teste = new ProdutoServiceTest();
        
        try {
            teste.deveLancarExcecaoQuandoProdutoSemFornecedor();
            System.out.println("\n=== RESULTADO: TODOS OS TESTES PASSARAM COM SUCESSO! ===");
        } catch (Exception e) {
            System.err.println("\n=== " + e.getMessage() + " ===");
        }
    }

    public void deveLancarExcecaoQuandoProdutoSemFornecedor() {
        ProdutoService service = new ProdutoService();
        Produto produto = new Produto();
        
        produto.setNomeProduto("Peça de Teste");
        produto.setPrecoProduto(50.00);
        produto.setQuantidadeProduto(10);
        produto.setFornecedor(null); // Fornecedor nulo deve disparar exceção

        boolean lancouExcecao = false;
        
        try {
            service.validarCadastroProduto(produto);
        } catch (IllegalArgumentException e) {
            lancouExcecao = true;
            System.out.println("[SUCESSO] Exceção capturada corretamente: " + e.getMessage());
        }

        if (!lancouExcecao) {
            throw new RuntimeException("FALHA NO TESTE: Deveria ter barrado o produto sem fornecedor!");
        }
    }
}