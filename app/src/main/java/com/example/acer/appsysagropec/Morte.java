package com.example.acer.appsysagropec;

/**
 * Created by Acer on 04/12/2017.
 */

public class Morte {


    private int idAnimal;
    private long idMorte;
    private String descricao;
    private int enviado;
    private String descricaoanimal;

    public Morte(long idMorte, int idAnimal, String descricao, int enviado,String descricaoanimal){
        this.descricao = descricao;
        this.idAnimal = idAnimal;
        this.idMorte = idMorte;
        this.enviado = enviado;
        this.descricaoanimal = descricaoanimal;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public long getIdMorte() {
        return idMorte;
    }

    public void setIdMorte(long idMorte) {
        this.idMorte = idMorte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEnviado() {
        return enviado;
    }

    public void setEnviado(int enviado) {
        this.enviado = enviado;
    }

    public String getDescricaoanimal() {
        return descricaoanimal;
    }

    public void setDescricaoanimal(String descricaoanimal) {
        this.descricaoanimal = descricaoanimal;
    }
}
