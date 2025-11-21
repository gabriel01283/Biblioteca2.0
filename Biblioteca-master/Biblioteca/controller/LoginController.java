public class LoginController {

    public boolean autenticar(String cpf, String senhaDigitada) {

        Bibliotecario b = Bibliotecario.getInstancia(
                biblioDados.nome,
                biblioDados.cpf,
                biblioDados.senhaCriptografada
        );

        String senhaCriptografada = Criptografia.criptografar(senhaDigitada);

        boolean cpfValido = b.getCpf().equals(cpf);
        boolean senhaValida = b.getSenhaCriptografada().equals(senhaCriptografada);

        return cpfValido && senhaValida;
    }
}
