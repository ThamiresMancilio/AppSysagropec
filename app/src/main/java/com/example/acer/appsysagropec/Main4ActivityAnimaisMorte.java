package com.example.acer.appsysagropec;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main4ActivityAnimaisMorte extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private  int idUsuario;
    private String nomeUsuario;
    private  int idfazenda;
    private ListView lvAnimais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_animais_morte);


        lvAnimais =(ListView) findViewById(R.id.lvAnimais);
        lvAnimais.setOnItemClickListener(this);

        Intent it = getIntent();
        idUsuario = it.getIntExtra("id", 0);
        nomeUsuario = it.getStringExtra("nome");
        idfazenda = it.getIntExtra("idfazenda", 0);

        Main4ActivityAnimaisMorte.GetAnimais download = new  Main4ActivityAnimaisMorte.GetAnimais(this);
        download.execute("http://10.0.2.2:8080/APIRest/rest/animal/list/"+idfazenda);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Animal animal = (Animal) parent.getItemAtPosition(position);

        Intent it = new Intent(this, Main4ActivityLancarMortes.class);

        it.putExtra("idfazenda", idfazenda);
        it.putExtra("nome", nomeUsuario);
        it.putExtra("idusuario", idUsuario);
        it.putExtra("descrianimal", animal.getLivro() + animal.getRegistro() + " - " + animal.getApelido());
        it.putExtra("idanimal", animal.getId());


        this.startActivity(it);
    }

    private class GetAnimais extends AsyncTask<String, Void, ArrayList<Animal>> {

        Context context;
        private GetAnimais(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected ArrayList<Animal> doInBackground(String... strings) {


            String path = strings[0];

            Utils util = new Utils();


            return util.getInformacaoAnimal(path);
        }

        @Override
        protected void onPostExecute(ArrayList<Animal> animais) {

            ListAdapterAnimal adapter = new ListAdapterAnimal(Main4ActivityAnimaisMorte.this, R.layout.item_animal, animais);
            lvAnimais.setAdapter(adapter);

        }
    }
}
