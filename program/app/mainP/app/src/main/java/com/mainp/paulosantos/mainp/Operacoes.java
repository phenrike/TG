package com.mainp.paulosantos.mainp;

import android.app.Activity;
import android.content.Intent;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo Santos on 11/02/2017.
 */
public class Operacoes {

    public String gerarTokenDeAcesso(String login, String senha, Activity activity) throws UnsupportedEncodingException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.0.11/WebAPIMainP/token");
        List<NameValuePair> pairs = new ArrayList<>();
        JSONObject jsonResultado;
        HttpResponse response;
        HttpEntity entity;
        String stringResultado;
        String mensagem = "";

        try {
            //Monta cabeçalho da requisição
            pairs.add(new BasicNameValuePair("grant_type", "password"));
            pairs.add(new BasicNameValuePair("username", login));
            pairs.add(new BasicNameValuePair("password", senha));
            httppost.setEntity(new UrlEncodedFormEntity(pairs));

            //Executa a requisição
            response = httpclient.execute(httppost);
            entity = response.getEntity();

            //Verifica a resposta
            if (entity != null) {
                stringResultado = EntityUtils.toString(entity);
                jsonResultado = new JSONObject(stringResultado);

                if (jsonResultado.has("access_token")) {
                    this.logar(login, senha, jsonResultado.getString("access_token"), activity);
                    mensagem = "";
                } else {
                    mensagem = "Login ou Senha incorretos!";
                }
            } else {
                mensagem = "Falha ao se comunicar com o servidor, tente novamente!";
            }
        } catch (Exception e) {
            Log.e("gerarTokenDeAcesso", "Falha ao tentar acessar a Web API.", e);
        }

        return mensagem;
    }

    public void logar(String login, String senha, String accessToken, Activity activity) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.0.11/WebAPIMainP/api/mainp/usuarios/" + login + "/" + senha);
        JSONObject jsonUsuario;
        String stringResposta;

        try {
            //Monta cabeçalho da requisição
            httppost.setHeader("Authorization", "bearer " + accessToken);

            //Executa a requisição
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            //Verifica a resposta
            if (entity != null) {
                //Converte a resposta em Json
                stringResposta = EntityUtils.toString(entity);
                jsonUsuario = new JSONObject(stringResposta);

                //Cria tela principal do app
                Intent mainActivity = new Intent(activity, MainActivity.class);

                //Converte o Json para um objeto Usuario
                Usuario usuario = new Usuario();
                usuario.carregarUsuario(jsonUsuario);

                //Abre a tela principal do app passando o usuário
                mainActivity.putExtra("usuario", (Serializable) usuario);
                activity.startActivity(mainActivity);
            }
        } catch (Exception e) {
            Log.e("logar", "Falha ao carregar o usuário", e);
        }
    }

    public JSONArray buscar(int rede, String busca) {

        HttpClient httpclient;
        HttpGet httpget;
        JSONArray perfis;

        perfis = null;
        httpclient = new DefaultHttpClient();
        httpget = new HttpGet("http://192.168.0.11/WebAPIMainP/api/mainp/usuarios/" + Integer.toString(rede) + "/" + busca);
        httpget.setHeader("Authorization", "bearer " + "Ocf8mTzpxo1tuCLaOJB0VAd1qJxbzBafUo9cDVVlOEMCaT0SqEwspKvMRycFsEsNw90KCM0jwDpC2VBzMuHQm6ReUovWk58kvTiPkZaoXYO17JVAaL29DC7x0-z1kNfEFff3U8CXkfIaheaLJubmKrombLJb4iJDCu_BvpFTJjLSg50Aba6B7OsPTbfM0pqxDklhaRFXjXzfkj75hS1-ytdeFBiY8wcUvbUdQnUP6Wo");

        try {
            HttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                String retSrc = EntityUtils.toString(entity);

                perfis = new JSONArray(retSrc);
            } else {
                perfis = null;
            }
        } catch (Exception e) {
            Log.e("Classe Operacoes.java", "Falha ao buscar perfis", e);
        }

        return perfis;
    }
}
