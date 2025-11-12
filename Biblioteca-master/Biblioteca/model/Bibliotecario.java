public class Bibliotecario implements Usuario{
    private final String nome;
    private final String tipo;


    public Bibliotecario(String nome){
        this.nome = nome;
        this.tipo = "Bibliotecario";
    }

    @Override
    public void mostrarTipo(){

        System.out.println("Sou um bibliotecario!");
    }

    @Override
    public String getNome(){

        return nome;
    }

    @Override
    public String getTipo(){

        return tipo;
    }

    @Override
    public String toString(){

        return tipo + ": " + nome;
    }
}
