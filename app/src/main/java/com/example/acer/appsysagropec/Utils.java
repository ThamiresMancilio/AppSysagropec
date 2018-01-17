package com.example.acer.appsysagropec;

/**
 * Created by Acer on 05/12/2017.
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {


    public Usuario getInformacaoUsuario(String end){
        String json;
        Usuario retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonUsuario(json);

        return retorno;
    }

    private Usuario parseJsonUsuario(String json){
        try {
            Usuario pessoa = new Usuario();

            JSONObject jsonObj = new JSONObject(json);

            pessoa.setId(jsonObj.getInt("id"));
            pessoa.setLogin(jsonObj.getString("login"));
            pessoa.setNome(jsonObj.getString("nome"));
            pessoa.setSenha(jsonObj.getString("senha"));


            return pessoa;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Fazenda> getInformacaoFazenda(String end){
        String json;
        ArrayList<Fazenda> retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonFazenda(json);

        return retorno;
    }

    private ArrayList<Fazenda> parseJsonFazenda(String json){

        try {
            ArrayList<Fazenda> fazendas = new ArrayList<>();

            JSONArray array = new JSONArray(json);

            for(int i=0; i<= array.length()-1;i++){

                JSONObject objArray = array.getJSONObject(i);
                Fazenda fazenda = new Fazenda();

                fazenda.setId(objArray.getInt("id"));
                fazenda.setNome(objArray.getString("nome"));
                fazendas.add(i, fazenda);

            }

            return fazendas;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Animal> getInformacaoAnimal(String end){
        String json;
        ArrayList<Animal> retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonAnimal(json);

        return retorno;
    }

    private ArrayList<Animal> parseJsonAnimal(String json){

        ArrayList<Animal> animais = new ArrayList<>();
        try {


            JSONArray array = new JSONArray(json);

            for(int i=0; i<= array.length()-1;i++){

                JSONObject objArray = array.getJSONObject(i);
                Animal animal = new Animal(0,"","","","");

                animal.setId(objArray.getInt("id"));
                animal.setApelido(objArray.getString("apelido"));
                animal.setLivro(objArray.getString("livro"));
                animal.setRaca(objArray.getString("raca"));
                animal.setRegistro(objArray.getString("registro"));

                animais.add(i, animal);

            }
            return animais;

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<Producao> getInformacaoProducao(String end){
        String json;
        ArrayList<Producao> retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonProducao(json);

        return retorno;
    }

    private ArrayList<Producao> parseJsonProducao(String json){
        ArrayList<Producao> producoes = new ArrayList<>();
        try {


            JSONArray array = new JSONArray(json);

            for(int i=0; i<= array.length()-1;i++){

                JSONObject objArray = array.getJSONObject(i);
                Producao producao = new Producao(0,"",0);

                producao.setId(objArray.getInt("id"));
                producao.setAnimal(objArray.getString("animal"));
                producao.setQuantidade(objArray.getInt("quantidade"));

                producoes.add(i, producao);

            }

            return producoes;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return producoes;
    }

    private ArrayList<Morte> parseJsonMorte(String json){
        ArrayList<Morte> vacinas = new ArrayList<>();
        try {

            JSONArray array = new JSONArray(json);

            for(int i=0; i<= array.length()-1;i++){

                JSONObject objArray = array.getJSONObject(i);
                Morte morte = new Morte(0,0,"",0, "");

                morte.setIdMorte(objArray.getInt("id"));
                morte.setDescricao(objArray.getString("descricao"));
                morte.setIdAnimal(objArray.getInt("idanimal"));
                morte.setEnviado(1);
                morte.setDescricaoanimal(objArray.getString("descricaoanimal"));
                vacinas.add(i, morte);

            }

            return vacinas;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Morte> getInformacaoMorte(String end){
        String json;
        ArrayList<Morte> retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonMorte(json);

        return retorno;
    }
}

