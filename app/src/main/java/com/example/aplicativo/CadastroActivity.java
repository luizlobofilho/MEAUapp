package com.example.aplicativo;

import android.app.Activity;
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

public class CadastroActivity extends Activity {

    private EditText edt_Email_Cadastro;
    private EditText edt_Senha_Cadastro;
    private Button btnCadastrar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        InicializaObjetos();
        eventoCadastro();
    }
    public void InicializaObjetos(){

        edt_Email_Cadastro = (EditText) findViewById(R.id.edt_Email_Cadastro);
        edt_Senha_Cadastro = (EditText) findViewById(R.id.edt_Senha_Cadastro);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
    }
    public void eventoCadastro(){
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_Email_Cadastro.getText().toString().trim();
                String senha = edt_Senha_Cadastro.getText().toString().trim();
                criarUser(email,senha);
            }
        });
    }

    private void criarUser(String email,String senha){
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alert("Usuário cadastrado com sucesso!!");
                    Intent i = new Intent(CadastroActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    alert("Erro de cadastro");
                }

            }
        });
    }

    private void alert(String msg){
        Toast.makeText(CadastroActivity.this,msg,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
