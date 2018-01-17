package com.example.acer.appsysagropec;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main4ActivityVisualizarMotivoMorte extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvMotivoMortes;
    private ProgressDialog load;
    private Morte morte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_visualizar_motivo_morte);

        lvMotivoMortes = (ListView) findViewById(R.id.lvMotivosMorte);
        lvMotivoMortes.setOnItemClickListener(this);

        MorteDB morte = new MorteDB(this);

        ArrayList<Morte> mortes = morte.busca();

        ListAdapterMotivoMorte adapter = new ListAdapterMotivoMorte(Main4ActivityVisualizarMotivoMorte.this, R.layout.item_view_motivo_morte, mortes);
        lvMotivoMortes.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        morte = (Morte) parent.getItemAtPosition(position);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.vaca);
        builder.setTitle("Confirmação de envio dados");
        builder.setMessage("Deseja realmente enviar a notificação de morte para o animal " + morte.getDescricaoanimal() + " com o motivo " + morte.getDescricao() + " ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which){

                SendMorte send = new SendMorte();

                send.execute();

                return;
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which){

                return;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private class SendMorte extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute(){

            load = ProgressDialog.show(Main4ActivityVisualizarMotivoMorte.this, "Por favor aguarde", "Enviado notificação de morte...");
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Integer doInBackground(String... params) {

            try {
                URL url = new URL("http://10.0.2.2:8080/APIRest/rest/morte/edit/");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("PUT"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
                httpURLConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
                httpURLConnection.connect();

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("idAnimal",morte.getIdAnimal());
                jsonObject.put("idMorte", morte.getIdMorte());
                jsonObject.put("descricao",morte.getDescricao());
                jsonObject.put("enviado",morte.getEnviado());
                jsonObject.put("descricaoanimal",morte.getDescricaoanimal());

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(jsonObject.toString());
                wr.flush();
                wr.close();
                int responsecode= httpURLConnection.getResponseCode();
                String resposta = httpURLConnection.getResponseMessage();

                return  responsecode;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer responsecode){

            MorteDB m = new MorteDB(getApplicationContext());

            m.altera(morte);

            load.dismiss();

            /*AlertDialog.Builder builder2 = new AlertDialog.Builder(getApplicationContext());

            builder2.setIcon(R.drawable.vaca);
            builder2.setTitle("Sucesso");
            builder2.setMessage("Dados enviados com sucesso");
            builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which){

                    return;
                }
            });

            AlertDialog dialog2 = builder2.create();
            dialog2.show();*/

            MorteDB morte = new MorteDB(getApplicationContext());

            ArrayList<Morte> mortes = morte.busca();

            ListAdapterMotivoMorte adapter = new ListAdapterMotivoMorte(Main4ActivityVisualizarMotivoMorte.this, R.layout.item_view_motivo_morte, mortes);
            lvMotivoMortes.setAdapter(adapter);

            if(responsecode == HttpURLConnection.HTTP_OK){
                Toast.makeText(getApplicationContext(), "Dados enviados com sucesso", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
            }


        }
    }
}
