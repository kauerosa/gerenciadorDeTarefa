package com.example.gerenciadordetarefas;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "meuapp.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela de usuários
    private static final String TABLE_USUARIOS = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_SENHA = "senha";

    // Tabela de tarefas
    private static final String TABLE_TAREFAS = "tarefas";
    private static final String COLUMN_TAREFA_ID = "id";
    private static final String COLUMN_TAREFA_NOME = "nome";
    private static final String COLUMN_TAREFA_DESCRICAO = "descricao";
    private static final String COLUMN_TAREFA_DATA_TERMINO = "data_termino";
    private static final String COLUMN_TAREFA_PROGRESSO = "progresso";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela de usuários
        String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLE_USUARIOS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_SENHA + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_USUARIOS);

        // Criação da tabela de tarefas
        String CREATE_TABLE_TAREFAS = "CREATE TABLE " + TABLE_TAREFAS + "("
                + COLUMN_TAREFA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TAREFA_NOME + " TEXT,"
                + COLUMN_TAREFA_DESCRICAO + " TEXT,"
                + COLUMN_TAREFA_DATA_TERMINO + " TEXT,"
                + COLUMN_TAREFA_PROGRESSO + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_TAREFAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAREFAS);
        onCreate(db);
    }

    // Métodos para gerenciar usuários
    public boolean adicionarUsuario(String nome, String email, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_SENHA, senha);

        long result = db.insert(TABLE_USUARIOS, null, values);
        db.close();
        return result != -1;
    }

    public boolean validarUsuario(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USUARIOS, null, COLUMN_EMAIL + "=? AND " + COLUMN_SENHA + "=?",
                new String[]{email, senha}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Métodos para gerenciar tarefas
    public boolean adicionarTarefa(String nome, String descricao, String dataTermino, int progresso) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TAREFA_NOME, nome);
        values.put(COLUMN_TAREFA_DESCRICAO, descricao);
        values.put(COLUMN_TAREFA_DATA_TERMINO, dataTermino);
        values.put(COLUMN_TAREFA_PROGRESSO, progresso);

        long result = db.insert(TABLE_TAREFAS, null, values);
        db.close();
        return result != -1;
    }

    public Cursor obterTarefas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_TAREFAS, null, null, null, null, null, null);
    }

    public boolean excluirTarefa(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_TAREFAS, COLUMN_TAREFA_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
}