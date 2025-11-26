public class Emprestado implements EstadoLivro {
    @Override
    // MOSTRA MENSAGEM QUE O LIVRO JA ESTA EMPRESTADO
    public void emprestar(Livro livro) {
        System.out.println("Livro já está emprestado.");
    }

    @Override
    public void devolver(Livro livro) {
        livro.setEstado(new Disponivel());
    }

    @Override
    public String getNomeEstado() {
        return "Emprestado";
    }
}
