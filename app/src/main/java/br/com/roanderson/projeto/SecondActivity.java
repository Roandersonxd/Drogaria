package br.com.roanderson.projeto;

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
import br.com.roanderson.projeto.domain.Funcionario;

import static br.com.roanderson.projeto.R.layout.*;


public class SecondActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    private ListView lstView;
    private Funcionario funcionario;
    private List<Funcionario> list;
    int     funcionarioCodigo;
    String  funcionarioNome;
    String  funcionarioFuncao;
    String  funcionarioSenha;
    String  funcionarioCpf;
    String codigo;

    ArrayList<String> cli = new ArrayList<String>();
    HashMap<String,String> fun = new HashMap<String,String>();
    HashMap<String,Funcionario> func = new HashMap<>();
    Funcionario funnnn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_second);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final ClienteREST client = new ClienteREST();
       // Button button = (Button)findViewById(R.id.button2);

       // funcionario = new Funcionario();
        try {
            list = client.getListaFuncionarios();

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("JSON",e.getMessage());
        }

        ListView Lv = (ListView) findViewById(R.id.listView);



        try {
            for (int i = 0; i <client.getListaFuncionarios().size(); i++) {

                String Id_Cliente = String.valueOf(list.get(i).getCodigo());
                String Nome = list.get(i).getNome();
                String cpf = list.get(i).getCpf();
                String senha = list.get(i).getCpf();
                String funcao = list.get(i).getFuncao();
                //

                Funcionario fun1= new Funcionario();
                fun1.setSenha(senha);
                fun1.setCpf(cpf);
                fun1.setCodigo(Integer.parseInt(Id_Cliente));
                fun1.setFuncao(funcao);
                fun1.setNome(Nome);
                //
                cli.add(Nome);
                fun.put(Nome,Id_Cliente);
                func.put(Nome,fun1);
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
                   funcionarioNome   = func.get(nomeCliente).getNome();
                   funcionarioFuncao = func.get(nomeCliente).getFuncao();
                   funcionarioCpf  = func.get(nomeCliente).getCpf();
                   funcionarioSenha  = func.get(nomeCliente).getSenha();
                   funcionarioCodigo = func.get(nomeCliente).getCodigo();
                    funnnn = func.get(nomeCliente);

                    passaDados();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("JSON",e.getMessage());
                 }

            }
        });



        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Lista de funcionários");
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
                Toast.makeText(SecondActivity.this, "Settings pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public AdapterView.OnItemClickListener lista(final Context  context){
        return(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                     intent = new Intent(context,Funcionario.class);
                     startActivity(intent);
                     break;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }

        return true;
    }
    public void passaDados(){
        Intent it = new Intent(this,MainActivity.class);
        it.putExtra("nome",funcionarioNome);
        it.putExtra("funcao",funcionarioFuncao);
        it.putExtra("cpf",funcionarioCpf);
        it.putExtra("senha",funcionarioSenha);
        it.putExtra("codigo",codigo
        );
        startActivity(it);

    }
}
