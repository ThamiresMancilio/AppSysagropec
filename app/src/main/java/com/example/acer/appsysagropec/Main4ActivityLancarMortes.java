package com.example.acer.appsysagropec;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main4ActivityLancarMortes extends AppCompatActivity {

    private  int idUsuario;
    private int idfazenda;
    private String nomeUsuario;
    private int idanimal;
    private String descrianimal;
    private TextView txtMsg;
    private EditText edtMotivo;
    private TextView edtAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_lancar_mortes);

        Intent it = getIntent();
        idUsuario = it.getIntExtra("id", 0);
        nomeUsuario = it.getStringExtra("nome");
        idfazenda = it.getIntExtra("idfazenda", 0);
        descrianimal = it.getStringExtra("descrianimal");
        idanimal = it.getIntExtra("idanimal", 0);
        txtMsg = (TextView) findViewById(R.id.txtMsg);

        txtMsg.setText("Informe o motivo para a morte do animal ");

        edtMotivo = (EditText) findViewById(R.id.txtMotivo);
        edtAnimal = (TextView) findViewById(R.id.txtAnimal);

        edtAnimal.setText("Animal: " + descrianimal);
    }

    public void onClickGravar(View view) {

        String motivo = edtMotivo.getText().toString();

        if(motivo.isEmpty()){

            Toast.makeText(this, "Informe o motivo da morte", Toast.LENGTH_LONG).show();

        }else{

            MorteDB morteDB = new MorteDB(this);

            long id = morteDB.inclui(new Morte(0, idanimal, motivo, 0, descrianimal));

            if (id > 0) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setIcon(R.drawable.vaca);
                builder.setTitle("Mensagem");
                builder.setMessage("Dados inseridos com sucesso. Id: " + id );
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which){
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setIcon(R.drawable.vaca);
                builder.setTitle("Erro");
                builder.setMessage("Ocorreu um erro");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which){
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }


        }
    }
}
