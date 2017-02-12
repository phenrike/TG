package com.mainp.paulosantos.mainp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo Santos on 11/02/2017.
 */
public class Operacoes {



    public void logar(Activity activity, String login, String senha) throws UnsupportedEncodingException, JSONException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.0.17/WebAPIMainP/token");
        JSONObject jObect = null;

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("grant_type", "password"));
        pairs.add(new BasicNameValuePair("username", login));
        pairs.add(new BasicNameValuePair("password", senha));
        httppost.setEntity(new UrlEncodedFormEntity(pairs));

        try {
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                String retSrc = EntityUtils.toString(entity);
                // parsing JSON
                jObect = new JSONObject(retSrc); //Convert String to JSON Object
            }
        } catch (Exception e) {
            Log.e("Classe Operacoes.java", "Falha ao acessar a Web API", e);
        }

        if(jObect.has("access_token")){

            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.17/WebAPIMainP/api/mainp/usuarios/"+login+"/"+senha);
            String accessToken = jObect.getString("access_token");
            httppost.setHeader("Authorization", "bearer " + accessToken);

            JSONObject jUsuario = null;

            try {
                HttpResponse response = httpclient.execute(httppost);

                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    String retSrc = EntityUtils.toString(entity);
                    // parsing JSON
                    jUsuario = new JSONObject(retSrc); //Convert String to JSON Object

                    if(jUsuario.has("id")){
                        Intent mainActivity = new Intent(activity, MainActivity.class);
                        Usuario usuario = new Usuario();
                        usuario.carregarUsuario(jUsuario);
                        mainActivity.putExtra("usuario", (Serializable) usuario);
                        activity.startActivity(mainActivity);
                    }

                }
            } catch (Exception e) {
                Log.e("Classe Operacoes.java", "Falha ao Carregar perfil", e);
            }

        }else{
            Intent telaLogin = new Intent(activity, LoginActivity.class);
            String msg = "O login ou a senha é inválido!";
            telaLogin.putExtra("msg", msg);
            activity.startActivity(telaLogin);
        }
    }

    public void buscar(int rede, String buscar,String token){

    }
}
