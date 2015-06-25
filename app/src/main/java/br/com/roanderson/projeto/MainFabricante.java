package br.com.roanderson.projeto;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.roanderson.projeto.conexao.ClienteREST;
import br.com.roanderson.projeto.domain.Fabricante;


public class MainFabricante extends ActionBarActivity {
    private static String TAG = "LOG";
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    String  codigo;
    private EditText editText ;
    private EditText editText2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fabricante);
        //
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //
        editText = (EditText)findViewById(R.id.descricao);
        //
        Button button = (Button)findViewById(R.id.button);


                //
        editText.setText(this.getIntent().getStringExtra("nome"));

        codigo = this.getIntent().getStringExtra("codigo");
        gerarToast(codigo);
        //
        try{

            // usuario.setLogin(editText.getText());
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    final Fabricante fabricante = new Fabricante();
                    fabricante.setDescricao(editText.getText().toString().trim());
                    ClienteREST fabREST = new ClienteREST();
                    try{

                        String resposta = fabREST.inserirFabricante(fabricante);
                        //textView.setText(resposta);
                        gerarToast("Fabricante cadastrado com sucesso!");
                    }catch(Exception e){
                        e.printStackTrace();
                        gerarToast(e.getMessage());
                    }
                }

            });
        }catch (Exception ex){
            Log.i("JSON", ex.getMessage());

        }
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(" "+" Cadastro de fabricantes");
        mToolbar.setSubtitle(" "+"  Drogaria");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);

        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent it = null;

                switch(menuItem.getItemId()){
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
                Toast.makeText(MainFabricante.this, "Pressinou", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void gerarToast(CharSequence message) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast
                .makeText(getApplicationContext(), message, duration);
        toast.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_fabricante, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_second_activity){
            startActivity(new Intent(this,MainListaFabricante.class));
        }

        if(id==R.id.excluir){
           ClienteREST fabREST = new ClienteREST();
            try {
                fabREST.deletarFabricante(new Fabricante(Integer.parseInt(codigo)));
                gerarToast("Fabricante excluido com sucesso!!");
                limpaTela();

            } catch (Exception e) {
                e.printStackTrace();
                gerarToast("Erro ao tentar excluir um fabricante!!");
            }
        }

        if(id==R.id.editar){
            Fabricante fabricante = new Fabricante(Integer.parseInt(codigo));
            fabricante.setDescricao(editText.getText().toString().trim());

            ClienteREST fabREST = new ClienteREST();
            try{

                String resposta = fabREST.editarFabricante(fabricante);
                //textView.setText(resposta);
                gerarToast("Fabricante Editado com sucesso!");
            }catch(Exception e){
                e.printStackTrace();
                gerarToast(e.getMessage());
            }
        }


        return super.onOptionsItemSelected(item);
    }
    public void limpaTela(){
        editText.setText(" ");
        editText2.setText(" ");
    }
}
