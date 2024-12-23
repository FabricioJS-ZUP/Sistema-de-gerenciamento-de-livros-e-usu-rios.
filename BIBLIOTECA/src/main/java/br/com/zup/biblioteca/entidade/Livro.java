
package br.com.zup.biblioteca.entidade;

// Classe que representa um livro na biblioteca
public class Livro {
 private String titulo;
 private String autor;
 private String isbn; // Código único do livro (ISBN)
 private boolean disponivel; // Indica se o livro está disponível para empréstimo

 // Construtor para inicializar os atributos do livro
 public Livro(String titulo, String autor, String isbn) {
  this.titulo = titulo;
  this.autor = autor;
  this.isbn = isbn;
  this.disponivel = true; // Por padrão, o livro está disponível
 }

 public String getTitulo() {
  return titulo;
 }

 public String getAutor() {
  return autor;
 }

 public String getIsbn() {
  return isbn;
 }

 public boolean isDisponivel() {
  return disponivel;
 }

 public void setDisponivel(boolean disponivel) {
  this.disponivel = disponivel;
 }
}