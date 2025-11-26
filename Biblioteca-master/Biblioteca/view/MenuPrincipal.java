import java.util.*;

public class MenuPrincipal {

    private static Scanner sc = new Scanner(System.in);
    private static LivroController livroController = new LivroController();

    public static void main(String[] args) {

        // Bibliotecário único
        Bibliotecario biblio = Bibliotecario.getInstancia(
                "Maria Souza",
                "11122233344",
                "12345"
        );

        int opcao;
        do {
            System.out.println("\n========= SISTEMA DA BIBLIOTECA =========");
            System.out.println("1 - Entrar como Bibliotecário");
            System.out.println("2 - Entrar como Aluno");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerNumero();

            switch (opcao) {
                case 1 -> menuBibliotecario(biblio);
                case 2 -> menuAluno();
                case 3 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    // =============================================================
    // MENU BIBLIOTECÁRIO
    // =============================================================
    private static void menuBibliotecario(Bibliotecario biblio) {

        System.out.print("\nDigite seu CPF: ");
        String cpf = sc.nextLine().trim();

        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine().trim();

        if (!biblio.autenticar(cpf, senha)) {
            System.out.println("CPF ou senha incorretos!");
            return;
        }

        int opcao;
        do {
            System.out.println("\n===== MENU DO BIBLIOTECÁRIO =====");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Remover Livro");
            System.out.println("3 - Alterar Livro");
            System.out.println("4 - Emprestar Livro");
            System.out.println("5 - Devolver Livro");
            System.out.println("6 - Listar Todos os Livros");
            System.out.println("7 - Listar Livros Emprestados");
            System.out.println("8 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerNumero();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> removerLivro();
                case 3 -> alterarLivro();
                case 4 -> emprestarLivro();
                case 5 -> devolverLivro();
                case 6 -> listarLivros();
                case 7 -> listarEmprestados();
                case 8 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 8);
    }

    // =============================================================
    // MENU ALUNO
    // =============================================================
    private static void menuAluno() {
        int opcao;
        do {
            System.out.println("\n===== MENU DO ALUNO =====");
            System.out.println("1 - Listar Livros por Gênero");
            System.out.println("2 - Ver Livros Disponíveis");
            System.out.println("3 - Solicitar Empréstimo");
            System.out.println("4 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerNumero();

            switch (opcao) {
                case 1 -> listarPorGenero();
                case 2 -> listarDisponiveis();
                case 3 -> solicitarEmprestimoAluno();
                case 4 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    // =============================================================
    // AÇÕES PRINCIPAIS
    // =============================================================
    private static void cadastrarLivro() {
        System.out.print("\nTítulo: ");
        String titulo = sc.nextLine().trim();
        System.out.print("Gênero: ");
        String genero = sc.nextLine().trim();

        livroController.cadastrarLivro(titulo, genero);
    }

    private static void removerLivro() {
        listarLivros();
        System.out.print("\nNúmero do livro para remover: ");
        int index = lerNumero() - 1;

        Livro livro = getLivroByIndex(index);
        if (livro == null) return;

        livroController.removerLivro(livro.getTitulo());
    }

    private static void alterarLivro() {
        listarLivros();
        System.out.print("\nEscolha o número do livro para alterar: ");
        int index = lerNumero() - 1;

        Livro livro = getLivroByIndex(index);
        if (livro == null) return;

        System.out.print("Novo título (enter p/ manter): ");
        String novoTitulo = sc.nextLine();
        if (!novoTitulo.isBlank()) livro.setTitulo(novoTitulo);

        System.out.print("Novo gênero (enter p/ manter): ");
        String novoGenero = sc.nextLine();
        if (!novoGenero.isBlank()) livro.setGenero(novoGenero);
    }

    private static void emprestarLivro() {
        listarDisponiveis();
        System.out.print("\nNúmero do livro para emprestar: ");
        int index = lerNumero() - 1;

        Livro livro = getLivroByIndex(index);
        if (livro == null) return;

        livroController.emprestarLivro(livro.getTitulo(), 7);
    }

    private static void devolverLivro() {
        listarEmprestados();
        System.out.print("\nNúmero do livro para devolver: ");
        int index = lerNumero() - 1;

        Livro livro = getLivroByIndex(index);
        if (livro == null) return;

        livroController.devolverLivro(livro.getTitulo());
    }

    // =============================================================
    // LISTAGENS
    // =============================================================
    private static void listarLivros() {
        List<Livro> livros = livroController.listarTodosComoLista();
        System.out.println("\n========== LIVROS CADASTRADOS ==========");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            System.out.printf("%d - %s (%s) [%s]%n",
                    i + 1, l.getTitulo(), l.getGenero(), l.getEstado().getNomeEstado());
        }
    }

    private static void listarEmprestados() {
        List<Livro> livros = livroController.listarTodosComoLista();
        System.out.println("\n===== LIVROS EMPRESTADOS =====");
        boolean encontrou = false;

        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.getEstado().getNomeEstado().equals("Emprestado")) {
                System.out.printf("%d - %s (%s)%n", i + 1, l.getTitulo(), l.getGenero());
                encontrou = true;
            }
        }

        if (!encontrou) System.out.println("Nenhum livro emprestado.");
    }

    private static void listarDisponiveis() {
        List<Livro> livros = livroController.listarTodosComoLista();
        System.out.println("\n===== LIVROS DISPONÍVEIS =====");
        boolean encontrou = false;

        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.getEstado().getNomeEstado().equals("Disponível")) {
                System.out.printf("%d - %s (%s)%n", i + 1, l.getTitulo(), l.getGenero());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum livro disponível.");
    }

    private static void listarPorGenero() {
        System.out.print("\nDigite o gênero: ");
        String genero = sc.nextLine().trim();

        List<Livro> livros = livroController.listarTodosComoLista();
        boolean encontrou = false;

        System.out.println("\n===== LIVROS DO GÊNERO \"" + genero + "\" =====");
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.getGenero().equalsIgnoreCase(genero)) {
                System.out.printf("%d - %s [%s]%n",
                        i + 1, l.getTitulo(), l.getEstado().getNomeEstado());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum livro encontrado.");
    }

    // =============================================================
    // ALUNO SOLICITA EMPRÉSTIMO
    // =============================================================
    private static void solicitarEmprestimoAluno() {
        listarDisponiveis();

        System.out.print("\nEscolha o número do livro: ");
        int index = lerNumero() - 1;

        Livro livro = getLivroByIndex(index);
        if (livro == null) return;

        livroController.emprestarLivro(livro.getTitulo(), 7);
        System.out.println("Empréstimo realizado com sucesso!");
    }

    // =============================================================
    // UTILITÁRIOS
    // =============================================================
    private static Livro getLivroByIndex(int index) {
        List<Livro> livros = livroController.listarTodosComoLista();
        if (index < 0 || index >= livros.size()) {
            System.out.println("Livro não encontrado!");
            return null;
        }
        return livros.get(index);
    }

    private static int lerNumero() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
