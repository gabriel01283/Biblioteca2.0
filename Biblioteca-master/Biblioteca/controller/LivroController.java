import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivroController {

    private List<Livro> livros = new ArrayList<>();
    private int prazoPadrao = 7; // dias para devolução automática


    // CADASTRAR LIVRO
    public Livro cadastrarLivro(String titulo, String genero) {
        Livro livro = LivroFactory.criarLivro(titulo, genero);
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso: " + livro.getTitulo());
        return livro;
    }

    
    // EXCLUIR LIVRO
    public boolean excluirLivro(String titulo) {
        Livro livro = buscarLivro(titulo);

        if (livro != null) {
            livros.remove(livro);
            System.out.println("Livro removido: " + titulo);
            return true;
        }

        System.out.println("Livro não encontrado: " + titulo);
        return false;
    }

    
    // ALTERAR DADOS DO LIVRO
    public boolean alterarLivro(String tituloAntigo, String novoTitulo, String novoGenero) {
        Livro livro = buscarLivro(tituloAntigo);

        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setGenero(novoGenero);
            System.out.println("Livro atualizado: " + novoTitulo);
            return true;
        }

        System.out.println("Livro não encontrado: " + tituloAntigo);
        return false;
    }

    // EMPRESTAR LIVRO (gera data automática)
    public boolean emprestarLivro(String titulo) {
        Livro livro = buscarLivro(titulo);

        if (livro != null) {

            // Executa o método emprestar do livro (State)
            livro.emprestar();

            // Gera automaticamente a data de entrega
            Date hoje = new Date();
            long milis = hoje.getTime() + (long) prazoPadrao * 24 * 60 * 60 * 1000;
            Date dataEntregaGerada = new Date(milis);

            livro.setDataEntrega(dataEntregaGerada);

            System.out.println("Livro emprestado: " + titulo);
            System.out.println("Data de entrega gerada automaticamente: " + dataEntregaGerada);
            return true;
        }

        System.out.println("Livro não encontrado: " + titulo);
        return false;
    }

    
    // DEVOLVER LIVRO
    public boolean devolverLivro(String titulo) {
        Livro livro = buscarLivro(titulo);

        if (livro != null) {
            livro.devolver();
            System.out.println("Livro devolvido: " + titulo);
            return true;
        }

        System.out.println("Livro não encontrado: " + titulo);
        return false;
    }

    
    // BUSCAR LIVRO PELO TÍTULO
    public Livro buscarLivro(String titulo) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

   
    // LISTAR TODOS OS LIVROS
    public void listarLivros() {
        System.out.println("---- LISTA DE LIVROS ----");
        for (Livro l : livros) {
            System.out.println(l);
        }
    }
}
