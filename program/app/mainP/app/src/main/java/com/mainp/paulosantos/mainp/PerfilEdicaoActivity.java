package com.mainp.paulosantos.mainp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;

public class PerfilEdicaoActivity extends AppCompatActivity {

    public Usuario usuario;
    public Usuario usuarioAtualizado;
    public EditText etFace;
    public EditText etWpp;
    public EditText etInsta;
    public EditText etSnap;
    public EditText etTwitter;
    public EditText etSite;
    public EditText etEmail;
    public EditText etNome;
    public Button btAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_edicao);

        usuario = (Usuario) this.getIntent().getSerializableExtra("usuario");
        usuarioAtualizado = usuario;

        btAlterar = (Button) findViewById(R.id.btAlterar);
        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(Intent.createChooser(new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), "Selecione uma imagem"), 123);
            }
        });

        etFace = (EditText) findViewById(R.id.etFace);
        etWpp = (EditText) findViewById(R.id.etWpp);
        etInsta = (EditText) findViewById(R.id.etInsta);
        etSnap = (EditText) findViewById(R.id.etSnap);
        etTwitter = (EditText) findViewById(R.id.etTwitter);
        etSite = (EditText) findViewById(R.id.etSite);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etNome = (EditText) findViewById(R.id.etNome);

        etFace.setText(usuario.getFace());
        etWpp.setText(usuario.getWpp());
        etInsta.setText(usuario.getInsta());
        etSnap.setText(usuario.getSnap());
        etTwitter.setText(usuario.getTwitter());
        etSite.setText(usuario.getLink());
        etEmail.setText(usuario.getEmail());
        etNome.setText(usuario.getNome());
    }

    public void salvar(View v) {
        usuarioAtualizado.setFace(etFace.getText().toString());
        usuarioAtualizado.setWpp(etWpp.getText().toString());
        usuarioAtualizado.setInsta(etInsta.getText().toString());
        usuarioAtualizado.setSnap(etSnap.getText().toString());
        usuarioAtualizado.setTwitter(etTwitter.getText().toString());
        usuarioAtualizado.setLink(etSite.getText().toString());
        usuarioAtualizado.setEmail(etEmail.getText().toString());
        usuarioAtualizado.setNome(etNome.getText().toString());

        Requisicao requisicao = new Requisicao();

        if (requisicao.atualizarPerfil(usuario)) {
            mostrarMensagem("Seu perfil atualizado com sucesso.", usuarioAtualizado);
        } else {
            mostrarMensagem("Falha ao atualizar o perfil. Tente novamente!", usuario);
        }
    }

    public void cancelar(View v) {
        voltarParaPerfil(usuario);
    }

    public void mostrarMensagem(String s, Usuario u) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(s);
        dlgAlert.setTitle(u.getNome());
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        voltarParaPerfil(u);
    }

    public void voltarParaPerfil(Usuario u) {
        //Cria tela principal do app
        Intent mainActivity = new Intent(this, MainActivity.class);

        //Abre a tela principal do app passando o usuário
        mainActivity.putExtra("usuario", (Serializable) usuario);
        startActivity(mainActivity);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 123){
                Uri imagemSelecionada = data.getData();
                ImageView ivFoto = (ImageView) findViewById(R.id.ivFoto);
                ivFoto.setImageURI(imagemSelecionada);
            }
        }
    }
}