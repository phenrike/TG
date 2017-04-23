package com.mainp.paulosantos.mainp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logar(View v) {
        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Requisicao requisicao = new Requisicao();
                EditText etLogin = (EditText) findViewById(R.id.etLogin);
                EditText etSenha = (EditText) findViewById(R.id.etSenha);
                TextView tvMensagem = (TextView) findViewById(R.id.tvMensagem);

                if ((!etLogin.getText().toString().isEmpty()) && (!etSenha.getText().toString().isEmpty())) {
                    tvMensagem.setVisibility(View.VISIBLE);
                    tvMensagem.setText(requisicao.logar(etLogin.getText().toString(), etSenha.getText().toString(), LoginActivity.this));
                } else {
                    tvMensagem.setVisibility(View.VISIBLE);
                    tvMensagem.setText("Preencha o Login e a Senha!");
                }
            }
        } catch (Exception e) {
            Log.e("LoginActivity", "Falha ao tentar realizar o login.", e);
        }
    }

    public void cadastrar(View v) {
        Intent it = new Intent(this, CadastroActivity.class);
        startActivity(it);
    }

    public void onBackPressed() {
        finish();
    }
}