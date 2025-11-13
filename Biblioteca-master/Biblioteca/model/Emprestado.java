public class Emprestado implements EstadoLivro{

    @Override
    public void emprestar(Livro livro) {
        livro.notificar("Livro já está emprestado. Não é possível emprestar.");
    }

    @Override
    public void devolver(Livro livro) {
        livro.setEstado(new Disponivel());
        livro.notificar("Livro devolvido. Status agora: " + livro.getEstado().getNomeEstado());
    }

    @Override
    public String getNomeEstado() {
        return "Emprestado";
    }
}
