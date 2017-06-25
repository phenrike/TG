package com.mainp.paulosantos.mainp;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PerfilOutroUsuActivity extends AppCompatActivity {

    public TextView tvNome;
    public TextView tvFace;
    public TextView tvWpp;
    public TextView tvInsta;
    public TextView tvSnap;
    public TextView tvTwitter;
    public TextView tvEmail;
    public TextView tvLink;
    public ImageButton btCopiarFb;
    public ImageButton btBrowseFace;
    public ImageButton btCopiarNumero;
    public ImageButton btCopiarInsta;
    public ImageButton btBrowseInta;
    public ImageButton btCopiarSnap;
    public ImageButton btCopiarTwitter;
    public ImageButton btBrowseTwitter;
    public ImageButton btCopiarSite;
    public ImageButton btBrowseSite;
    public ImageButton btCopiarEmail;
    public Usuario receptor;
    public Usuario emissor;
    public Requisicao requisicao;
    public ImageView iv1;
    public ImageView iv2;
    public ImageView iv3;
    public ImageView iv4;
    public ImageView iv5;
    public ImageView iv6;
    public ImageView iv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_outro_usu);

        receptor = (Usuario) this.getIntent().getSerializableExtra("receptor");
        emissor = (Usuario) this.getIntent().getSerializableExtra("emissor");

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvFace = (TextView) findViewById(R.id.tvFace);
        tvWpp = (TextView) findViewById(R.id.tvWpp);
        tvInsta = (TextView) findViewById(R.id.tvInsta);
        tvSnap = (TextView) findViewById(R.id.tvSnap);
        tvTwitter = (TextView) findViewById(R.id.tvTwitter);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvLink = (TextView) findViewById(R.id.tvLink);

        btCopiarFb = (ImageButton) findViewById(R.id.btCopiarFb);
        btBrowseFace = (ImageButton) findViewById(R.id.btBrowseFace);
        btCopiarNumero = (ImageButton) findViewById(R.id.btCopiarNumero);
        btCopiarInsta = (ImageButton) findViewById(R.id.btCopiarInsta);
        btBrowseInta = (ImageButton) findViewById(R.id.btBrowseInta);
        btCopiarSnap = (ImageButton) findViewById(R.id.btCopiarSnap);
        btCopiarTwitter = (ImageButton) findViewById(R.id.btCopiarTwitter);
        btBrowseTwitter = (ImageButton) findViewById(R.id.btBrowseTwitter);
        btCopiarSite = (ImageButton) findViewById(R.id.btCopiarSite);
        btBrowseSite = (ImageButton) findViewById(R.id.btBrowseSite);
        btCopiarEmail = (ImageButton) findViewById(R.id.btCopiarEmail);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);
        iv6 = (ImageView) findViewById(R.id.iv6);
        iv7 = (ImageView) findViewById(R.id.iv7);

        tvNome.setText("");
        tvFace.setText("");
        tvWpp.setText("");
        tvInsta.setText("");
        tvSnap.setText("");
        tvTwitter.setText("");
        tvEmail.setText("");
        tvLink.setText("");

        tvNome.setText(receptor.getNome());

        for (Username un : receptor.getUsernames())
        {
            String nomeusuario = "";

            if(un.getPrivado()){
                nomeusuario = "Privado";
            }else{
                nomeusuario = un.getNomeusuario();
            }

            switch (un.getIdredesocial().nome)
            {
                case "FACEBOOK":
                    tvFace.setText(nomeusuario);
                    if(un.getPrivado()){
                        iv1.setVisibility(View.VISIBLE);
                        btCopiarFb.setVisibility(View.INVISIBLE);
                        btBrowseFace.setVisibility(View.INVISIBLE);
                    }
                    break;

                case "WHATSAPP":
                    tvWpp.setText(nomeusuario);
                    if(un.getPrivado()){
                        iv2.setVisibility(View.VISIBLE);
                        btCopiarNumero.setVisibility(View.INVISIBLE);
                    }
                    break;

                case "INSTAGRAM":
                    tvInsta.setText(nomeusuario);
                    if(un.getPrivado()){
                        iv3.setVisibility(View.VISIBLE);
                        btBrowseInta.setVisibility(View.INVISIBLE);
                        btCopiarInsta.setVisibility(View.INVISIBLE);
                    }
                    break;

                case "SNAPCHAT":
                    tvSnap.setText(nomeusuario);
                    if(un.getPrivado()){
                        iv4.setVisibility(View.VISIBLE);
                        btCopiarSnap.setVisibility(View.INVISIBLE);
                    }
                    break;

                case "TWITTER":
                    tvTwitter.setText(nomeusuario);
                    if(un.getPrivado()){
                        iv5.setVisibility(View.VISIBLE);
                        btBrowseTwitter.setVisibility(View.INVISIBLE);
                        btCopiarTwitter.setVisibility(View.INVISIBLE);
                    }
                    break;

                case "EMAIL":
                    tvEmail.setText(nomeusuario);
                    if(un.getPrivado()){
                        iv7.setVisibility(View.VISIBLE);
                        btCopiarEmail.setVisibility(View.INVISIBLE);
                    }
                    break;

                case "LINK":
                    tvLink.setText(nomeusuario);
                    if(un.getPrivado()){
                        iv6.setVisibility(View.VISIBLE);
                        btBrowseSite.setVisibility(View.INVISIBLE);
                        btCopiarSite.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }

        btCopiarFb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copiarTexto(tvFace);
            }
        });

        btCopiarNumero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copiarTexto(tvWpp);
            }
        });

        btCopiarInsta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copiarTexto(tvInsta);
            }
        });

        btCopiarSnap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copiarTexto(tvSnap);
            }
        });

        btCopiarTwitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copiarTexto(tvTwitter);
            }
        });

        btCopiarSite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copiarTexto(tvLink);
            }
        });

        btCopiarEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copiarTexto(tvEmail);
            }
        });

        btBrowseFace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.fb.com/" + removerCaracteresEspeciais(tvFace.getText().toString());
                abrirUrlNoNavegador(url);
            }
        });

        btBrowseSite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = tvLink.getText().toString();
                abrirUrlNoNavegador(url);
            }
        });

        btBrowseInta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.instagram.com/" + removerCaracteresEspeciais(tvInsta.getText().toString());
                abrirUrlNoNavegador(url);
            }
        });

        btBrowseTwitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.twitter.com/" + removerCaracteresEspeciais(tvTwitter.getText().toString());
                abrirUrlNoNavegador(url);
            }
        });
    }

    public void copiarTexto(TextView tv) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", tv.getText());
        clipboard.setPrimaryClip(clip);
    }

    public void abrirUrlNoNavegador(String url) {

        boolean bURLvalida = URLUtil.isValidUrl(url);

        if(bURLvalida){
            Uri uri = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            startActivity(intent);
        }else {
            mostrarMensagem("IFY", "O endereço do site é inválido.");
        }
    }

    public String removerCaracteresEspeciais(String texto) {
        String[] caracteresEspeciais = {"!", "#", "$", "%", "¨", "&", "*", "(", ")", "'", "+", "@"};
        String[] espacos = {"       ", "      ", "     ", "    ", "   ", "  ", " "};

        for (String ce : caracteresEspeciais)
            texto = texto.replace(ce, "");

        for (String e : espacos)
            texto = texto.replace(e, "");

        return texto;
    }

    public void compartilhar(View v) {
        try {
            requisicao = new Requisicao();

            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Você deseja compartilhar o seu perfil com " + receptor.getNome() + " ?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            boolean bCompartilhou = requisicao.compartilhar(emissor, receptor);
                            if (bCompartilhou) {
                                mostrarMensagem("IFY", emissor.getNome() + " seu perfil foi compartilhado.");
                            }
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            builder.create().show();
        } catch (Exception e) {
            Log.e("", "Falha ao tentar compartilhar o perfil.", e);
        }
    }

    public void mostrarMensagem(String titulo, String msg) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle(titulo);
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