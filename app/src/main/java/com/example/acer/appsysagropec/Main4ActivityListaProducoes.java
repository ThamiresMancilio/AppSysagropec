package com.example.acer.appsysagropec;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class Main4ActivityListaProducoes extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private  int idUsuario;
    private String nomeUsuario;
    private  int idfazenda;
    private ListView lvProducoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_lista_producoes);

        lvProducoes =(ListView) findViewById(R.id.lvProducao);
        lvProducoes.setOnItemClickListener(this);

        Intent it = getIntent();
        idUsuario = it.getIntExtra("id", 0);
        nomeUsuario = it.getStringExtra("nome");
        idfazenda = it.getIntExtra("idfazenda", 0);

        GetProducoes download = new GetProducoes(this);
        download.execute("http://10.0.2.2:8080/APIRest/rest/producao/list/"+idfazenda);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Producao p = (Producao) parent.getItemAtPosition(position);

        Toast.makeText(this, "Animal = " + p.getAnimal(), Toast.LENGTH_LONG);
    }

    private class GetProducoes extends AsyncTask<String, Void, ArrayList<Producao>> {

        Context context;
        private GetProducoes(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected ArrayList<Producao> doInBackground(String... strings) {


            String path = strings[0];

            Utils util = new Utils();


            return util.getInformacaoProducao(path);
        }

        @Override
        protected void onPostExecute(ArrayList<Producao> producoes) {


            ListAdapterPro adapter = new ListAdapterPro(Main4ActivityListaProducoes.this, R.layout.item_pro, producoes);
            lvProducoes.setAdapter(adapter);

        }
    }
}
