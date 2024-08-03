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
public class PessoaFisicaDAO {
    
    public PessoaFisica getPessoa(int id) throws SQLException {
        String sql = "SELECT Pessoa.idPessoa, Pessoa.nome, Pessoa.logradouro, Pessoa.cidade, " +
                "Pessoa.estado, Pessoa.telefone, Pessoa.email, PessoaFisica.cpf " +
                "FROM Pessoa " +
                "JOIN PessoaFisica ON Pessoa.idPessoa = PessoaFisica.idPessoa " +
                "WHERE Pessoa.idPessoa = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = ConectorBD.getSelect(stmt)) {
                if (rs.next()) {
                    PessoaFisica pessoa = new PessoaFisica();
                    pessoa.setId(rs.getInt("idPessoa"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setLogradouro(rs.getString("logradouro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setEstado(rs.getString("estado"));
                    pessoa.setTelefone(rs.getString("telefone"));
                    pessoa.setEmail(rs.getString("email"));
                    pessoa.setCpf(rs.getString("cpf"));
                    return pessoa;
                }
            }
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() throws SQLException {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT Pessoa.idPessoa, Pessoa.nome, Pessoa.logradouro, Pessoa.cidade, " +
                "Pessoa.estado, Pessoa.telefone, Pessoa.email, PessoaFisica.cpf " +
                "FROM Pessoa " +
                "JOIN PessoaFisica ON Pessoa.idPessoa = PessoaFisica.idPessoa";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(sql);
             ResultSet rs = ConectorBD.getSelect(stmt)) {
            while (rs.next()) {
                PessoaFisica pessoa = new PessoaFisica();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) throws SQLException {
        String sqlInsertPessoa = "INSERT INTO Pessoa (idPessoa, nome, logradouro, cidade,"
                + " estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlInsertPessoaFisica = "INSERT INTO PessoaFisica (idPessoa, cpf)"
                + " VALUES (?, ?)";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtInsertPessoa = conn.prepareStatement(sqlInsertPessoa);
             PreparedStatement stmtInsertPessoaFisica = conn.prepareStatement(sqlInsertPessoaFisica)) {

            // Inserir na tabela Pessoa
            stmtInsertPessoa.setInt(1, pessoa.getId());
            stmtInsertPessoa.setString(2, pessoa.getNome());
            stmtInsertPessoa.setString(3, pessoa.getLogradouro());
            stmtInsertPessoa.setString(4, pessoa.getCidade());
            stmtInsertPessoa.setString(5, pessoa.getEstado());
            stmtInsertPessoa.setString(6, pessoa.getTelefone());
            stmtInsertPessoa.setString(7, pessoa.getEmail());
            stmtInsertPessoa.executeUpdate();

            // Inserir na tabela PessoaFisica
            stmtInsertPessoaFisica.setInt(1, pessoa.getId());
            stmtInsertPessoaFisica.setString(2, pessoa.getCpf());
            stmtInsertPessoaFisica.executeUpdate();
        }
    }

    public void alterar(PessoaFisica pessoa, String novoNome, String novoLogradouro, String novaCidade,
        String novoEstado,String novoTelefone, String novoEmail, String novoCpf) throws SQLException {
        String sql = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?,"
                + " estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        String sqlFisica = "UPDATE PessoaFisica SET cpf = ? WHERE idPessoa = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmtFisica = conn.prepareStatement(sqlFisica)) {

            // Atualizar dados na tabela Pessoa
            stmt.setString(1, novoNome);
            stmt.setString(2, novoLogradouro);
            stmt.setString(3, novaCidade);
            stmt.setString(4, novoEstado);
            stmt.setString(5, novoTelefone);
            stmt.setString(6, novoEmail);
            stmt.setInt(7, pessoa.getId());
            stmt.executeUpdate();

            // Atualizar CPF na tabela PessoaFisica
            stmtFisica.setString(1, novoCpf);
            stmtFisica.setInt(2, pessoa.getId());
            stmtFisica.executeUpdate();
        }
    }
 
    public void excluir(int id) throws SQLException {
    String sql = "DELETE FROM PessoaFisica WHERE idPessoa = ?";
    String sqlPessoa = "DELETE FROM Pessoa WHERE idPessoa = ?";
    
    try (Connection conn = ConectorBD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {
        // Excluir da tabela PessoaFisica
        stmt.setInt(1, id);
        stmt.executeUpdate();

        // Excluir da tabela Pessoa
        stmtPessoa.setInt(1, id);
        stmtPessoa.executeUpdate();

        System.out.println("Pessoa fisica excluida com ID: " + id);
        }
    }
}
