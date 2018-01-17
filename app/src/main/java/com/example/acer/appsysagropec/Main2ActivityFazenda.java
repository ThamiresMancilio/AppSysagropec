package com.example.acer.appsysagropec;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2ActivityFazenda extends AppCompatActivity {

    private Spinner spnFazendas;
    private ArrayAdapter<Fazenda> adapterFazendas;
    private int idFazendaSelecionada;
    private String descricaoFazendaSelecionada;
    private  int idUsuario;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_fazenda);

        spnFazendas = (Spinner) findViewById(R.id.spnFazendas);

        Context context = getApplicationContext();

        Main2ActivityFazenda.GetFazendas download = new Main2ActivityFazenda.GetFazendas(context);

        download.execute("http://10.0.2.2:8080/APIRest/rest/fazenda/list");


        Intent it = getIntent();
        idUsuario = it.getIntExtra("id", 0);
        nomeUsuario = it.getStringExtra("nome");


    }

    public void onClickbtnEntrar(View view) {

        Intent it = new Intent(this, Main3ActivityOpcoes.class);

        it.putExtra("idfazenda", idFazendaSelecionada);
        it.putExtra("nome", nomeUsuario);
        it.putExtra("idusuario", idUsuario);

        this.startActivity(it);

    }

    private class GetFazendas extends AsyncTask<String, Void, ArrayList<Fazenda>> {

        Context context;
        private GetFazendas(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected ArrayList<Fazenda> doInBackground(String... strings) {


            String path = strings[0];

            Utils util = new Utils();


            return util.getInformacaoFazenda(path);
        }

        @Override
        protected void onPostExecute(ArrayList<Fazenda> fazendas) {
            final List<String> fazendasDescricao;
            final List<Integer> fazendasId;


            fazendasDescricao = new ArrayList<>();
            fazendasId = new ArrayList<>();

            for(int i =0; i<=fazendas.size() -1; i++){

                fazendasDescricao.add(fazendas.get(i).getNome());
                fazendasId.add(fazendas.get(i).getId());

            }

            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(Main2ActivityFazenda.this,
                            android.R.layout.simple_list_item_1,
                            fazendasDescricao);


            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFazendas.setAdapter(arrayAdapter);

            spnFazendas.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(
                                AdapterView<?> parent, View view, int position, long id) {

                            idFazendaSelecionada = fazendasId.get(position);
                            descricaoFazendaSelecionada = fazendasDescricao.get(position);

                            Toast.makeText(getApplicationContext(), "Fazenda: " + descricaoFazendaSelecionada + " - id: " + idFazendaSelecionada, Toast.LENGTH_SHORT).show();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
        }
    }
}



