package br.com.roanderson.projeto;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.*;
import android.widget.*;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.roanderson
        .projeto.R;
import br.com.roanderson.projeto.conexao.ClienteREST;
import br.com.roanderson.projeto.domain.Fabricante;
import br.com.roanderson.projeto.domain.Funcionario;


import static br.com.roanderson.projeto.R.layout.*;



public class MainListaFabricante extends ActionBarActivity {
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    private ListView lstView;
    private Fabricante fabricante;
    private List<Fabricante> list;
    int     fabricanteCodigo;
    String  fabricanteDescricao;
    String codigo;

    ArrayList<String> cli = new ArrayList<String>();
    HashMap<String,String> fun = new HashMap<String,String>();
    HashMap<String,Fabricante> func = new HashMap<>();
    Fabricante funnnn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_fabricante);



        final ClienteREST client = new ClienteREST();

        try {
            list = client.getListaFabricantes();

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("JSON",e.getMessage());
        }

        ListView Lv = (ListView) findViewById(R.id.listView);

        try {
            for (int i = 0; i <client.getListaFabricantes().size(); i++) {

                String Id_Cliente = String.valueOf(list.get(i).getCodigo());
                String descricao = list.get(i).getDescricao();


                Fabricante fun1= new Fabricante();
                fun1.setCodigo(Integer.parseInt(Id_Cliente));
                fun1.setDescricao(descricao);
                //
                cli.add(descricao);
                fun.put(descricao,Id_Cliente);
                func.put(descricao,fun1);
            }
            //Cria ListVeiew dentro de um método
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cli);
            Lv.setAdapter(arrayAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int posicao, long id) {
                // TODO Auto-generated method stub

                String nomeCliente = cli.get(posicao);
                Toast.makeText(getBaseContext(),
                        "Posição Selecionada:"+fun.get(nomeCliente), Toast.LENGTH_SHORT)
                        .show();


                try {
                    // client.deletarUsuario(new Funcionario(Integer.parseInt(fun.get(nomeCliente))));
                    codigo=fun.get(nomeCliente);
                    fabricanteDescricao  = func.get(nomeCliente).getDescricao();
                    fabricanteCodigo = func.get(nomeCliente).getCodigo();
                    funnnn = func.get(nomeCliente);

                    passaDados();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("JSON",e.getMessage());
                }

            }
        });



        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Lista de Fabricantes");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent it = null;

                switch (menuItem.getItemId()) {
                    case R.id.action_facebook:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.facebook.com"));
                        break;
                    case R.id.action_youtube:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.youtube.com"));
                        break;
                    case R.id.action_google_plus:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://plus.google.com"));
                        break;
                    case R.id.action_linkedin:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.linkedin.com"));
                        break;
                    case R.id.action_whatsapp:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.whatsapp.com"));
                        break;
                }

                startActivity(it);
                return true;
            }

        });
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);

        mToolbarBottom.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainListaFabricante.this, "Settings pressed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_lista_fabricante, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void passaDados(){
        Intent it = new Intent(this,MainFabricante.class);
        it.putExtra("nome",fabricanteDescricao);
        it.putExtra("codigo",codigo
        );
        startActivity(it);

    }
}
