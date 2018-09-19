package com.example.vi_ni.applistafilme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity {

    private List<Filme> filmes;
    private EditText edtNome,edtNota;
    private Spinner spnGenero;
    private ListView listFilme;
    private ArrayAdapter<Filme> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmes = new ArrayList<>();
        edtNome = findViewById(R.id.edtFilme);
        edtNota = findViewById(R.id.edtNota);
        spnGenero = findViewById(R.id.spnGenero);
        listFilme = findViewById(R.id.listFilme);

        adapter = new FilmeAdapter(getApplicationContext(), R.layout.filme_item, filmes);
        listFilme.setAdapter(adapter);
    }

    public void salvar(View view){
        String nome = edtNome.getText().toString();
        int nota = Integer.parseInt(edtNota.getText().toString());
        String genero = spnGenero.getSelectedItem().toString();
        Filme filme = new Filme();

        filme.setNome(nome);
        filme.setNota(nota);
        filme.setGenero(genero);

        filmes.add(filme);

        adapter.sort(new Comparator<Filme>() {
            @Override
            public int compare(Filme o1, Filme o2) {
                return o2.getNota().compareTo(o1.getNota());
            }
        });

        adapter.notifyDataSetChanged();
    }
}
