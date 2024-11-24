package com.example.gerenciadordetarefas;


import java.io.Serializable;

public class Tarefa implements Serializable {
    private int id;
    private String nome;
    private String descricao;
    private String dataTermino;
    private int progresso;

    public Tarefa(int id, String nome, String descricao, String dataTermino, int progresso) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.progresso = progresso;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public int getProgresso() {
        return progresso;
    }
}