package br.com.gvl.main;

import br.com.gvl.model.Cliente;
import br.com.gvl.model.Fornecedor;
import br.com.gvl.model.Produto;
import br.com.gvl.service.ProdutoService;
import br.com.gvl.service.VendaService;

public class ExecutarTestes {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTES DAS REGRAS DE NEGÓCIO (SISTEMA GVL) ===\n");

        // Instanciando os Serviços
        ProdutoService produtoService = new ProdutoService();
        VendaService vendaService = new VendaService();

        // TESTE 1: Validar Produto SEM Fornecedor (Deve falhar)
        System.out.println("--- Teste 1: Cadastro de Produto sem Fornecedor ---");
        try {
            Produto prodSemFornecedor = new Produto(1, "Filtro de Óleo", 45.0, 10, null);
            produtoService.validarCadastroProduto(prodSemFornecedor);
            System.out.println("ERRO: O sistema permitiu cadastrar produto sem fornecedor!");
        } catch (IllegalArgumentException e) {
            System.out.println("SUCESSO (Exceção capturada): " + e.getMessage());
        }

        // TESTE 2: Validar Produto COM Fornecedor (Deve passar)
        System.out.println("\n--- Teste 2: Cadastro de Produto com Fornecedor Válido ---");
        Fornecedor fornecedor01 = new Fornecedor(1, "Bosch Auto Peças", "12.345.678/0001-90", "63999998888", "contato@bosch.com");
        Produto prodComFornecedor = new Produto(2, "Disco de Freio", 180.0, 5, fornecedor01);
        
        try {
            if (produtoService.validarCadastroProduto(prodComFornecedor)) {
                System.out.println("SUCESSO: Produto '" + prodComFornecedor.getNomeProduto() + "' cadastrado com sucesso para o fornecedor '" + prodComFornecedor.getFornecedor().getNomeEmpresa() + "'.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        // TESTE 3: Realizar Venda de Produto Sem Estoque (Deve falhar)
        System.out.println("\n--- Teste 3: Venda de Produto Fora de Estoque ---");
        Cliente cliente01 = new Cliente(1, "Sérgio Reis", "123.456.789-00", "63988887777", "sergio@email.com");
        Produto prodSemEstoque = new Produto(3, "Óleo 5W30", 60.0, 0, fornecedor01);

        try {
            vendaService.realizarVenda(cliente01, prodSemEstoque, 1);
            System.out.println("ERRO: O sistema permitiu a venda de um produto sem estoque!");
        } catch (IllegalArgumentException e) {
            System.out.println("SUCESSO (Exceção capturada): " + e.getMessage());
        }

        // TESTE 4: Realizar Venda Válida (Deve deduzir do estoque)
        System.out.println("\n--- Teste 4: Realizar Venda Válida ---");
        System.out.println("Estoque inicial do produto (" + prodComFornecedor.getNomeProduto() + "): " + prodComFornecedor.getQuantidadeProduto());
        try {
            boolean vendaOk = vendaService.realizarVenda(cliente01, prodComFornecedor, 2);
            if (vendaOk) {
                System.out.println("SUCESSO: Venda efetuada!");
                System.out.println("Novo estoque restante: " + prodComFornecedor.getQuantidadeProduto());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        System.out.println("\n=== TODOS OS TESTES EXECUTADOS COM SUCESSO! ===");
    }
}