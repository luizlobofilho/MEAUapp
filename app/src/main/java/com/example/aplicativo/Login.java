package com.example.aplicativo;



public class Login {


    private static final String USUARIO = "CATDOG";
    private static final String SENHA = "123456";

    private String Usuario;
    private String Senha;

    public String getUsuario()
    {
        return Usuario;
    }
    public void setUsuario(String usuario)
    {
        Usuario = usuario;
    }
    public String getSenha()
    {
        return Senha;
    }
    public void setSenha(String senha)
    {
        Senha = senha;
    }

    public Login()
    {

    }

    public boolean ValidarUsuario()
    {
        if (Usuario.equals(""))
        {
            return false;
        }
        else if (Senha.equals(""))
        {
            return false;
        }
        else if (!Usuario.equals(USUARIO) || !Senha.equals(SENHA))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
