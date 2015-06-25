/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.roanderson.projeto.domain;


public class Fabricante {
    private int codigo;
    private String descricao;
    private String nome;
    private String funcao;
    private String cpf;

    public Fabricante (int id){
        this.codigo=id;
    }
    public Fabricante (){
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String Descricao) {
        this.descricao = descricao;
    }


}
