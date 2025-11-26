public interface EstadoLivro {
    void emprestar(Livro livro);
    void devolver(Livro livro);
    String getNomeEstado();
}
//define as ações que um estado precisa ter,baseado no padrão State