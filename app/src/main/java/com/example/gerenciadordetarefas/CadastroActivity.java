package com.example.gerenciadordetarefas;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomeCompletoEditText;
    private EditText emailEditText;
    private EditText senhaEditText;
    private EditText confirmarSenhaEditText;
    private Button botaoCadastrar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nomeCompletoEditText = findViewById(R.id.nomeCompleto);
        emailEditText = findViewById(R.id.email);
        senhaEditText = findViewById(R.id.senha);
        confirmarSenhaEditText = findViewById(R.id.confirmarSenha);
        botaoCadastrar = findViewById(R.id.botaoCadastrar);
        databaseHelper = new DatabaseHelper(this);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
        String nomeCompleto = nomeCompletoEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String senha = senhaEditText.getText().toString().trim();
        String confirmarSenha = confirmarSenhaEditText.getText().toString().trim();

        if (TextUtils.isEmpty(nomeCompleto)) {
            nomeCompletoEditText.setError("Nome completo é obrigatório");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email é obrigatório");
            return;
        }

        if (TextUtils.isEmpty(senha)) {
            senhaEditText.setError("Senha é obrigatória");
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            confirmarSenhaEditText.setError("As senhas não coincidem");
            return;
        }

        boolean isInserted = databaseHelper.adicionarUsuario(nomeCompleto, email, senha);
        if (isInserted) {
            Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao registrar usuário", Toast.LENGTH_SHORT).show();
        }
    }
}