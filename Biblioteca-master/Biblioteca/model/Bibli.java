public class Bibli {
    private String nome;
    private String login;
    private String senha;
    private String cargo;

    public Bibli(String nome, String login, String senha, String cargo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public void cadastrarLivro() {
        System.out.println(" Cadastrando novo livro...");
    }

    public void excluirLivro() {
        System.out.println("luindo livro do sistema...");
    }

    public void alterarStatusLivro() {
        System.out.println("Alterando status do livro...");
    }

    public void gerarDataEntrega() {
        System.out.println("Gerando data de entrega...");
    }

    @Override
    public String toString() {
        return "Bibliotecário {" +
                "Nome='" + nome + '\'' +
                ", Login='" + login + '\'' +
                ", Cargo='" + cargo + '\'' +
                '}';
    }
}
