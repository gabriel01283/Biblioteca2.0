public class Bibliotecario implements Usuario {

    private static Bibliotecario instancia;

    private String nome;
    private String cpf;
    private String senhaCriptografada;

    private LivroController livroController; // acesso ao CRUD

    private Bibliotecario(String nome, String cpf, String senhaCriptografada) {
        this.nome = nome;
        this.cpf = cpf;
        this.senhaCriptografada = senhaCriptografada;
        this.livroController = new LivroController(); // controlador do CRUD
    }

    public static Bibliotecario getInstancia(String nome, String cpf, String senhaCriptografada) {
        if (instancia == null) {
            instancia = new Bibliotecario(nome, cpf, senhaCriptografada);
        }
        return instancia;
    }

    // ========== MÉTODOS DO USUARIO ==========

    @Override
    public void mostrarTipo() {
        System.out.println("Sou o bibliotecário administrador do sistema.");
    }

    @Override
    public void separador() {
        System.out.println("--------------------------------");
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public String getTipo() { return "Bibliotecário"; }

    // ========== ACESSO AO CRUD ==========

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

    // GETTERS

    public String getCpf() { return cpf; }
    public String getSenhaCriptografada() { return senhaCriptografada; }
}
