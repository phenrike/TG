package com.mainp.paulosantos.mainp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class UserPerfilAcvt extends AppCompatActivity implements Runnable{

    private TextView tvName;
    private android.os.Handler handler = new android.os.Handler();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_perfil_acvt);

        tvName = (TextView)findViewById(R.id.tvName);
        onClick(this);

    }

    private void onClick(UserPerfilAcvt userPerfilAcvt) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Obtendo seu perfil, aguarde...");
        dialog.setTitle("mainP");
        dialog.show();

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        String name = tvName.getText().toString();

        try {

            WSmainP ws = new WSmainP();
            final String res = ws.CarregarPerfil();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvName.setText(res);
                }
            });


        }catch (Exception ex){
            Log.e("UserPerfil","Erro",ex);
        }finally {
            dialog.dismiss();
        }


    }
}
