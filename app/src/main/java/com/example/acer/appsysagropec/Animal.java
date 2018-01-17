package com.example.acer.appsysagropec;

import javax.sql.StatementEvent;

/**
 * Created by Acer on 04/12/2017.
 */

public class Animal {

    private int id;
    private String registro;
    private String livro;
    private String raca;
    private String apelido;

    public Animal(int id, String registro, String livro, String raca, String apelido){

        this.id = id;
        this.registro = registro;
        this.livro  =livro;
        this.raca = raca;
        this.apelido = apelido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
}
