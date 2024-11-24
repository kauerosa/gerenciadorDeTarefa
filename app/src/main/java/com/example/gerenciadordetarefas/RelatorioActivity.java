package com.example.gerenciadordetarefas;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RelatorioActivity extends AppCompatActivity {

    private ListView listViewConcluidas;
    private ListView listViewIncompletas;
    private TarefaAdapter tarefaAdapterConcluidas;
    private TarefaAdapter tarefaAdapterIncompletas;
    private ArrayList<Tarefa> listaTarefasConcluidas;
    private ArrayList<Tarefa> listaTarefasIncompletas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        listViewConcluidas = findViewById(R.id.listViewConcluidas);
        listViewIncompletas = findViewById(R.id.listViewIncompletas);

        listaTarefasConcluidas = new ArrayList<>();
        listaTarefasIncompletas = new ArrayList<>();

        // Receber as listas de tarefas da MainActivity
        ArrayList<Tarefa> listaTarefas = (ArrayList<Tarefa>) getIntent().getSerializableExtra("listaTarefas");

        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getProgresso() == 100) {
                listaTarefasConcluidas.add(tarefa);
            } else {
                listaTarefasIncompletas.add(tarefa);
            }
        }

        tarefaAdapterConcluidas = new TarefaAdapter(this, listaTarefasConcluidas);
        tarefaAdapterIncompletas = new TarefaAdapter(this, listaTarefasIncompletas);

        listViewConcluidas.setAdapter(tarefaAdapterConcluidas);
        listViewIncompletas.setAdapter(tarefaAdapterIncompletas);
    }
}
