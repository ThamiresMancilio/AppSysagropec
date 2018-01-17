package com.example.acer.appsysagropec;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtLogin;
    private EditText edtSenha;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
    }

    public void onClickEntrar(View view) {

        if(edtLogin.getText().toString().isEmpty() || edtSenha.getText().toString().isEmpty()){

            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

        }else{

            String login = edtLogin.getText().toString();
            String senha = edtSenha.getText().toString();

            Context context = getApplicationContext();
            GetJson download = new GetJson(context);

            download.execute("http://10.0.2.2:8080/APIRest/rest/usuario/get/" + login + "/"+ senha);
        }

    }

    private class GetJson extends AsyncTask<String, Void, Usuario> {

        Context context;
        private GetJson(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected void onPreExecute(){

            load = ProgressDialog.show(MainActivity.this, "Por favor Aguarde ...", "Validando usuário...");
        }

        @Override
        protected Usuario doInBackground(String... strings) {

            String path = strings[0];

            Utils util = new Utils();


            return util.getInformacaoUsuario(path);

        }

        @Override
        protected void onPostExecute(Usuario pessoa){

            load.dismiss();
            if (pessoa != null){

                Intent it = new Intent(context, Main2ActivityFazenda.class);
                it.putExtra("id", pessoa.getId());
                it.putExtra("nome", pessoa.getNome());

                context.startActivity(it);

            }else {
                Toast.makeText(context, "Usuário não cadastrado no sistema", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
