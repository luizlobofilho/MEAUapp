package com.example.aplicativo;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity {
    private EditText edtUsuario;
    private EditText edtSenha;
    private Button btnLogin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        InicializaObjetos();
        eventoLogin();
    }

    public void InicializaObjetos()
    {
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    public void eventoLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtUsuario.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();
                login(email,senha);
            }
        });
    }

    private void login(String email,String senha){
        auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                }else{
                    alert("E-mail ou senha inválidos, tente novamente!");
                }
            }
        });
    }

    private void alert(String msg){
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    private void MensagemInformativa(String mensagen) {
        new AlertDialog.Builder(this)
                .setTitle("MEAU App")
                .setMessage(mensagen)
                .setPositiveButton("OK", null)
                .setCancelable(false)
                .show();
    }

    /*public void Login(View v)
    {
        Login classe_login = new Login();
        classe_login.setUsuario(edtUsuario.getText().toString());
        classe_login.setSenha(edtSenha.getText().toString());

        if (!classe_login.ValidarUsuario())
        {
            MensagemInformativa("Dados Incorretos, Favor preenchê-los corretamente!");
        }
        else
        {
            Intent it = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(it);
        }
    }*/

    public void chamarCadastro(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }



}
