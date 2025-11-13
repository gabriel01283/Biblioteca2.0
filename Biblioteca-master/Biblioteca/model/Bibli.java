import java.util.Date;

public class Bibli {

    private static Bibli instancia;

    private String nome;
    private String login;
    private String senha;

    private Bibli(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public static Bibli getInstancia(String nome, String login, String senha) {
        if (instancia == null) {
            instancia = new Bibli(nome, login, senha);
        }
        return instancia;
    }

    // Cadastra um livro (simples, só exemplo)
    public void cadastrarLivro(Livro livro) {
        System.out.println("Cadastrando livro: " + livro.getTitulo());
    }

    // Exclui um livro
    public void excluirLivro(Livro livro) {
        System.out.println("Excluindo livro: " + livro.getTitulo());
    }

    // Empresta o livro (delegando pro Estado)
    public void emprestarLivro(Livro livro, Date dataEntrega) {
        livro.emprestar(dataEntrega); // State decide se pode emprestar
        System.out.println("Livro '" + livro.getTitulo() + "' emprestado. Status: " + livro.getEstado().getNomeEstado());
    }

    // Devolve o livro (delegando pro Estado)
    public void devolverLivro(Livro livro) {
        livro.devolver(); // State decide se pode devolver
        System.out.println("Livro '" + livro.getTitulo() + "' devolvido. Status: " + livro.getEstado().getNomeEstado());
    }

    // Ver status atual
    public String verStatusLivro(Livro livro) {
        return livro.getEstado().getNomeEstado();
    }

    // Getters e setters do bibliotecário
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return "Bibliotecário {" +
                "Nome='" + nome + '\'' +
                ", Login='" + login + '\'' +
                '}';
    }
}
