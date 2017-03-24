package com.mainp.paulosantos.mainp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btEntrar = (Button) findViewById(R.id.btEntrar);

        assert btEntrar != null;
        btEntrar.setOnClickListener(this);
    }

    public void onClick(View v) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Operacoes operacoes = new Operacoes();
                    EditText etLogin = (EditText) findViewById(R.id.etLogin);
                    EditText etSenha = (EditText) findViewById(R.id.etSenha);
                    TextView tvMensagem = (TextView) findViewById(R.id.tvMensagem);

                    if ((etLogin.getText().toString() != null) && (etSenha.getText().toString() != null)) {
                        tvMensagem.setVisibility(View.VISIBLE);
                        try {
                            tvMensagem.setText(operacoes.gerarTokenDeAcesso(etLogin.getText().toString(), etSenha.getText().toString(), LoginActivity.this));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        tvMensagem.setVisibility(View.VISIBLE);
                        tvMensagem.setText("Preencha o Login e a Senha!");
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }
}