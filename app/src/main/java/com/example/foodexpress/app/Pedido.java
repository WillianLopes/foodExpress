package com.example.foodexpress.app;

import java.io.Serializable;

/**
 * Created by Vitor on 06/02/2015.
 */
public class Pedido implements Serializable{

    private int id;
    private double valorTotal;
    private double troco;
    private int formaPagamento;
    private int statusPed;
    private String observacoes;
    private int id_cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getStatusPed() {
        return statusPed;
    }

    public void setStatusPed(int statusPed) {
        this.statusPed = statusPed;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
