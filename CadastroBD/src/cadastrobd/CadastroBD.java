/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import cadatrobd.model.PessoaFisica;
import cadatrobd.model.PessoaFisicaDAO;
import cadatrobd.model.PessoaJuridica;
import cadatrobd.model.PessoaJuridicaDAO;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class CadastroBD {

    private static final Scanner sc = new Scanner(System.in);
    private static final PessoaFisicaDAO pfDao = new PessoaFisicaDAO();
    private static final PessoaJuridicaDAO pjDao = new PessoaJuridicaDAO();

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            printMenu();
            opcao = inputInt("ESCOLHA: ");
            switch (opcao) {
                case 1 ->
                    incluir();
                case 2 ->
                    alterar();
                case 3 ->
                    excluir();
                case 4 ->
                    buscarPeloId();
                case 5 ->
                    exibirTodos();
                case 0 ->
                    System.out.println("Finalizando...");
                default ->
                    System.out.println("Escolha invalida!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n==============================");
        System.out.println("1 - Incluir");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Buscar pelo ID");
        System.out.println("5 - Exibir todos");
        System.out.println("0 - Sair");
        System.out.println("==============================");
    }

    private static String input(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private static int inputInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada invalida. Tente novamente.");
            return inputInt(prompt);
        }
    }

    private static void incluir() {
        System.out.println("\nIncluindo pessoa...");
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipoPessoa = input("TIPO DE PESSOA: ").toUpperCase();
        Integer id = inputInt("Informe o ID: ");
        switch (tipoPessoa) {
            case "F" -> {
                try {
                    pfDao.incluir(criarPessoaFisica(id));
                    System.out.println("Pessoa fisica incluida com sucesso!");
                } catch (SQLException e) {
                    System.out.println("Erro ao incluir pessoa fisica: " + e.getMessage());
                }
            }
            case "J" -> {
                try {
                    pjDao.incluir(criarPessoaJuridica(id));
                    System.out.println("Pessoa juridica incluida com sucesso!");
                } catch (SQLException e) {
                    System.out.println("Erro ao incluir pessoa juridica: " + e.getMessage());
                }
            }
            default ->
                System.out.println("Tipo de pessoa invalido!");
        }
    }

    private static PessoaFisica criarPessoaFisica(Integer id) {
        System.out.println("Criando Pessoa Fisica...");
        String nome = input("Informe o nome: ");
        String logradouro = input("Informe o logradouro: ");
        String cidade = input("Informe a cidade: ");
        String estado = input("Informe o estado: ");
        String telefone = input("Informe o telefone: ");
        String email = input("Informe o email: ");
        String cpf = input("Informe o CPF: ");
        return new PessoaFisica(id, nome, logradouro, cidade, estado, telefone, email, cpf);
    }

    private static PessoaJuridica criarPessoaJuridica(Integer id) {
        System.out.println("Criando Pessoa Juridica...");
        String nome = input("Informe o nome: ");
        String logradouro = input("Informe o logradouro: ");
        String cidade = input("Informe a cidade: ");
        String estado = input("Informe o estado: ");
        String telefone = input("Informe o telefone: ");
        String email = input("Informe o email: ");
        String cnpj = input("Informe o CNPJ: ");
        return new PessoaJuridica(id, nome, logradouro, cidade, estado, telefone, email, cnpj);
    }

    private static void alterar() {
        System.out.println("\nAlterando pessoa...");
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipoPessoa = input("TIPO DE PESSOA: ").toUpperCase();
        if (tipoPessoa.equals("F")) {
            try {
                Integer id = inputInt("Informe o ID da Pessoa Fisica: ");
                PessoaFisica pf = pfDao.getPessoa(id);
                if (pf != null) {
                    System.out.println("Dados atuais da Pessoa Fisica:");
                    pf.exibir();

                    String novoNome = input("Informe o novo nome: ");
                    String novoLogradouro = input("Informe o novo logradouro: ");
                    String novaCidade = input("Informe a nova cidade: ");
                    String novoEstado = input("Informe o novo estado: ");
                    String novoTelefone = input("Informe o novo telefone: ");
                    String novoEmail = input("Informe o novo email: ");
                    String novoCpf = input("Informe o novo CPF: ");

                    pfDao.alterar(pf, novoNome, novoLogradouro, novaCidade,
                            novoEstado, novoTelefone, novoEmail, novoCpf);
                    System.out.println("Pessoa fisica alterada com sucesso!");
                } else {
                    System.out.println("ID errado!");
                }
            } catch (NullPointerException | SQLException e) {
                System.out.println("Erro ao alterar pessoa fisica: " + e.getMessage());
            }
        } else if (tipoPessoa.equals("J")) {
            try {
                Integer id = inputInt("Informe o ID da Pessoa Juridica: ");
                PessoaJuridica pj = pjDao.getPessoa(id);
                if (pj != null) {
                    System.out.println("Dados atuais da Pessoa Juridica:");
                    pj.exibir();

                    String novoNome = input("Informe o novo nome: ");
                    String novoLogradouro = input("Informe o novo logradouro: ");
                    String novaCidade = input("Informe a nova cidade: ");
                    String novoEstado = input("Informe o novo estado: ");
                    String novoTelefone = input("Informe o novo telefone: ");
                    String novoEmail = input("Informe o novo email: ");
                    String novoCnpj = input("Informe o novo CNPJ: ");

                    pjDao.alterar(pj, novoNome, novoLogradouro, novaCidade,
                            novoEstado, novoTelefone, novoEmail, novoCnpj);
                    System.out.println("Pessoa juridica alterada com sucesso!");
                } else {
                    System.out.println("ID errado!");
                }
            } catch (NullPointerException | SQLException e) {
                System.out.println("Erro ao alterar pessoa juridica: " + e.getMessage());
            }
        } else {
            System.out.println("Tipo de pessoa invalido!");
        }
    }

    private static void excluir() {
        System.out.println("\nExcluindo pessoa...");
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipoPessoa = input("TIPO DE PESSOA: ").toUpperCase();
        switch (tipoPessoa) {
            case "F" -> {
                try {
                    Integer id = inputInt("Informe o ID da Pessoa Fisica: ");
                    PessoaFisica pf = pfDao.getPessoa(id);
                    if (pf != null) {
                        pfDao.excluir(pf.getId());
                        System.out.println("Sucesso ao excluir!");
                    } else {
                        System.out.println("ID errado!");
                    }
                } catch (NullPointerException | SQLException e) {
                    System.out.println("Erro ao excluir pessoa fisica: " + e.getMessage());
                }
            }
            case "J" -> {
                try {
                    Integer id = inputInt("Informe o ID da Pessoa Juridica: ");
                    PessoaJuridica pj = pjDao.getPessoa(id);
                    if (pj != null) {
                        pjDao.excluir(pj.getId());
                        System.out.println("Sucesso ao excluir!");
                    } else {
                        System.out.println("ID errado!");
                    }
                } catch (NullPointerException | SQLException e) {
                    System.out.println("Erro ao excluir pessoa juridica: " + e.getMessage());
                }
            }
            default ->
                System.out.println("Tipo de pessoa invalido!");
        }
    }

    private static void buscarPeloId() {
        System.out.println("\nBuscando pessoa pelo ID...");
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipoPessoa = input("TIPO DE PESSOA: ").toUpperCase();
        switch (tipoPessoa) {
            case "F" -> {
                try {
                    Integer id = inputInt("Informe o ID da Pessoa Fisica: ");
                    PessoaFisica pf = pfDao.getPessoa(id);
                    if (pf != null) {
                        pf.exibir();
                    } else {
                        System.err.println("Pessoa fisica com o ID " + id + " nao encontrada!");
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao buscar pessoa fisica: " + e.getMessage());
                }
            }
            case "J" -> {
                try {
                    Integer id = inputInt("Informe o ID da Pessoa Juridica: ");
                    PessoaJuridica pj = pjDao.getPessoa(id);
                    if (pj != null) {
                        pj.exibir();
                    } else {
                        System.err.println("Pessoa juridica com o ID " + id + " nao encontrada!");
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao buscar pessoa juridica: " + e.getMessage());
                }
            }
            default ->
                System.out.println("Tipo de pessoa invalido!");
        }
    }

    private static void exibirTodos() {
        System.out.println("\nExibindo todas as pessoas...");
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipoPessoa = input("TIPO DE PESSOA: ").toUpperCase();
        try {
            switch (tipoPessoa) {
                case "F" -> {
                    ArrayList<PessoaFisica> listaPf = (ArrayList<PessoaFisica>) pfDao.getPessoas();
                    for (PessoaFisica pessoa : listaPf) {
                        pessoa.exibir();
                    }
                }
                case "J" -> {
                    ArrayList<PessoaJuridica> listaPj = (ArrayList<PessoaJuridica>) pjDao.getPessoas();
                    for (PessoaJuridica pessoa : listaPj) {
                        pessoa.exibir();
                    }
                }
                default ->
                    System.out.println("Tipo de pessoa invalido!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao exibir pessoas: " + e.getMessage());
        }
    }
}
