package br.com.zup.biblioteca.entidade;

public interface RegrasNegocio {
    boolean podeEmprestarLivro(String isbn); // Verifica se o livro pode ser emprestado
    boolean podeEmprestarParaUsuario(int idUsuario); // Verifica se o usuário pode pegar mais livros
    void marcarLivroComoDisponivel(String isbn); // Marca o livro como disponível após devolução
}