package com.example.acer.appsysagropec;

/**
 * Created by Acer on 04/12/2017.
 */

public class Producao {

    private int id;
    private String animal;
    private double quantidade;


    public Producao(int id, String animal, double quantidade){

        this.id = id;
        this.animal = animal;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
