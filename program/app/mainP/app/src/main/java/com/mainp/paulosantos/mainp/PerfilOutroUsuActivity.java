package com.mainp.paulosantos.mainp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_outro_usu);

        Usuario usuario = (Usuario) this.getIntent().getSerializableExtra("usuario");

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

        if (usuario.getNOME() != null) {
            tvNome.setText(usuario.getNOME());
        }
        if (usuario.getFACE() != null) {
            tvFace.setText(usuario.getFACE());
        }
        if (usuario.getWPP() != null) {
            tvWpp.setText(usuario.getWPP());
        }
        if (usuario.getINSTA() != null) {
            tvInsta.setText(usuario.getINSTA());
        }
        if (usuario.getSNAP() != null) {
            tvSnap.setText(usuario.getSNAP());
        }
        if (usuario.getTWITTER() != null) {
            tvTwitter.setText(usuario.getTWITTER());
        }
        if (usuario.getEMAIL() != null) {
            tvEmail.setText(usuario.getEMAIL());
        }
        if (usuario.getLINK() != null) {
            tvLink.setText(usuario.getLINK());
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

                /*if (URLUtil.isValidUrl(tvFace.getText().toString())) {

                } else {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getApplication());
                    dlgAlert.setMessage("O link do Facebook é inválido!");
                    dlgAlert.setTitle("App Title");
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }*/

                abrirUrlNoNavegador(tvFace.getText().toString());
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

    public String substituirCaracteresEspeciais(String texto) {
        String[] caracteresEspeciais = {"!", "#", "$", "%", "¨", "&", "*", "(", ")", "'", "+"};
        String[] espacos = {"       ", "      ", "     ", "    ", "   ", "  ", " "};

        for (String ce : caracteresEspeciais)
            texto = texto.replace(ce, "");

        for (String e : espacos)
            texto = texto.replace(e, "");

        return texto;
    }
}