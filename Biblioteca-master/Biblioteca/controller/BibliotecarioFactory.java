public class BibliotecarioFactory extends UsuarioFactory{

    @Override
    public Usuario criarUsuario(String nome){

        return new Bibliotecario (nome);
    }
}
