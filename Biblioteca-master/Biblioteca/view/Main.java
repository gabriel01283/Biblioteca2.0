public class Main {
    public static void main(String[] args) {
        // Criando um Aluno via Factory
        UsuarioFactory f1 = new AlunoFactory();
        Usuario aluno = f1.criarUsuario("Gabriel");
        aluno.mostrarTipo();

        // Criando um Professor via Factory
        UsuarioFactory f2 = new FuncionarioFactory();
        Usuario prof = f2.criarUsuario("Carla");
        prof.mostrarTipo();

        // Mostrando os objetos
        System.out.println(aluno);
        System.out.println(prof);
    }
}

