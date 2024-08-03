/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadatrobd.model;

import cadastrobd.model.Util.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class PessoaJuridicaDAO {
    
    public PessoaJuridica getPessoa(int id) throws SQLException {
        String sql = "SELECT Pessoa.idPessoa, Pessoa.nome, Pessoa.logradouro, Pessoa.cidade, " +
                "Pessoa.estado, Pessoa.telefone, Pessoa.email, PessoaJuridica.cnpj " +
                "FROM Pessoa " +
                "JOIN PessoaJuridica ON Pessoa.idPessoa = PessoaJuridica.idPessoa " +
                "WHERE Pessoa.idPessoa = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = ConectorBD.getSelect(stmt)) {
                if (rs.next()) {
                    PessoaJuridica pessoa = new PessoaJuridica();
                    pessoa.setId(rs.getInt("idPessoa"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setLogradouro(rs.getString("logradouro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setEstado(rs.getString("estado"));
                    pessoa.setTelefone(rs.getString("telefone"));
                    pessoa.setEmail(rs.getString("email"));
                    pessoa.setCnpj(rs.getString("cnpj"));
                    return pessoa;
                }
            }
        }
        return null;
    }

    public List<PessoaJuridica> getPessoas() throws SQLException {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT Pessoa.idPessoa, Pessoa.nome, Pessoa.logradouro, Pessoa.cidade, " +
                "Pessoa.estado, Pessoa.telefone, Pessoa.email, PessoaJuridica.cnpj " +
                "FROM Pessoa " +
                "JOIN PessoaJuridica ON Pessoa.idPessoa = PessoaJuridica.idPessoa";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(sql);
             ResultSet rs = ConectorBD.getSelect(stmt)) {
            while (rs.next()) {
                PessoaJuridica pessoa = new PessoaJuridica();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCnpj(rs.getString("cnpj"));
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoa) throws SQLException {
        String sqlInsertPessoa = "INSERT INTO Pessoa (idPessoa, nome, logradouro, cidade,"
                + " estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlInsertPessoaJuridica = "INSERT INTO PessoaJuridica (idPessoa, cnpj)"
                + " VALUES (?, ?)";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtInsertPessoa = conn.prepareStatement(sqlInsertPessoa);
             PreparedStatement stmtInsertPessoaJuridica = conn.prepareStatement(sqlInsertPessoaJuridica)) {

            // Inserir na tabela Pessoa
            stmtInsertPessoa.setInt(1, pessoa.getId());
            stmtInsertPessoa.setString(2, pessoa.getNome());
            stmtInsertPessoa.setString(3, pessoa.getLogradouro());
            stmtInsertPessoa.setString(4, pessoa.getCidade());
            stmtInsertPessoa.setString(5, pessoa.getEstado());
            stmtInsertPessoa.setString(6, pessoa.getTelefone());
            stmtInsertPessoa.setString(7, pessoa.getEmail());
            stmtInsertPessoa.executeUpdate();

            // Inserir na tabela PessoaJuridica
            stmtInsertPessoaJuridica.setInt(1, pessoa.getId());
            stmtInsertPessoaJuridica.setString(2, pessoa.getCnpj());
            stmtInsertPessoaJuridica.executeUpdate();
        }
    }

    public void alterar(PessoaJuridica pessoa, String novoNome, String novoLogradouro, String novaCidade,
        String novoEstado,String novoTelefone, String novoEmail, String novoCnpj) throws SQLException {
        String sql = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, "
                + "estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        String sqlJuridica = "UPDATE PessoaJuridica SET cnpj = ? WHERE idPessoa = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmtJuridica = conn.prepareStatement(sqlJuridica)) {

            // Atualizar dados na tabela Pessoa
            stmt.setString(1, novoNome);
            stmt.setString(2, novoLogradouro);
            stmt.setString(3, novaCidade);
            stmt.setString(4, novoEstado);
            stmt.setString(5, novoTelefone);
            stmt.setString(6, novoEmail);
            stmt.setInt(7, pessoa.getId());
            stmt.executeUpdate();

            // Atualizar CNPJ na tabela PessoaJuridica
            stmtJuridica.setString(1, novoCnpj);
            stmtJuridica.setInt(2, pessoa.getId());
            stmtJuridica.executeUpdate();
        }
    }
 
    public void excluir(int id) throws SQLException {
    String sql = "DELETE FROM PessoaJuridica WHERE idPessoa = ?";
    String sqlPessoa = "DELETE FROM Pessoa WHERE idPessoa = ?";
    
    try (Connection conn = ConectorBD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {
        // Excluir da tabela PessoaJuridica
        stmt.setInt(1, id);
        stmt.executeUpdate();

        // Excluir da tabela Pessoa
        stmtPessoa.setInt(1, id);
        stmtPessoa.executeUpdate();

        System.out.println("Pessoa juridica excluida com ID: " + id);
        }
    }
}