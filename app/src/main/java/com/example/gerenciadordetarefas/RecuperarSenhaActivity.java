package com.example.gerenciadordetarefas;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private EditText editTextEmailRecuperacao;
    private Button botaoEnviarRecuperacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        editTextEmailRecuperacao = findViewById(R.id.editTextEmailRecuperacao);
        botaoEnviarRecuperacao = findViewById(R.id.botaoEnviarRecuperacao);

        botaoEnviarRecuperacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarRecuperacao();
            }
        });
    }

    private void enviarRecuperacao() {
        String email = editTextEmailRecuperacao.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmailRecuperacao.setError("Email é obrigatório");
            return;
        }

        // Lógica para enviar o email de recuperação de senha
        // Exemplo: enviar um email com um link para redefinir a senha
        Toast.makeText(this, "Email de recuperação enviado!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
