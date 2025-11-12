public class AlunoFactory extends  UsuarioFactory{

    @Override
    public Usuario criarUsuario(String nome){

        return new Aluno(nome);
    }


}
