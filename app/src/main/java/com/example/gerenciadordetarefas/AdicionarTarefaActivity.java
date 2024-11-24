package com.example.gerenciadordetarefas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private EditText editTextNomeTarefa;
    private EditText editTextDescricaoTarefa;
    private EditText editTextDataTermino;
    private Button botaoSalvar;
    private Button botaoCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTextNomeTarefa = findViewById(R.id.editTextNomeTarefa);
        editTextDescricaoTarefa = findViewById(R.id.editTextDescricaoTarefa);
        editTextDataTermino = findViewById(R.id.editTextDataTermino);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        botaoCancelar = findViewById(R.id.botaoCancelar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarTarefa();
            }
        });

        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarTarefa();
            }
        });
    }

    private void salvarTarefa() {
        String nomeTarefa = editTextNomeTarefa.getText().toString().trim();
        String descricaoTarefa = editTextDescricaoTarefa.getText().toString().trim();
        String dataTermino = editTextDataTermino.getText().toString().trim();

        if (nomeTarefa.isEmpty()) {
            editTextNomeTarefa.setError("Nome da tarefa é obrigatório");
            return;
        }

        if (descricaoTarefa.isEmpty()) {
            editTextDescricaoTarefa.setError("Descrição da tarefa é obrigatória");
            return;
        }

        if (dataTermino.isEmpty()) {
            editTextDataTermino.setError("Data de término é obrigatória");
            return;
        }

        // Lógica para salvar a tarefa (ex: salvar no banco de dados)
        Intent resultIntent = new Intent();
        resultIntent.putExtra("nomeTarefa", nomeTarefa);
        resultIntent.putExtra("descricaoTarefa", descricaoTarefa);
        resultIntent.putExtra("dataTermino", dataTermino);
        resultIntent.putExtra("progresso", 0); // Progresso inicial é 0
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void cancelarTarefa() {
        setResult(RESULT_CANCELED);
        finish();
    }
}