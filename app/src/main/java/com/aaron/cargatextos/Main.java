package com.aaron.cargatextos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;


public class Main extends Activity {

    private EditText etT;
    private String texto;
    private File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etT=(EditText)findViewById(R.id.etEdit);
        leer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.save:
                guardar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void guardar(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        try {
             texto = etT.getText().toString();
             OutputStreamWriter escribir = new OutputStreamWriter(new FileOutputStream(f));
             escribir.write(texto);
             escribir.flush();
             escribir.close();
             finish();
             tostada(R.string.guardado);
            } catch(IOException e){}
    }

    //fgffhffghgbd
    /* Mostramos  un  mensaje flotante a partir de un recurso string*/
    private void tostada(int s){
        Toast.makeText(this, getText(s), Toast.LENGTH_SHORT).show();
    }

    public void leer() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        texto = data.getPath();
        f=new File(texto);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            StringBuilder texto = new StringBuilder("");
            while ((linea = br.readLine()) != null) {
                texto.append(linea+"\n");
            }
        br.close();
            etT.setText(texto);
        } catch (IOException e) {}
    }

}
