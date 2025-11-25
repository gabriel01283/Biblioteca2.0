public abstract class UsuarioFactory {
// classe generica para criar os usuários
    public abstract Usuario criarUsuario(String nome);// as subclasses vão dizer qual o tipo de usuário

//   O criar usuario defini qual o tipo de usuário
    public void registrarUsuario(String nome){
        Usuario u = criarUsuario(nome);
        System.out.println("Registrando novo usuário...");
        u.mostrarTipo();
    }
}
