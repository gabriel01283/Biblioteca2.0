public class Bibliotecario implements Usuario {

    private static Bibliotecario instancia;

    private String nome;
    private String cpf;
    private String senha;

    private LivroController livroController; // acesso ao CRUD DOS LIVROS 

    private Bibliotecario(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        //CONTROLADOR DE LIVROS
        this.livroController = new LivroController();
    }
//CONFIRMA O CPF E SENHA
    public boolean autenticar(String cpf, String senha) {
        return this.cpf.equals(cpf) && this.senha.equals(senha);
    }

    public static Bibliotecario getInstancia(String nome, String cpf, String senha) {
        if (instancia == null) {
            instancia = new Bibliotecario(nome, cpf, senha);
        }
        return instancia;
    }

    @Override
    public void mostrarTipo() {
        System.out.println("Sou o bibliotecário administrador do sistema.");
    }


    @Override
    public String getNome() { return nome; }

    @Override
    public String getTipo() { return "Bibliotecário"; }
// METODOS DO CONTROLLER DE LIVROS
    public void cadastrarLivro(String titulo, String genero) {
        livroController.cadastrarLivro(titulo, genero);
    }

    public void removerLivro(String titulo) {
        livroController.removerLivro(titulo);
    }

    public void alterarLivro(String titulo, String novoGenero) {
        livroController.alterarGenero(titulo, novoGenero);
    }

    public void emprestarLivro(String titulo, int diasEntrega) {
        livroController.emprestarLivro(titulo, diasEntrega);
    }

    public void devolverLivro(String titulo) {
        livroController.devolverLivro(titulo);
    }

    public void listarLivros() {
        livroController.listarLivros();
    }

    public String getCpf() { return cpf; }
    public String getSenha() { return senha; }
}
