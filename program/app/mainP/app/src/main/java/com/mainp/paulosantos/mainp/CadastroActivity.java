package com.mainp.paulosantos.mainp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button btCadastrar = (Button) findViewById(R.id.btCadastrar);
        Button btCancelar = (Button) findViewById(R.id.btCancelar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

    }
}