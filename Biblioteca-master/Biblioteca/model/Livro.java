
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Livro {

    private String titulo;
    private String genero;
    private EstadoLivro estado;          // State
    private Date dataEntrega;

    private List<ObservadorLivro> observadores = new ArrayList<>();  // Observer
    //faz uma copia da lista assim evita erros de concorrência

    public Livro(String titulo, String genero, EstadoLivro estadoInicial) {
        this.titulo = titulo;
        this.genero = genero;
        this.estado = estadoInicial;
        this.dataEntrega = null;
    }

    // Métodos que delegam ao Estado
    // Pergunta o estado do livro e verifica se ele esta disponivel ou emprestado, dependendo do resultado gera a data de entrega
    public void emprestar() {
    if (estado != null) {
        estado.emprestar(this);
        gerarDataEntregaAutomatica(7); // gera automaticamente, 7 dias à frente
    }
}

// chama o metodo e verifica o estado atual se a devolução for permitida o status muda para disponível e a data de entrega é apagada
    public void devolver() {
        if (estado != null) {
            estado.devolver(this);
            this.dataEntrega = null;
        }
    }

    // Observer
    public void adicionarObservador(ObservadorLivro obs) {
        if (obs != null && !observadores.contains(obs)) {
            observadores.add(obs);
        }
    }

    public void removerObservador(ObservadorLivro obs) {
        observadores.remove(obs);
    }

    public void notificar(String mensagem) {
        for (ObservadorLivro obs : new ArrayList<>(observadores)) {
            obs.atualizar(this, mensagem);
        }
    }
    public void gerarDataEntregaAutomatica(int dias) {
        Date hoje = new Date();
        long milis = hoje.getTime() + (long) dias * 24 * 60 * 60 * 1000;
        this.dataEntrega = new Date(milis);
}

    // Getters e setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public EstadoLivro getEstado() { return estado; }

    public void setEstado(EstadoLivro estado) {
        this.estado = estado;
        notificar("Estado alterado para: " + estado.getNomeEstado());
    }

    public Date getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(Date dataEntrega) { this.dataEntrega = dataEntrega; }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", estado=" + (estado != null ? estado.getNomeEstado() : "null") +
                ", dataEntrega=" + dataEntrega +
                '}';
    }
}
