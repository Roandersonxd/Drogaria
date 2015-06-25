package br.com.roanderson.projeto;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.roanderson.projeto.conexao.ClienteREST;
import br.com.roanderson.projeto.domain.Funcionario;


public class MainActivity extends ActionBarActivity {
    private static String TAG = "LOG";
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    String  codigo;
    private EditText editText ;
    private EditText editText2 ;
    private EditText editText3 ;
    private EditText editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

       // final TextView textView = (TextView)findViewById(R.id.texto);
         editText = (EditText)findViewById(R.id.descricao);
         editText2 = (EditText)findViewById(R.id.editText2);
         editText3 = (EditText)findViewById(R.id.editText3);
         editText4 = (EditText)findViewById(R.id.editText4);


            Button button = (Button)findViewById(R.id.button);

            editText.setText(this.getIntent().getStringExtra("nome"));
            editText2.setText(this.getIntent().getStringExtra("funcao"));
            editText3.setText(this.getIntent().getStringExtra("cpf"));
            editText4.setText(this.getIntent().getStringExtra("senha"));
            codigo = this.getIntent().getStringExtra("codigo");
            gerarToast(codigo);
        try{

            // usuario.setLogin(editText.getText());
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    final Funcionario funcionario = new Funcionario();
                    funcionario.setNome(editText.getText().toString().trim());
                    funcionario.setFuncao(editText2.getText().toString().trim());
                    funcionario.setCpf(editText3.getText().toString().trim());
                    funcionario.setSenha(editText4.getText().toString().trim());
                    ClienteREST cliREST = new ClienteREST();
                    try{

                        String resposta = cliREST.inserirUsuario(funcionario);
                        //textView.setText(resposta);
                        gerarToast("Funcionário cadastrado com sucesso!");
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
        mToolbar.setTitle(" "+" Cadastro de funcionários");
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
                Toast.makeText(MainActivity.this, "Pressinou", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_second_activity){
            startActivity(new Intent(this,SecondActivity.class));
        }
      if(id==R.id.excluir){
          ClienteREST funREST = new ClienteREST();
          try {
              funREST.deletarUsuario(new Funcionario(Integer.parseInt(codigo)));
              gerarToast("Funcionário excluido com sucesso!!");
              limpaTela();

          } catch (Exception e) {
              e.printStackTrace();
              gerarToast("Erro ao tentar excluir um funcionário!!");
          }
      }
      if(id==R.id.editar){
          Funcionario funcionario = new Funcionario(Integer.parseInt(codigo));
          funcionario.setNome(editText.getText().toString().trim());
          funcionario.setFuncao(editText2.getText().toString().trim());
          funcionario.setCpf(editText3.getText().toString().trim());
          funcionario.setSenha(editText4.getText().toString().trim());

          ClienteREST cliREST = new ClienteREST();
          try{

              String resposta = cliREST.editarUsuario(funcionario);
              //textView.setText(resposta);
              gerarToast("Funcionário Editado com sucesso!");
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
        editText3.setText(" ");
        editText4.setText(" ");
    }
}
