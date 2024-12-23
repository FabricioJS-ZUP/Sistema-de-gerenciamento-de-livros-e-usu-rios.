package br.com.zup.biblioteca.entidade;

import java.util.ArrayList;

// Classe que representa um usuário da biblioteca
public class Usuario {
    private String nome; // Nome do usuário
    private int id; // Id do usuário
    private ArrayList<Livro> livrosEmprestados;

    // Construtor para inicializar os atributos do usuário
    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    // Adiciona um livro à lista de livros emprestados
    public void adicionarLivro(Livro livro) {
        livrosEmprestados.add(livro);
    }

    // Remove um livro da lista de livros emprestados
    public void removerLivro(Livro livro) {
        livrosEmprestados.remove(livro);
    }
}