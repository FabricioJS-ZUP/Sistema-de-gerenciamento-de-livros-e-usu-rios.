
package br.com.zup.biblioteca.entidade;

import java.util.ArrayList;

// Classe que implementa a interface RegrasNegocio e gerencia a biblioteca
public class Biblioteca implements RegrasNegocio {
    private ArrayList<Livro> livros; // Lista de livros cadastrados na biblioteca
    private ArrayList<Usuario> usuarios; // Lista de usuários cadastrados na biblioteca

    // Construtor para inicializar as listas
    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    // Implementação das regras de negócio
    @Override
    public boolean podeEmprestarLivro(String isbn) {
        Livro livro = buscarLivroPorIsbn(isbn);
        return livro != null && livro.isDisponivel(); // Retorna true se o livro existir e estiver disponível
    }

    @Override
    public boolean podeEmprestarParaUsuario(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        return usuario != null && usuario.getLivrosEmprestados().size() < 3; // Retorna true se o usuário tiver menos de 3 livros emprestados
    }

    @Override
    public void marcarLivroComoDisponivel(String isbn) {
        Livro livro = buscarLivroPorIsbn(isbn);
        if (livro != null) {
            livro.setDisponivel(true); // Marca o livro como disponível
        }
    }

    // Método para cadastrar um livro
    public void cadastrarLivro(Livro livro) {
        if (isbnJaCadastrado(livro.getIsbn())) {
            System.out.println("Erro: Já existe um livro cadastrado com este ISBN.");
        } else {
            livros.add(livro);
            System.out.println("Livro cadastrado com sucesso!");
        }
    }

    // Método para cadastrar um usuário
    public void cadastrarUsuario(Usuario usuario) {
        if (idJaCadastrado(usuario.getId())) {
            System.out.println("Erro: Já existe um usuário cadastrado com este ID.");
        } else {
            usuarios.add(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
        }
    }

    // Método para realizar o empréstimo de um livro
    public void realizarEmprestimo(String isbn, int idUsuario) {
        if (!podeEmprestarLivro(isbn)) {
            System.out.println("Erro: O livro não está disponível para empréstimo.");
            return;
        }
        if (!podeEmprestarParaUsuario(idUsuario)) {
            System.out.println("Erro: O usuário já possui 3 livros emprestados.");
            return;
        }
        Livro livro = buscarLivroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (livro != null && usuario != null) {
            usuario.adicionarLivro(livro); // Adiciona o livro à lista de empréstimos do usuário
            livro.setDisponivel(false); // Marca o livro como indisponível
            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Erro: Livro ou usuário não encontrado.");
        }
    }

    // Método para realizar a devolução de um livro
    public void realizarDevolucao(String isbn, int idUsuario) {
        Livro livro = buscarLivroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (livro != null && usuario != null) {
            if (usuario.getLivrosEmprestados().contains(livro)) {
                usuario.removerLivro(livro); // Remove o livro da lista de empréstimos do usuário
                marcarLivroComoDisponivel(isbn); // Marca o livro como disponível
                System.out.println("Devolução realizada com sucesso!");
            } else {
                System.out.println("Erro: O usuário não possui este livro emprestado.");
            }
        } else {
            System.out.println("Erro: Livro ou usuário não encontrado.");
        }
    }

    // Método para exibir todos os livros disponíveis
    public void exibirLivrosDisponiveis() {
        System.out.print("Estão disponíveis ");
        boolean primeiro = true;
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                if (!primeiro) {
                    System.out.print(" | ");
                }
                System.out.print("livro: " + livro.getTitulo() + " do autor " + livro.getAutor() + " com o ISBN " + livro.getIsbn());
                primeiro = false;
            }
        }
        System.out.println(); // Quebra de linha ao final
    }

    // Método para exibir a visualização geral
    public void visualizacaoGeral() {
        System.out.println("Visualização Geral:");
        System.out.println("Livros disponíveis:");
        exibirLivrosDisponiveis();
        System.out.println("\nUsuários e seus livros emprestados:");
        for (Usuario usuario : usuarios) {
            System.out.print("O usuário \"" + usuario.getNome() + " com o ID " + usuario.getId() + "\" TEM OS LIVROS: ");
            if (usuario.getLivrosEmprestados().isEmpty()) {
                System.out.println("Nenhum livro emprestado.");
            } else {
                boolean primeiro = true;
                for (Livro livro : usuario.getLivrosEmprestados()) {
                    if (!primeiro) {
                        System.out.print(" | ");
                    }
                    System.out.print(livro.getTitulo() + " do autor " + livro.getAutor() + " com o ISBN " + livro.getIsbn());
                    primeiro = false;
                }
                System.out.println(); // Quebra de linha ao final de cada usuário
            }
        }
    }

    // Métodos auxiliares para buscar livros e usuários
    private Livro buscarLivroPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    // Validação para evitar duplicidade de ISBNs
    public boolean isbnJaCadastrado(String isbn) {
        return buscarLivroPorIsbn(isbn) != null;
    }

    // Validação para evitar duplicidade de IDs
    public boolean idJaCadastrado(int id) {
        return buscarUsuarioPorId(id) != null;
    }
}
