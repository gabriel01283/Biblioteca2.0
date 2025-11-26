import java.io.*;
import java.util.*;

public class LivroController {
    //LISTA QUE GUARDA OS LIVROS DO SISTEMA
    private List<Livro> livros = new ArrayList<>();
    private static final String ARQUIVO = "livros.txt";
// CONSTRUTOR
    public LivroController() {
        carregarLivros();
    }

    // CADASTRAR
    public void cadastrarLivro(String titulo, String genero) {
        // PADRAO STATE, LIVRO INICIA DISPONIVEL
        Livro livro = new Livro(titulo, genero, new Disponivel());
        livros.add(livro);
        salvarLivros();
        System.out.println("Livro cadastrado: " + titulo);
    }

    //  REMOVER 
    public void removerLivro(String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            livros.remove(livro);
            salvarLivros();
            System.out.println("Livro removido: " + titulo);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

    // ALTERAR 
    public void alterarGenero(String titulo, String novoGenero) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            livro.setGenero(novoGenero);
            salvarLivros();
            System.out.println("Gênero do livro '" + titulo + "' alterado para: " + novoGenero);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

    //  EMPRESTAR
    public void emprestarLivro(String titulo, int diasEntrega) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {

            livro.setEstado(new Emprestado());
                                    // pega a data de HOJE
            Calendar cal = Calendar.getInstance();
            // SOMA A DATA DE HOJE PARA VERIFICAR A DATA DE ENTREGA
            cal.add(Calendar.DAY_OF_MONTH, diasEntrega);
            livro.setDataEntrega(cal.getTime());

            salvarLivros();
            System.out.println("Livro emprestado: " + titulo + ", data de entrega: " + livro.getDataEntrega());
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

    //  DEVOLVER
    public void devolverLivro(String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {

            livro.setEstado(new Disponivel());
            livro.setDataEntrega(null);

            salvarLivros();
            System.out.println("Livro devolvido: " + titulo);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }
    // LISTAR
    public void listarLivros() {
        System.out.println("---- LISTA DE LIVROS ----");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (Livro l : livros) {
            System.out.println(l);
        }
    }

    //  BUSCAR
    private Livro buscarLivro(String titulo) {
        for (Livro l : livros) {
            //COMPARA O LIVRO COM O QUE O USUARIO DIGITOU NO TERMINAL
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    //  SALVAR ARQUIVOS
    private void salvarLivros() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Livro l : livros) {
                pw.println(
                        l.getTitulo() + ";" +
                        l.getGenero() + ";" +
                        l.getEstado().getNomeEstado() + ";" +
                        (l.getDataEntrega() != null ? l.getDataEntrega().getTime() : "")
                );
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar livros: " + e.getMessage());
        }
    }

    //  CARREGAR 
    private void carregarLivros() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] partes = linha.split(";");

                if (partes.length >= 3) {
                    String titulo = partes[0];
                    String genero = partes[1];
                    String estadoNome = partes[2];

                    EstadoLivro estado =
                            estadoNome.equalsIgnoreCase("Emprestado")
                                    ? new Emprestado()
                                    : new Disponivel();

                    Livro livro = new Livro(titulo, genero, estado);
                                //VERIFICA SE O ARUIVO AS 4 PARTES PREENCHIDAS
                    if (partes.length == 4 && !partes[3].isEmpty()) {
                        long millis = Long.parseLong(partes[3]);
                        livro.setDataEntrega(new Date(millis));
                    }

                    livros.add(livro);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
    }

    public List<Livro> listarTodosComoLista() {
        return new ArrayList<>(livros);
    }
}
