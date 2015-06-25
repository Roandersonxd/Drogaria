package br.com.roanderson.projeto.conexao;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import br.com.roanderson.projeto.domain.Fabricante;
import br.com.roanderson.projeto.domain.Funcionario;
import br.com.roanderson.projeto.domain.Produto;

public class ClienteREST {

    private static final String URL_WS = "http://192.168.43.149:8080/drog/webresources/drogaria/Funcionario/";

    public Funcionario getUsuario(int id) throws Exception {

        String[] resposta = new WebServiceCliente().get(URL_WS + id);

        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            Funcionario funcionario = gson.fromJson(resposta[1], Funcionario.class);
            return funcionario;
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public List<Funcionario> getListaFuncionarios() throws Exception {

        String[] resposta = new WebServiceCliente().get(URL_WS + "list");
//       String[] resposta = new WebServiceCliente().get(URL_WS + "buscarTodos");

        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                listaFuncionario.add(gson.fromJson(array.get(i), Funcionario.class));
            }
            return listaFuncionario;
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public String inserirUsuario(Funcionario funcionario) throws Exception {

        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(funcionario);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "insere", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }
    public String editarUsuario(Funcionario funcionario) throws Exception {

        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(funcionario);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "editar", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public String deletarUsuario(Funcionario funcionario) throws Exception {
        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(funcionario);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "delete", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public String inserirFabricante(Fabricante fabricante) throws Exception {

        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(fabricante);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "insereFabricante", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public String deletarFabricante(Fabricante fabricante) throws Exception {
        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(fabricante);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "deleteFabricante", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public List<Fabricante> getListaFabricantes() throws Exception {

        String[] resposta = new WebServiceCliente().get(URL_WS + "listFabricantes");
//       String[] resposta = new WebServiceCliente().get(URL_WS + "buscarTodos");

        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            ArrayList<Fabricante> listaFabricante = new ArrayList<Fabricante>();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                listaFabricante.add(gson.fromJson(array.get(i), Fabricante.class));
            }
            return listaFabricante;
        } else {
            throw new Exception(resposta[1]);
        }
    }
    public String editarFabricante(Fabricante fabricante) throws Exception {

        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(fabricante);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "Funcionario/editarFabricante", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public String inserirProduto(Produto produto) throws Exception {

        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(produto);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "insereProduto", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public String editarProduto(Produto produto) throws Exception {

        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(produto);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "Funcionario/editarProduto", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public String deletarProduto(Produto produto) throws Exception {
        Gson gson = new Gson();
        String funcionarioJSON = gson.toJson(produto);
        System.out.println(funcionarioJSON);
        String[] resposta = new WebServiceCliente().post(URL_WS + "deleteProduto", funcionarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public List<Produto> getListaProduto() throws Exception {

        String[] resposta = new WebServiceCliente().get(URL_WS + "list");
//       String[] resposta = new WebServiceCliente().get(URL_WS + "buscarTodos");

        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            ArrayList<Produto> listaProduto = new ArrayList<Produto>();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                listaProduto.add(gson.fromJson(array.get(i), Produto.class));
            }
            return listaProduto;
        } else {
            throw new Exception(resposta[1]);
        }
    }

}