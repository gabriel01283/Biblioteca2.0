import java.util.*;

public class Livro {

    private static final int PRAZO_PADRAO = 7;

    private String titulo;
    private String genero;
    private EstadoLivro estado;
    private Date dataEntrega;
    private Usuario emprestadoPara;

    private List<ObservadorLivro> observadores = new ArrayList<>();

    public Livro(String titulo, String genero, EstadoLivro estadoInicial) {
        this.titulo = titulo;
        this.genero = genero;
        this.estado = estadoInicial;
        this.dataEntrega = null;
        this.emprestadoPara = null;
    }

    public void emprestar() {
        if (estado != null) {
            estado.emprestar(this);
            gerarDataEntregaAutomatica(PRAZO_PADRAO);
            notificar("Livro emprestado. Data de entrega: " + dataEntrega);
        }
    }

    public void emprestar(Usuario usuario) {
        this.emprestar();
        this.emprestadoPara = usuario;
        notificar("Livro emprestado para: " + usuario.getNome());
    }

    public void emprestar(Date dataEntrega) {
        if (estado != null) {
            estado.emprestar(this);
            this.dataEntrega = dataEntrega;
            notificar("Livro emprestado. Data de entrega: " + dataEntrega);
        }
    }

    public void devolver() {
        if (estado != null) {
            estado.devolver(this);
            this.dataEntrega = null;
            this.emprestadoPara = null;
            notificar("Livro devolvido.");
        }
    }

    public void adicionarObservador(ObservadorLivro obs) {
        if (obs != null && !observadores.contains(obs)) {
            observadores.add(obs);
        }
    }

    public void removerObservador(ObservadorLivro obs) {
        observadores.remove(obs);
    }

    private void notificar(String mensagem) {
        for (ObservadorLivro obs : new ArrayList<>(observadores)) {
            obs.atualizar(this, mensagem);
        }
    }

    private void gerarDataEntregaAutomatica(int dias) {
        Date hoje = new Date();
        long milis = hoje.getTime() + (long) dias * 24 * 60 * 60 * 1000;
        this.dataEntrega = new Date(milis);
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public EstadoLivro getEstado() { return estado; }
    public void setEstado(EstadoLivro estado) {
        this.estado = estado;
        notificar("Estado alterado para: " + (estado != null ? estado.getNomeEstado() : "null"));
    }

    public Usuario getEmprestadoPara() { return emprestadoPara; }
    public void setEmprestadoPara(Usuario usuario) { this.emprestadoPara = usuario; }

    public Date getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(Date dataEntrega) { this.dataEntrega = dataEntrega; }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", estado=" + (estado != null ? estado.getNomeEstado() : "null") +
                ", dataEntrega=" + dataEntrega +
                ", emprestadoPara=" + (emprestadoPara != null ? emprestadoPara.getNome() : "nenhum") +
                '}';
    }
}
