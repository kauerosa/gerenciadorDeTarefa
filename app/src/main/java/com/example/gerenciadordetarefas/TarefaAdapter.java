package com.example.gerenciadordetarefas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TarefaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Tarefa> listaTarefas;
    private DatabaseHelper databaseHelper;

    public TarefaAdapter(Context context, ArrayList<Tarefa> listaTarefas) {
        this.context = context;
        this.listaTarefas = listaTarefas;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return listaTarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);
        }

        Tarefa tarefa = listaTarefas.get(position);

        TextView nomeTarefa = convertView.findViewById(R.id.nomeTarefa);
        TextView descricaoTarefa = convertView.findViewById(R.id.descricaoTarefa);
        TextView dataTermino = convertView.findViewById(R.id.dataTermino);
        ProgressBar progressoTarefa = convertView.findViewById(R.id.progressoTarefa);
        Button botaoExcluir = convertView.findViewById(R.id.botaoExcluir);
        Button botaoVoltar = convertView.findViewById(R.id.botaoVoltar);

        nomeTarefa.setText(tarefa.getNome());
        descricaoTarefa.setText(tarefa.getDescricao());
        dataTermino.setText(tarefa.getDataTermino());
        progressoTarefa.setProgress(tarefa.getProgresso());

        botaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = databaseHelper.excluirTarefa(tarefa.getId());
                if (isDeleted) {
                    listaTarefas.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Erro ao excluir tarefa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para voltar à tela principal
                // Neste caso, não é necessário fazer nada, pois o botão de voltar
                // está na própria lista de tarefas da tela principal.
            }
        });

        return convertView;
    }
}