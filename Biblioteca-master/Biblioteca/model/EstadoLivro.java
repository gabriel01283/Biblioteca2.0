public interface EstadoLivro {
    //utiliza os dois padroes criados anteriormente (disponível e emprestado)
    void emprestar(Livro livro);
    void devolver(Livro livro);
    String getNomeEstado();
}
