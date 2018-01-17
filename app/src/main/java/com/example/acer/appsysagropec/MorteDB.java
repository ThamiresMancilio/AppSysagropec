package com.example.acer.appsysagropec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Acer on 05/12/2017.
 */

public class MorteDB extends SQLiteOpenHelper {

    public MorteDB(Context context) {
        super(context, "sysagropec.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists mortes(" +
                "_id integer primary key autoincrement, " +
                "descricao text, idanimal integer, enviado integer, descricaoanimal text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long inclui(Morte morte) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues dados = new ContentValues();

            dados.put("descricao", morte.getDescricao());
            dados.put("idanimal", morte.getIdAnimal());
            dados.put("enviado",0);
            dados.put("descricaoanimal", morte.getDescricaoanimal());

            long id = db.insert("mortes", "", dados);
            return id;
        } finally {
            db.close();
        }
    }

    public ArrayList<Morte> busca() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.query("mortes", null, "enviado=?",
                    new String[]{String.valueOf(0)},
                    null, null, null);

            if (c.getCount() > 0) {

                ArrayList<Morte> mortes = new ArrayList<>();

                c.moveToFirst();

                for(int i = 0; i < c.getCount(); i++){

                    long id = c.getLong(0);
                    String descricao = c.getString(1);
                    int idAnimal = c.getInt(2);
                    int enviado = c.getInt(3);
                    String descricaoAnimal = c.getString(4);

                    Morte morte = new Morte(id, idAnimal, descricao, enviado, descricaoAnimal);

                    mortes.add(i,morte);

                    c.moveToNext();
                }

                return mortes;

            } else {

                return null; //new Morte(0, 0, "",0,"");
            }
        } finally {
            db.close();
        }
    }

    public long altera(Morte morte) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues dados = new ContentValues();

            dados.put("descricao", morte.getDescricao());
            dados.put("idanimal", morte.getIdAnimal());
            dados.put("enviado", 1);
            dados.put("descricaoanimal", morte.getDescricaoanimal());

            long id = db.update("mortes", dados,
                    "_id=?", new String[]{String.valueOf(morte.getIdMorte())});
            return id;
        } finally {
            db.close();
        }
    }

}
