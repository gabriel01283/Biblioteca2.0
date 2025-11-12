
import java.util.Date;

public class Livro {


    private String titulo;
    private String genero;
    private String status;      // Ex: "Disponível", "Emprestado"
    private Date dataEntrega;   // Data prevista para devolução


    public Livro(String titulo, String genero, String status, Date dataEntrega) {
        this.titulo = titulo;
        this.genero = genero;
        this.status = status;
        this.dataEntrega = dataEntrega;
    }
    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getGenero() {

        return genero;
    }

    public void setGenero(String genero) {

        this.genero = genero;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public Date getDataEntrega() {

        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {

        this.dataEntrega = dataEntrega;
    }

  
    @Override
    public String toString() {
        return "Livro {" +
                "Título='" + titulo + '\'' +
                ", Gênero='" + genero + '\'' +
                ", Status='" + status + '\'' +
                ", Data de Entrega=" + dataEntrega +
                '}';
    }
}
