package com.mainp.paulosantos.mainp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btEntrar = (Button) findViewById(R.id.btEntrar);
        TextView tvMsgErro = (TextView) findViewById(R.id.tvMsgErro);

        Intent intent = getIntent();
        String msg = (String) intent.getStringExtra("usuario");

        if(msg != ""){
            tvMsgErro.setText(msg);
            tvMsgErro.setVisibility(View.VISIBLE);
        }

        assert btEntrar != null;
        btEntrar.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        new Thread(new Runnable()

        {
            public void run() {
                Operacoes operacoes = new Operacoes();
                EditText etLogin = (EditText) findViewById(R.id.etLogin);
                EditText etSenha = (EditText) findViewById(R.id.etSenha);
                try {
                    operacoes.logar(LoginActivity.this, etLogin.getText().toString(), etSenha.getText().toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}