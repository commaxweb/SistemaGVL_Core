package br.com.gvl.dao;

import br.com.gvl.model.Fornecedor;
import br.com.gvl.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, preco, quantidade, id_fornecedor) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getPrecoProduto());
            stmt.setInt(3, produto.getQuantidadeProduto());
            stmt.setInt(4, produto.getFornecedor().getId());
            
            stmt.executeUpdate();
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.preco, p.quantidade, f.id as fornecedor_id, f.nome_empresa " +
                     "FROM produto p INNER JOIN fornecedor f ON p.id_fornecedor = f.id";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("fornecedor_id"));
                f.setNomeEmpresa(rs.getString("nome_empresa"));

                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNomeProduto(rs.getString("nome"));
                p.setPrecoProduto(rs.getDouble("preco")); // Usando o setter correto
                p.setQuantidadeProduto(rs.getInt("quantidade"));
                p.setFornecedor(f);

                produtos.add(p);
            }
        }
        return produtos;
    }
}