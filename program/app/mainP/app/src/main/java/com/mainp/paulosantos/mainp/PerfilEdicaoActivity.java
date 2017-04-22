package com.mainp.paulosantos.mainp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class PerfilEdicaoActivity extends AppCompatActivity {

    public Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_edicao);

        usuario = (Usuario) this.getIntent().getSerializableExtra("usuario");

        EditText etFace = (EditText) findViewById(R.id.etFace);
        EditText etWpp = (EditText) findViewById(R.id.etWpp);
        EditText etInsta = (EditText) findViewById(R.id.etInsta);
        EditText etSnap = (EditText) findViewById(R.id.etSnap);
        EditText etTwitter = (EditText) findViewById(R.id.etTwitter);
        EditText etSite = (EditText) findViewById(R.id.etSite);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);

        etFace.setText(usuario.getFACE());
        etWpp.setText(usuario.getWPP());
        etInsta.setText(usuario.getINSTA());
        etSnap.setText(usuario.getSNAP());
        etTwitter.setText(usuario.getTWITTER());
        etSite.setText(usuario.getLINK());
        etEmail.setText(usuario.getEMAIL());
    }

    public void cancelar(View v) {
        //Cria tela principal do app
        Intent mainActivity = new Intent(this, MainActivity.class);

        //Abre a tela principal do app passando o usuário
        mainActivity.putExtra("usuario", (Serializable) usuario);
        startActivity(mainActivity);
    }
}