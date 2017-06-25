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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Paulo Santos on 11/02/2017.
 */
public class Requisicao {

    public static String tokenDeAcesso;
    public static String ip = "192.168.0.11";

    public String logar(String login, String senha, Activity activity) throws UnsupportedEncodingException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://" + ip + "/WebAPIMainP/token");
        List<NameValuePair> pairs = new ArrayList<>();
        JSONObject jsonResultado;
        String mensagem = "";
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

            switch (response.getStatusLine().getStatusCode()) {
                //Se a requisição retornou 200 -> http ok status carrega o perfil do usuário
                case HttpsURLConnection.HTTP_OK:
                    jsonResultado = new JSONObject(EntityUtils.toString(entity));
                    tokenDeAcesso = jsonResultado.getString("access_token");
                    carregarUsuario(login, senha, activity);
                    break;
                //Se a requisição retornou 400 -> http bad request status
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    mensagem = "Login incorreto e/ou Senha incorreta!";
                    break;
                //Erro ou problema de conexão com o servidor
                default:
                    mensagem = "Ocorreu um erro na comunicação com o servidor.";
            }
        } catch (Exception e) {
            Log.e("logar", "Falha ao tentar gerar o token.", e);
            mensagem = "Ocorreu um erro na comunicação com o servidor.";
        }

        return mensagem;
    }

    private void carregarUsuario(String login, String senha, Activity activity) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/carregar");
        JSONObject jsonUsuario;
        HttpResponse response;
        HttpEntity entity;

        try {
            //Monta cabeçalho da requisição
            httppost.setHeader("Authorization", "bearer " + tokenDeAcesso);
            httppost.setHeader("login", login);
            httppost.setHeader("senha", senha);

            response = httpclient.execute(httppost);
            entity = response.getEntity();

            //Verifica a resposta
            if (response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_OK) {

                //Converte a resposta em Json
                jsonUsuario = new JSONObject(EntityUtils.toString(entity));

                //Cria tela principal do app
                Intent mainActivity = new Intent(activity, MainActivity.class);

                //Converte o Json para um objeto Usuario
                Usuario usuario = new Usuario();
                usuario.carregarUsuario(jsonUsuario);

                //Abre a tela principal do app passando o usuário
                mainActivity.putExtra("usuario", (Serializable) usuario);
                activity.startActivity(mainActivity);
            } else {
                deslogar();
            }
        } catch (Exception e) {
            Log.e("carregarUsuario", "Falha ao tentar executar o login.", e);
            deslogar();
        }
    }

    public JSONArray buscar(int idRedeSocial, String username) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/" + Integer.toString(idRedeSocial) + "/" + username);
        HttpResponse response;
        HttpEntity entity;
        JSONArray jsonArrayPerfis;

        try {
            httpget.setHeader("Authorization", "bearer " + tokenDeAcesso);
            response = httpclient.execute(httpget);
            entity = response.getEntity();
            String sEntity = EntityUtils.toString(entity);

            if (response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_OK) {
                jsonArrayPerfis = new JSONArray(sEntity);
                if(jsonArrayPerfis.length() > 0){
                    return jsonArrayPerfis;
                } else{
                    return null;
                }
            } else {
                deslogar();
                return null;
            }
        } catch (Exception e) {
            Log.e("Classe Requisicao.java", "Falha ao buscar usuários.", e);
            return null;
        }
    }

    public boolean cadastrar(Usuario usuario) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/");
        HttpResponse response = null;

        try {
            //Monta cabeçalho da requisição
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new ByteArrayEntity(usuario.toJSON().toString().getBytes("UTF8")));

            //Executa a requisição
            response = httpclient.execute(httpPost);
        } catch (Exception e) {
            Log.e("Requisicao.Java", "Falha ao cadastrar.", e);
        }

        return response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_NO_CONTENT;
    }

    public boolean verificarExistenciaLogin(String login) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/login/" + login);
        HttpResponse response = null;
        HttpEntity entity;

        try {
            //Executa a requisição
            response = httpclient.execute(httpPost);
            entity = response.getEntity();
            return Boolean.parseBoolean(EntityUtils.toString(entity));
        } catch (Exception e) {
            Log.e("Requisicao.Java", "Falha ao verificar existência do login.", e);
            return true;
        }
    }

    public boolean atualizarPerfil(Usuario usuario) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/");
        HttpResponse response = null;

        try {
            //Monta cabeçalho da requisição
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setHeader("Authorization", "bearer " + tokenDeAcesso);
            httpPut.setEntity(new ByteArrayEntity(usuario.toJSON().toString().getBytes("UTF8")));

            //Executa a requisição
            response = httpclient.execute(httpPut);
        } catch (Exception e) {
            Log.e("Requisicao.Java", "Falha ao atualizar o perfil.", e);
        }

        if(response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_NO_CONTENT){
            return true;
        }else{
            deslogar();
            return false;
        }
    }

    public void deslogar() {
        final Activity Activity = new LoginActivity();

        //Cria tela login do app
        Intent loginActivity = new Intent(Activity, LoginActivity.class);

        Requisicao.tokenDeAcesso = "";

        //Vai para a tela de login
        Activity.startActivity(loginActivity);
    }

    public List<Notificacao> carregarNotificacoes(Usuario usuario) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/notificacoes");
        HttpResponse response;
        HttpEntity entity;
        List<Notificacao> listaDeNotificacoes = new ArrayList<Notificacao>();

        try {

            httpPost.setHeader("Authorization", "bearer " + tokenDeAcesso);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new ByteArrayEntity(usuario.toJSON().toString().getBytes("UTF8")));

            response = httpclient.execute(httpPost);
            entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_OK) {

                String sEntity = EntityUtils.toString(entity);
                JSONArray jsonNotificacoes = new JSONArray(sEntity);

                if(jsonNotificacoes.length() > 0){
                    //Transforma JsonArray em ArrayList
                    for (int i = 0; i < jsonNotificacoes.length(); i++) {
                        Notificacao notificacao = new Notificacao(jsonNotificacoes.getJSONObject(i), usuario);
                        listaDeNotificacoes.add(notificacao);
                    }
                    return listaDeNotificacoes;
                }else{
                    return null;
                }
            } else {
                deslogar();
                return null;
            }
        } catch (Exception e) {
            Log.e("Requisicao.Java", "Falha ao carregar notificacoes.", e);
            return null;
        }
    }

    public boolean compartilhar(Usuario emissor, Usuario receptor) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/compartilhar");
        HttpResponse response = null;

        try {
            //Monta cabeçalho da requisição
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "bearer " + tokenDeAcesso);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", 0);
            jsonObject.put("emissor", emissor.toJSON());
            jsonObject.put("receptor", receptor.toJSON());
            jsonObject.put("dataehora", "");

            httpPost.setEntity(new ByteArrayEntity(jsonObject.toString().getBytes("UTF8")));
            response = httpclient.execute(httpPost);
        } catch (Exception e) {
            Log.e("Requisicao.Java", "Falha ao compartilhar.", e);
        }

        if(response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_NO_CONTENT){
            return true;
        }else{
            deslogar();
            return false;
        }
    }
}