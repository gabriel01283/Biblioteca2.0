import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class LivroController {

    private List<Livro> livros = new ArrayList<>();

    // ===================== CADASTRAR =====================
    public void cadastrarLivro(String titulo, String genero) {
        Livro livro = new Livro(titulo, genero, new Disponivel());
        livros.add(livro);
        System.out.println("Livro cadastrado: " + titulo);
    }

    // ===================== REMOVER =====================
    public void removerLivro(String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            livros.remove(livro);
            System.out.println("Livro removido: " + titulo);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

    // ===================== ALTERAR =====================
    public void alterarGenero(String titulo, String novoGenero) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            livro.setGenero(novoGenero);
            System.out.println("Gênero do livro '" + titulo + "' alterado para: " + novoGenero);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

    // ===================== EMPRESTAR =====================
    public void emprestarLivro(String titulo, int diasEntrega) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            livro.emprestar();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, diasEntrega);
            livro.setDataEntrega(cal.getTime());
            System.out.println("Livro emprestado: " + titulo + ", data de entrega: " + livro.getDataEntrega());
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

    // ===================== DEVOLVER =====================
    public void devolverLivro(String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            livro.devolver();
            System.out.println("Livro devolvido: " + titulo);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

    // ===================== LISTAR =====================
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

    // ===================== BUSCAR =====================
    private Livro buscarLivro(String titulo) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }
}
