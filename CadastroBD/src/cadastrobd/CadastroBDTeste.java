/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import java.sql.SQLException;
import java.util.List;
import cadatrobd.model.PessoaFisica;
import cadatrobd.model.PessoaFisicaDAO;
import cadatrobd.model.PessoaJuridica;
import cadatrobd.model.PessoaJuridicaDAO;
import cadastrobd.model.Util.ConectorBD;
import java.sql.Connection;

/**
 *
 * @author Administrador
 */
public class CadastroBDTeste {

    public static void main(String[] args) {
        try {
            Connection conn = ConectorBD.getConnection();
            PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
            PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();

            // Criar uma pessoa física
            PessoaFisica pf = new PessoaFisica(8, "Angelo", "Rua Z, 40", "Vitoria", "ES",
                    "1234-5678", "angelo@gmail.com", "12345678910");

            // Persistir a pessoa física no banco de dados
            pfDAO.incluir(pf);
            System.out.println("Pessoa fisica criada:");
            pf.exibir();
            System.out.println();

            // Alterar os dados da pessoa fisica no banco
            pfDAO.alterar(pf, "Angelo Pereira", "Rua B, 11", "Salvador", "BA",
                    "9999-8888", "angelo.pereira@email.com", "12345678900");
            System.out.println("---------------------------------");
            System.out.println("Dados da pessoa fisica alterados.");
            System.out.println("---------------------------------");
            System.out.println();

            // Consultar todas as pessoas físicas do banco de dados e listar no console
            List<PessoaFisica> pessoasFisicas = pfDAO.getPessoas();
            System.out.println("Todas as pessoas fisicas:");
            for (PessoaFisica pessoaFisica : pessoasFisicas) {
                pessoaFisica.exibir();
            }
            System.out.println();

            // Excluir a pessoa física criada anteriormente no banco
            System.out.println("---------------------------------");
            pfDAO.excluir(pf.getId());
            System.out.println("---------------------------------");
            System.out.println();

            // Criar uma pessoa jurídica
            PessoaJuridica pj = new PessoaJuridica(9, "Empresa Vespor", "Av. Darlin Santos, 90",
                    "Vitoria", "ES", "1234-5678", "empresa@vespor.com", "12345678901234");

            // Persistir a pessoa jurídica no banco de dados
            pjDAO.incluir(pj);
            System.out.println("Pessoa juridica criada:");
            pj.exibir();
            System.out.println();

            // Alterar os dados da pessoa jurídica no banco
            pjDAO.alterar(pj, "Companhia AutomotivaSA", "Av. Nova, 200", "Salvador", "BA",
                    "9876-5432", "companhia@automotivasa.com", "98765432109876");
            System.out.println("---------------------------------");
            System.out.println("Dados da pessoa juridica alterados.");
            System.out.println("---------------------------------");
            System.out.println();

            // Consultar todas as pessoas jurídicas do banco de dados e listar no console
            List<PessoaJuridica> pessoasJuridicas = pjDAO.getPessoas();
            System.out.println("Todas as pessoas juridicas:");
            for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
                pessoaJuridica.exibir();
            }
            System.out.println();

            // Excluir a pessoa jurídica criada anteriormente no banco
            System.out.println("---------------------------------");
            pjDAO.excluir(pj.getId());
            System.out.println("---------------------------------");
            System.out.println();

            ConectorBD.close(conn);

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
