package com.example.acer.appsysagropec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main3ActivityOpcoes extends AppCompatActivity {

    private int idUsuario;
    private String nomeUsuario;
    private int idfazenda;
    private TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_opcoes);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        Intent it = getIntent();
        idUsuario = it.getIntExtra("id", 0);
        nomeUsuario = it.getStringExtra("nome");
        idfazenda = it.getIntExtra("idfazenda", -1);

        txtMsg.setText(nomeUsuario + " escolha uma opção abaixo!!");

    }

    public void onClickbtnProducao(View view) {

        Intent it = new Intent(this, Main4ActivityListaProducoes.class);

        it.putExtra("idfazenda", idfazenda);
        it.putExtra("nome", nomeUsuario);
        it.putExtra("idusuario", idUsuario);

        this.startActivity(it);


    }

    public void onclickbtnMorte(View view) {

        Intent it = new Intent(this, Main4ActivityAnimaisMorte.class);

        it.putExtra("idfazenda", idfazenda);
        it.putExtra("nome", nomeUsuario);
        it.putExtra("idusuario", idUsuario);

        this.startActivity(it);

    }

    public void onclickbtnSincronizarMorte(View view) {

        Intent it = new Intent(this, Main4ActivityVisualizarMotivoMorte.class);

        it.putExtra("idfazenda", idfazenda);
        it.putExtra("nome", nomeUsuario);
        it.putExtra("idusuario", idUsuario);

        this.startActivity(it);
    }
}
