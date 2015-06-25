/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.roanderson.projeto.domain;

public class Produto extends Funcionario {
     int    codigo;
     int    codigoFab;
    String descricao;
    float  preco;
    String quantidade;

    public Produto (int id){
        this.codigo=id;
    }
    public Produto (){
    }


    public int getCodigoFab() {
        return codigoFab;
    }

    public void setCodigoFab(int codigoFab) {
        this.codigoFab = codigoFab;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }




}
