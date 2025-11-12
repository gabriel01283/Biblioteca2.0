public abstract class UsuarioFactory {

    public abstract Usuario criarUsuario(String nome);

    public void registrarUsuario(String nome){
        Usuario u = criarUsuario(nome);
        System.out.println("Redistrando novo usuário...");
        u.mostrarTipo();
    }
}
