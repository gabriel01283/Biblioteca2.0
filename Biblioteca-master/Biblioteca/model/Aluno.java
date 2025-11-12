public class Aluno implements Usuario{
    private final String nome;
    private final String tipo;

    public Aluno(String nome){
        this.nome = nome;
        this.tipo = "Aluno";
    }

    @Override
    public void mostrarTipo(){

        System.out.println("Sou um aluno!");
    }
    @Override
    public void separador(){
        System.out.println("#####################");
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
