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
public class Requisicao {

    public static String tokenDeAcesso;
    public static String ip = "192.168.0.11";

    private void gerarTokenDeAcesso(String login, String senha) throws UnsupportedEncodingException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://"+ip+"/WebAPIMainP/token");
        List<NameValuePair> pairs = new ArrayList<>();
        JSONObject jsonResultado;
        String stringResultado;
        HttpResponse response;
        HttpEntity entity;

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
                    tokenDeAcesso = jsonResultado.getString("access_token");
                } else {
                    tokenDeAcesso = "";
                }
            }
        } catch (Exception e) {
            Log.e("gerarTokenDeAcesso", "Falha ao tentar gerar o token.", e);
        }
    }

    public String logar(String login, String senha, Activity activity) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://"+ip+"/WebAPIMainP/api/mainp/usuarios/" + login + "/" + senha);
        JSONObject jsonUsuario;
        String stringResposta, mensagem = "";
        HttpResponse response;
        HttpEntity entity;

        try {

            gerarTokenDeAcesso(login, senha);

            //Verifica se um token foi gerado e realiza o login
            if ((!tokenDeAcesso.equals("")) && (!tokenDeAcesso.equals(null))) {

                //Monta cabeçalho da requisição
                httppost.setHeader("Authorization", "bearer " + tokenDeAcesso);

                //Executa a requisição
                response = httpclient.execute(httppost);
                entity = response.getEntity();

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
            } else {
                mensagem = "Login incorreto e/ou Senha incorreta!";
            }
        } catch (Exception e) {
            Log.e("Requisicao.java logar", "Falha ao tentar executar o login.", e);
        }

        return mensagem;
    }

    public JSONArray buscar(int indexDaRedeSocial, String conteudoDaBusca) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://"+ip+"/WebAPIMainP/api/mainp/usuarios/" + Integer.toString(indexDaRedeSocial) + "/" + conteudoDaBusca);
        JSONArray jsonArrayPerfis = null;
        String stringResposta;
        HttpResponse response;
        HttpEntity entity;

        try {
            httpget.setHeader("Authorization", "bearer " + tokenDeAcesso);
            response = httpclient.execute(httpget);
            entity = response.getEntity();

            if (entity != null) {
                stringResposta = EntityUtils.toString(entity);

                //Verifica se a resposta contém usuários e retorna um json array
                if (!stringResposta.equals("[]")) {
                    jsonArrayPerfis = new JSONArray(stringResposta);
                } else {
                    jsonArrayPerfis = null;
                }
            }
        } catch (Exception e) {
            Log.e("Classe Requisicao.java", "Falha ao buscar usuários.", e);
        }

        return jsonArrayPerfis;
    }
}