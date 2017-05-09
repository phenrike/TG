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

/**
 * Created by Paulo Santos on 11/02/2017.
 */
public class Requisicao {

    public static String tokenDeAcesso;
    public static String ip = "192.168.0.11";

    private void gerarTokenDeAcesso(String login, String senha) throws UnsupportedEncodingException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://" + ip + "/WebAPIMainP/token");
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
        HttpPost httppost = new HttpPost("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/" + login + "/" + senha);
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
        JSONArray jsonArrayPerfis = null;

        if (!tokenValido()) {
            deslogar();
        } else {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/" + Integer.toString(indexDaRedeSocial) + "/" + conteudoDaBusca);
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
        }

        return jsonArrayPerfis;
    }

    public boolean cadastrar(Usuario usuario) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/");

        try {
            //Monta cabeçalho da requisição
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new ByteArrayEntity(
                    usuario.toJSON().toString().getBytes("UTF8")));

            //Executa a requisição
            httpclient.execute(httpPost);
            return true;
        } catch (Exception e) {
            Log.e("Requisicao.Java", "Falha ao cadastrar.", e);
            return false;
        }
    }

    public boolean atualizarPerfil(Usuario usuario) {

        boolean resultado = false;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut("http://" + ip + "/WebAPIMainP/api/mainp/usuarios/");

        try {
            //Monta cabeçalho da requisição
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setHeader("Authorization", "bearer " + tokenDeAcesso);
            httpPut.setEntity(new ByteArrayEntity(
                    usuario.toJSON().toString().getBytes("UTF8")));

            //Executa a requisição
            httpclient.execute(httpPut);

            return resultado = true;
        } catch (Exception e) {
            Log.e("Requisicao.Java", "Falha ao atualizar o perfil.", e);
            return resultado = false;
        }
    }

    public boolean tokenValido() {

        boolean resposta = false;

        //Se o token estiver vazio ou nulo o usuario é redirecionado para o login
        if (tokenDeAcesso.isEmpty() || tokenDeAcesso == null) {
            return resposta = false;
        } else {
            return resposta = true;
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
            //Monta cabeçalho da requisição
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new ByteArrayEntity(
                    usuario.toJSON().toString().getBytes("UTF8")));

            //Executa a requisição
            response = httpclient.execute(httpPost);

            entity = response.getEntity();

            //Verifica a resposta
            if (entity != null) {
                String stringResposta = EntityUtils.toString(entity);

                //Verifica se a resposta contém usuários e retorna um json array
                if (!stringResposta.equals("[]")) {
                    JSONArray jsonNotificacoes = new JSONArray(stringResposta);

                    //Transforma JsonArray em ArrayList
                    for (int i = 0; i < jsonNotificacoes.length(); i++) {
                        Notificacao notificacao = new Notificacao(jsonNotificacoes.getJSONObject(i), usuario);
                        listaDeNotificacoes.add(notificacao);
                    }

                    return listaDeNotificacoes;
                } else {
                    return null;
                }
            } else {
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

        try {
            //Monta cabeçalho da requisição
            httpPost.setHeader("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id", 0);
            jsonObject.put("emissor", emissor.toJSON());
            jsonObject.put("receptor", receptor.toJSON());
            jsonObject.put("dataehora", "");

            httpPost.setEntity(new ByteArrayEntity(
                    jsonObject.toString().getBytes("UTF8")));

            //Executa a requisição
            httpclient.execute(httpPost);
            return true;
        } catch (Exception e) {
            Log.e("Requisicao.Java", "Falha ao compartilhar.", e);
            return false;
        }
    }
}