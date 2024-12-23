/*
Aluno: Fabrício John Sullivan
Dia: 23/12/2024
Projeto: Catalisa
Professora: Laura Fumagalli Rodrigues
Atividade 1 de POO em java
Neste projeto foi pedido para desenvolver um sistema simples de gerenciamento de livros e usuários.
 */

package br.com.zup.biblioteca;

import br.com.zup.biblioteca.entidade.Biblioteca;
import br.com.zup.biblioteca.entidade.Livro;
import br.com.zup.biblioteca.entidade.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("1: Cadastrar livro");
            System.out.println("2: Cadastrar usuário");
            System.out.println("3: Realizar empréstimo");
            System.out.println("4: Realizar devolução");
            System.out.println("5: Exibir livros disponíveis");
            System.out.println("6: Visualização Geral");
            System.out.println("7: Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();
                    biblioteca.cadastrarLivro(new Livro(titulo, autor, isbn));
                    break;
                case 2:
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o ID do usuário: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    biblioteca.cadastrarUsuario(new Usuario(nome, id));
                    break;
                case 3:
                    System.out.print("Digite o ISBN do livro: ");
                    String isbnEmprestimo = scanner.nextLine();
                    System.out.print("Digite o ID do usuário: ");
                    int idUsuarioEmprestimo = scanner.nextInt();
                    scanner.nextLine();
                    biblioteca.realizarEmprestimo(isbnEmprestimo, idUsuarioEmprestimo);
                    break;
                case 4:
                    System.out.print("Digite o ISBN do livro: ");
                    String isbnDevolucao = scanner.nextLine();
                    System.out.print("Digite o ID do usuário: ");
                    int idUsuarioDevolucao = scanner.nextInt();
                    scanner.nextLine();
                    biblioteca.realizarDevolucao(isbnDevolucao, idUsuarioDevolucao);
                    break;
                case 5:
                    biblioteca.exibirLivrosDisponiveis();
                    break;
                case 6:
                    biblioteca.visualizacaoGeral();
                    break;
                case 7:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção inválida.Tente de novo.");
            }
        } while (opcao != 7);

        scanner.close();
    }
}