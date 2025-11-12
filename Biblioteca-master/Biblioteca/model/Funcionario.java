public class Funcionario implements Usuario {
    private final String nome;
    private final String tipo;

    public Funcionario(String nome){
        this.nome = nome;
        this.tipo = "Funcionario";
    }

    @Override
    public void mostrarTipo(){

        System.out.println("Sou um funcionario!");
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
