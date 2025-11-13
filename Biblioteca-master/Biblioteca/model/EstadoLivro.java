public interface EstadoLivro {
    void emprestar(Livro livro);
    void devolver(Livro livro);
    String getNomeEstado();
}
