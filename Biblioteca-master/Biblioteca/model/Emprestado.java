public class Emprestado implements EstadoLivro {
    @Override
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
