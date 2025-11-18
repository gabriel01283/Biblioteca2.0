public class Disponivel implements EstadoLivro {
// padrao state, muda de acordo com a disponíbildade do livro 
    @Override
    public void emprestar(Livro livro) {
        livro.setEstado(new Emprestado());
        livro.notificar("Livro emprestado. Status agora: " + livro.getEstado().getNomeEstado());
    }

    @Override
    public void devolver(Livro livro) {
        livro.notificar("Livro já está disponível. Devolução não necessária.");
    }

    @Override
    public String getNomeEstado() {
        return "Disponível";
    }

}
