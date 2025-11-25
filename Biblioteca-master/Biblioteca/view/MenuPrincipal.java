import java.util.*;

public class MenuPrincipal {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Bibliotecário único
        Bibliotecario biblio = Bibliotecario.getInstancia(
                "Maria Souza",
                "11122233344",
                "12345"
        );

        List<Livro> livros = new ArrayList<>();

        while (true) {
            System.out.println("\n========= SISTEMA DA BIBLIOTECA =========");
            System.out.println("1 - Entrar como Bibliotecário");
            System.out.println("2 - Entrar como Aluno");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerNumero();

            switch (opcao) {
                case 1 -> menuBibliotecario(biblio, livros);
                case 2 -> menuAluno(livros);
                case 3 -> {
                    System.out.println("Encerrando o sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ====================== MENU BIBLIOTECARIO ======================
    private static void menuBibliotecario(Bibliotecario biblio, List<Livro> livros) {

        System.out.print("\nDigite seu CPF: ");
        String cpf = sc.nextLine().trim();

        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

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
            System.out.println("6 - Listar Livros Cadastrados");
            System.out.println("7 - Listar Livros Emprestados");
            System.out.println("8 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerNumero();

            switch (opcao) {
                case 1 -> cadastrarLivro(livros);
                case 2 -> removerLivro(livros);
                case 3 -> alterarLivro(livros);
                case 4 -> emprestarLivro(biblio, livros);
                case 5 -> devolverLivro(biblio, livros);
                case 6 -> listarLivros(livros);
                case 7 -> listarEmprestados(livros);
                case 8 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 8);
    }

    // ====================== MENU ALUNO ======================
    private static void menuAluno(List<Livro> livros) {
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
                case 1 -> listarPorGenero(livros);
                case 2 -> listarDisponiveis(livros);
                case 3 -> solicitarEmprestimo(livros);
                case 4 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    // ====================== MÉTODOS BIBLIOTECARIO ======================
    private static void cadastrarLivro(List<Livro> livros) {
        System.out.print("\nTítulo: ");
        String titulo = sc.nextLine();
        System.out.print("Gênero: ");
        String genero = sc.nextLine();

        Livro livro = new Livro(titulo, genero, new Disponivel());
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void removerLivro(List<Livro> livros) {
        listarLivros(livros);
        System.out.print("\nDigite o número do livro para remover: ");
        int index = lerNumero() - 1;
        if (index < 0 || index >= livros.size()) {
            System.out.println("Livro não encontrado!");
            return;
        }
        livros.remove(index);
        System.out.println("✔ Livro removido!");
    }

    private static void alterarLivro(List<Livro> livros) {
        listarLivros(livros);
        System.out.print("\nEscolha o número do livro para alterar: ");
        int index = lerNumero() - 1;
        if (index < 0 || index >= livros.size()) {
            System.out.println("Livro não encontrado!");
            return;
        }

        Livro livro = livros.get(index);
        System.out.print("Novo título (enter para manter): ");
        String novoTitulo = sc.nextLine();
        if (!novoTitulo.isBlank()) livro.setTitulo(novoTitulo);
        System.out.print("Novo gênero (enter para manter): ");
        String novoGenero = sc.nextLine();
        if (!novoGenero.isBlank()) livro.setGenero(novoGenero);

        System.out.println("✔ Livro alterado com sucesso!");
    }

    private static void emprestarLivro(Bibliotecario biblio, List<Livro> livros) {
        listarDisponiveis(livros);
        System.out.print("\nEscolha o número do livro para emprestar: ");
        int index = lerNumero() - 1;
        if (index < 0 || index >= livros.size()) {
            System.out.println("Livro não encontrado!");
            return;
        }

        Livro livro = livros.get(index);
        biblio.emprestarLivro(livro.getTitulo(), 7);
    }

    private static void devolverLivro(Bibliotecario biblio, List<Livro> livros) {
        listarEmprestados(livros);
        System.out.print("\nEscolha o número do livro para devolver: ");
        int index = lerNumero() - 1;
        if (index < 0 || index >= livros.size()) {
            System.out.println("Livro não encontrado!");
            return;
        }
        Livro livro = livros.get(index);
        biblio.devolverLivro(livro.getTitulo());
    }

    private static void listarLivros(List<Livro> livros) {
        System.out.println("\n===== LIVROS CADASTRADOS =====");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        int i = 1;
        for (Livro l : livros) {
            System.out.println(i++ + " - " + l);
        }
    }

    private static void listarEmprestados(List<Livro> livros) {
        System.out.println("\n===== LIVROS EMPRESTADOS =====");
        boolean encontrou = false;
        int i = 1;
        for (Livro l : livros) {
            if (!l.getEstado().getNomeEstado().equals("Disponível")) {
                System.out.println(i + " - " + l);
                encontrou = true;
            }
            i++;
        }
        if (!encontrou) System.out.println("Nenhum livro emprestado.");
    }

    // ====================== MÉTODOS ALUNO ======================
    private static void listarPorGenero(List<Livro> livros) {
        System.out.print("\nDigite o gênero: ");
        String genero = sc.nextLine();
        boolean encontrou = false;
        System.out.println("\n===== LIVROS DO GÊNERO \"" + genero + "\" =====");
        for (Livro l : livros) {
            if (l.getGenero().equalsIgnoreCase(genero)) {
                System.out.println("- " + l);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum livro encontrado desse gênero.");
    }

    private static void listarDisponiveis(List<Livro> livros) {
        System.out.println("\n===== LIVROS DISPONÍVEIS =====");
        boolean encontrou = false;
        int i = 1;
        for (Livro l : livros) {
            if (l.getEstado().getNomeEstado().equals("Disponível")) {
                System.out.println(i + " - " + l);
                encontrou = true;
            }
            i++;
        }
        if (!encontrou) System.out.println("Nenhum livro disponível.");
    }

    private static void solicitarEmprestimo(List<Livro> livros) {
        listarDisponiveis(livros);
        System.out.print("\nEscolha o número do livro: ");
        int index = lerNumero() - 1;
        if (index < 0 || index >= livros.size()) {
            System.out.println("Livro não encontrado!");
            return;
        }
        Livro livro = livros.get(index);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        livro.emprestar(cal.getTime());
        System.out.println("✔ Empréstimo realizado com sucesso!");
    }

    // ====================== AUXILIAR ======================
    private static int lerNumero() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Entrada inválida, tente novamente!");
            return -1;
        }
    }
}
