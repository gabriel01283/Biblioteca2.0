public class FuncionarioFactory extends UsuarioFactory{

    @Override
    public Usuario criarUsuario(String nome){

        return new Funcionario(nome);
    }
}
