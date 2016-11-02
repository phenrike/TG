package com.mainp.paulosantos.mainp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PerfilUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

            JSONObject usuario;
            WebService ws = new WebService();
            usuario = ws.carregarPerfil();
            String nome="";
            String fb="";
            String tt="";
            String snap="";
            String insta="";
            String wpp="";
            String link="";
            try {
                nome = usuario.getString("NOME").toString();
                fb = usuario.getString("FACE").toString();
                tt = usuario.getString("TWITTER").toString();
                snap = usuario.getString("SNAP").toString();
                insta = usuario.getString("INSTA").toString();
                wpp = usuario.getString("WPP").toString();
                link = usuario.getString("LINK").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            TextView tvNome;
            TextView tvFb;
            TextView tvTt;
            TextView tvSnap;
            TextView tvInsta;
            TextView tvWpp;
            TextView tvLink;

            tvNome = (TextView) findViewById(R.id.tvNome);
            tvFb = (TextView) findViewById(R.id.tvFb);
            tvInsta = (TextView) findViewById(R.id.tvInsta);
            tvLink = (TextView) findViewById(R.id.tvLink);
            tvSnap = (TextView) findViewById(R.id.tvSnap);
            tvTt = (TextView) findViewById(R.id.tvTt);
            tvWpp = (TextView) findViewById(R.id.tvWpp);

            tvNome.setText(nome);
            tvFb.setText(fb);
            tvInsta.setText(insta);
            tvLink.setText(link);
            tvSnap.setText(snap);
            tvTt.setText(tt);
            tvWpp.setText(wpp);
        }
    }
}
