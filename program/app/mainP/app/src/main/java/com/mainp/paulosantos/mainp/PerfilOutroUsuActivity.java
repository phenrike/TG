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
import android.widget.ImageButton;
import android.widget.TextView;

public class PerfilOutroUsuActivity extends AppCompatActivity {

    TextView tvNome;
    TextView tvFace;
    TextView tvWpp;
    TextView tvInsta;
    TextView tvSnap;
    TextView tvTwitter;
    TextView tvEmail;
    TextView tvLink;
    ImageButton btCopiarFb;
    ImageButton btBrowseFace;
    ImageButton btCopiarNumero;
    ImageButton btCopiarInsta;
    ImageButton btBrowseInta;
    ImageButton btCopiarSnap;
    ImageButton btCopiarTwitter;
    ImageButton btBrowseTwitter;
    ImageButton btCopiarSite;
    ImageButton btBrowseSite;
    ImageButton btCopiarEmail;
    Usuario receptor;
    Usuario emissor;
    Requisicao requisicao;

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

        if (receptor.getNome() != null) {
            tvNome.setText(receptor.getNome());
        }
        if (receptor.getFace() != null) {
            tvFace.setText(receptor.getFace());
        }
        if (receptor.getWpp() != null) {
            tvWpp.setText(receptor.getWpp());
        }
        if (receptor.getInsta() != null) {
            tvInsta.setText(receptor.getInsta());
        }
        if (receptor.getSnap() != null) {
            tvSnap.setText(receptor.getSnap());
        }
        if (receptor.getTwitter() != null) {
            tvTwitter.setText(receptor.getTwitter());
        }
        if (receptor.getEmail() != null) {
            tvEmail.setText(receptor.getEmail());
        }
        if (receptor.getLink() != null) {
            tvLink.setText(receptor.getLink());
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
                String url = removerCaracteresEspeciais(tvLink.getText().toString());
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
        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
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