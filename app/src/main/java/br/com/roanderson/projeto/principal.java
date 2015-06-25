package br.com.roanderson.projeto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.*;
import android.view.*;
import android.content.*;

import br.com.roanderson.projeto.conexao.ClienteREST;
import br.com.roanderson.projeto.domain.Funcionario;


public class principal extends ActionBarActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Button button = (Button)findViewById(R.id.button5);
        Button button1 = (Button)findViewById(R.id.button3);
        Button button2 = (Button)findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
              chamaTela();
            }

        });

        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                chamaTela1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                chamaTela2();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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

    public void chamaTela() {
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
    }

    public void chamaTela1() {
        Intent it = new Intent(this,MainFabricante.class);
        startActivity(it);
    }

    public void chamaTela2() {
        Intent it = new Intent(this,MainProduto.class);
        startActivity(it);
    }
}
