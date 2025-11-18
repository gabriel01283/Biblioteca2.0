public class AlunoFactory extends  UsuarioFactory{
 // implementa o metodo usuario criando um Aluno
    @Override
    public Usuario criarUsuario(String nome){

        return new Aluno(nome);
    }
}
