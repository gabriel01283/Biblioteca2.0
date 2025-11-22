public class Disponivel implements EstadoLivro {
    @Override
    public void emprestar(Livro livro) {
        livro.setEstado(new Emprestado());
    }

    @Override
    public void devolver(Livro livro) {
        System.out.println("Livro já está disponível.");
    }

    @Override
    public String getNomeEstado() {
        return "Disponível";
    }
}
