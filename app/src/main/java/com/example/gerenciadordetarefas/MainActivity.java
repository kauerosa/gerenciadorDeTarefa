package com.example.gerenciadordetarefas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewTarefas;
    private Button botaoAdicionar;
    private Button botaoRelatorio;
    private TarefaAdapter tarefaAdapter;
    private ArrayList<Tarefa> listaTarefas;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTarefas = findViewById(R.id.listViewTarefas);
        botaoAdicionar = findViewById(R.id.botaoAdicionar);
        botaoRelatorio = findViewById(R.id.botaoRelatorio);
        databaseHelper = new DatabaseHelper(this);

        listaTarefas = new ArrayList<>();
        tarefaAdapter = new TarefaAdapter(this, listaTarefas);
        listViewTarefas.setAdapter(tarefaAdapter);

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de adicionar tarefa
                Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        botaoRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de relatório
                Intent intent = new Intent(MainActivity.this, RelatorioActivity.class);
                intent.putExtra("listaTarefas", listaTarefas);
                startActivity(intent);
            }
        });

        // Carregar e exibir a lista de tarefas
        carregarTarefas();
    }

    private void carregarTarefas() {
        listaTarefas.clear();
        Cursor cursor = databaseHelper.obterTarefas();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
                String dataTermino = cursor.getString(cursor.getColumnIndex("data_termino"));
                int progresso = cursor.getInt(cursor.getColumnIndex("progresso"));

                Tarefa tarefa = new Tarefa(id, nome, descricao, dataTermino, progresso);
                listaTarefas.add(tarefa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        tarefaAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nomeTarefa = data.getStringExtra("nomeTarefa");
            String descricaoTarefa = data.getStringExtra("descricaoTarefa");
            String dataTermino = data.getStringExtra("dataTermino");
            int progresso = data.getIntExtra("progresso", 0);

            boolean isInserted = databaseHelper.adicionarTarefa(nomeTarefa, descricaoTarefa, dataTermino, progresso);
            if (isInserted) {
                carregarTarefas();
            } else {
                Toast.makeText(this, "Erro ao adicionar tarefa", Toast.LENGTH_SHORT).show();
            }
        }
    }
}