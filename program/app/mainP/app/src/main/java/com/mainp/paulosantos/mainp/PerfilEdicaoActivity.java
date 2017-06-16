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
import android.widget.CheckBox;
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
    public CheckBox cbFace;
    public CheckBox cbWpp;
    public CheckBox cbInsta;
    public CheckBox cbSnap;
    public CheckBox cbTwitter;
    public CheckBox cbSite;
    public CheckBox cbEmail;

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
        cbFace = (CheckBox) findViewById(R.id.cbFace);
        cbWpp = (CheckBox) findViewById(R.id.cbWpp);
        cbInsta = (CheckBox) findViewById(R.id.cbInsta);
        cbSnap = (CheckBox) findViewById(R.id.cbSnap);
        cbTwitter = (CheckBox) findViewById(R.id.cbTwitter);
        cbSite = (CheckBox) findViewById(R.id.cbSite);
        cbEmail = (CheckBox) findViewById(R.id.cbEmail);

        etNome.setText(usuario.getNome());

        for (Username un : usuario.getUsernames())
        {
            switch (un.getIdredesocial().nome)
            {
                case "FACEBOOK":
                    etFace.setText(un.getNomeusuario());
                    cbFace.setChecked(un.getPrivado());
                    break;

                case "WHATSAPP":
                    etWpp.setText(un.getNomeusuario());
                    cbWpp.setChecked(un.getPrivado());
                    break;

                case "INSTAGRAM":
                    etInsta.setText(un.getNomeusuario());
                    cbInsta.setChecked(un.getPrivado());
                    break;

                case "SNAPCHAT":
                    etSnap.setText(un.getNomeusuario());
                    cbSnap.setChecked(un.getPrivado());
                    break;

                case "TWITTER":
                    etTwitter.setText(un.getNomeusuario());
                    cbTwitter.setChecked(un.getPrivado());
                    break;

                case "EMAIL":
                    etEmail.setText(un.getNomeusuario());
                    cbEmail.setChecked(un.getPrivado());
                    break;

                case "LINK":
                    etSite.setText(un.getNomeusuario());
                    cbSite.setChecked(un.getPrivado());
                    break;
            }
        }
    }

    public void salvar(View v) {

        for (Username un : usuarioAtualizado.getUsernames())
        {
            switch (un.getIdredesocial().nome)
            {
                case "FACEBOOK":
                    un.setNomeusuario(substituirCaracteresEspeciais(etFace.getText().toString()));
                    un.setPrivado(cbFace.isChecked());
                    break;

                case "WHATSAPP":
                    un.setNomeusuario(substituirCaracteresEspeciais(etWpp.getText().toString()));
                    un.setPrivado(cbWpp.isChecked());
                    break;

                case "INSTAGRAM":
                    un.setNomeusuario(substituirCaracteresEspeciais(etInsta.getText().toString()));
                    un.setPrivado(cbInsta.isChecked());
                    break;

                case "SNAPCHAT":
                    un.setNomeusuario(substituirCaracteresEspeciais(etSnap.getText().toString()));
                    un.setPrivado(cbSnap.isChecked());
                    break;

                case "TWITTER":
                    un.setNomeusuario(substituirCaracteresEspeciais(etTwitter.getText().toString()));
                    un.setPrivado(cbTwitter.isChecked());
                    break;

                case "EMAIL":
                    un.setNomeusuario(substituirCaracteresEspeciais(etEmail.getText().toString()));
                    un.setPrivado(cbEmail.isChecked());
                    break;

                case "LINK":
                    un.setNomeusuario(substituirCaracteresEspeciais(etSite.getText().toString()));
                    un.setPrivado(cbSite.isChecked());
                    break;
            }
        }

        usuarioAtualizado.setNome(etNome.getText().toString());

        Requisicao requisicao = new Requisicao();

        if (requisicao.atualizarPerfil(usuario)) {
            mostrarMensagem("Seu perfil atualizado com sucesso.");
        } else {
            usuarioAtualizado = usuario;
            mostrarMensagem("Falha ao atualizar o perfil. Tente novamente!");
        }
    }

    public void cancelar(View v) {
        voltarParaPerfil(usuario);
    }

    public void mostrarMensagem(String s) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(s);
        dlgAlert.setTitle(usuarioAtualizado.getNome());
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        voltarParaPerfil(usuarioAtualizado);
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
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

    public String substituirCaracteresEspeciais(String username) {
        String[] caracteresEspeciais = {"!", "#", "$", "%", "¨", "&", "*", "(", ")", "'", "-", "+"};
        String[] espacos = {"       ", "      ", "     ", "    ", "   ", "  ", " "};

        for (String ce : caracteresEspeciais)
            username = username.replace(ce, "");

        for (String e : espacos)
            username = username.replace(e, "");

        return username;
    }
}