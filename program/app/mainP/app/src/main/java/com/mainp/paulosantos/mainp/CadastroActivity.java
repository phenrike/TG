package com.mainp.paulosantos.mainp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    public Usuario usuario;
    public EditText etLogin;
    public EditText etSenha;
    public EditText etNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etNome = (EditText) findViewById(R.id.etNome);
        usuario = new Usuario();
    }

    public void cadastrar(View v) {

        if ((!etLogin.getText().toString().isEmpty()) && (!etSenha.getText().toString().isEmpty()) && (!etNome.getText().toString().isEmpty())) {
            usuario.setLogin(etLogin.getText().toString());
            usuario.setSenha(etSenha.getText().toString());
            usuario.setNome(etNome.getText().toString());

            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Requisicao requisicao = new Requisicao();
                requisicao.cadastrar(usuario);
                requisicao.logar(usuario.getLogin().toString(), usuario.getSenha().toString(), CadastroActivity.this);
            }

        } else {
            mostrarMensagem("Preencha todos os campos de cadastro!");
        }
    }

    public void cancelar(View v) {
        Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(it);
    }

    public void mostrarMensagem(String s) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(s);
        dlgAlert.setTitle("Atenção!");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}