package com.mainp.paulosantos.mainp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    public Usuario usuario;
    public EditText etLogin;
    public EditText etSenha;
    public EditText etNome;
    public boolean bCadastrou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etNome = (EditText) findViewById(R.id.etNome);
        usuario = new Usuario();
        bCadastrou = false;
    }

    public void cadastrar(View v) {

        if ((!etLogin.getText().toString().isEmpty()) && (!etSenha.getText().toString().isEmpty()) && (!etNome.getText().toString().isEmpty())) {


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Requisicao requisicao = new Requisicao();
            boolean loginExiste = requisicao.verificarExistenciaLogin(etLogin.getText().toString());

            if (!loginExiste) {
                usuario.setId(0);
                usuario.setLogin(etLogin.getText().toString());
                usuario.setSenha(etSenha.getText().toString());
                usuario.setNome(etNome.getText().toString());
                usuario.setDtinscricao("");

                 bCadastrou = requisicao.cadastrar(usuario);

                if (bCadastrou) {
                    mostrarMensagem("Bem-vindo!", "Seu cadastro foi realizado com sucesso.");
                } else {
                    mostrarMensagem("Atenção!", "O cadastro não foi realizado. Tente novamente.");
                }
            } else {
                mostrarMensagem("Altere o login!", "O login digitado já está sendo usado.");
            }
        } else {
            mostrarMensagem("Atenção!", "Preencha todos os campos do cadastro.");
        }
    }

    public void cancelar(View v) {
        voltarParaTelaLogin();
    }

    public void voltarParaTelaLogin() {
        Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(it);
    }

    public void mostrarMensagem(String titulo, String mensagem) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(mensagem);
        dlgAlert.setTitle(titulo);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(bCadastrou)
                            voltarParaTelaLogin();
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}