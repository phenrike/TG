package com.mainp.paulosantos.mainp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PerfilOutroUsuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_outro_usu);

        Usuario usuario = (Usuario) this.getIntent().getSerializableExtra("usuario");

        TextView tvNome = (TextView) findViewById(R.id.tvNome);
        TextView tvFace = (TextView) findViewById(R.id.tvFace);
        TextView tvWpp = (TextView) findViewById(R.id.tvWpp);
        TextView tvInsta = (TextView) findViewById(R.id.tvInsta);
        TextView tvSnap = (TextView) findViewById(R.id.tvSnap);
        TextView tvTwitter = (TextView) findViewById(R.id.tvTwitter);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        TextView tvLink = (TextView) findViewById(R.id.tvLink);
        Button btSair = (Button) findViewById(R.id.btSair);

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
    }
}