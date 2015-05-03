package com.example.foodexpress.app;

import java.io.Serializable;

/**
 * Created by MarcoSilva on 20/07/2014.
 */
public class Restaurante implements Serializable{

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getImagem() {
        return imagem;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getHorarioAbertura() {
        return horarioAbertura;
    }
    public String getHorarioFechamento() {
        return horarioFechamento;
    }
    public String getEndereco() {
        return endereco;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setHorarioAbertura(String horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }
    public void setHorarioFechamento(String horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    private int id;
    private String nome;
    private String imagem;
    private String telefone;
    private String horarioAbertura;
    private String horarioFechamento;
    private String endereco;

}
