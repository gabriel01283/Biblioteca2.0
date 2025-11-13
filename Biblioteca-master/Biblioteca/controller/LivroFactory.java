import java.util.Date;

public class LivroFactory {

    // Cria livro novo, disponível
    public static Livro criarLivro(String titulo, String genero) {
        EstadoLivro estadoInicial = new Disponivel(); // estado inicial
        return new Livro(titulo, genero, estadoInicial);
    }

    // Cria livro já emprestado
    public static Livro criarLivroEmprestado(String titulo, String genero, Date dataEntrega) {
        // aqui você poderia criar um estado Emprestado
        EstadoLivro estadoInicial = new Disponivel(); // ou new Emprestado() se já existir
        Livro livro = new Livro(titulo, genero, estadoInicial);
        livro.emprestar(dataEntrega); // delega pro estado
        return livro;
    }
}
