package com.example.controlecurso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroAluno extends AppCompatActivity {
    EditText editText_IdAluno, editText_editAluno, editText_editCpf, editText_editEmail, editText_editTelefone;
    Button btnPolimorf;
    Aluno editarAluno, aluno;
    BDAlunoHelper alunoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        aluno = new Aluno();
        alunoHelper = new BDAlunoHelper(CadastroAluno.this);

        Intent intent = getIntent();
        editarAluno = (Aluno) intent.getSerializableExtra("aluno-escolhido");

        editText_IdAluno = (EditText) findViewById(R.id.IdAluno);
        editText_editAluno = (EditText) findViewById(R.id.editAluno);
        editText_editCpf = (EditText) findViewById(R.id.editCpf);
        editText_editEmail = (EditText) findViewById(R.id.editEmail);
        editText_editTelefone = (EditText) findViewById(R.id.editTelefone);

        btnPolimorf = (Button) findViewById(R.id.btnPolimorf);

        if(editarAluno != null) {
            btnPolimorf.setText("Editar");

            editText_IdAluno.setText(editarAluno.getAlunoId());
            editText_editAluno.setText(editarAluno.getAlunoId());
            editText_editCpf.setText(editarAluno.getAlunoId());
            editText_editEmail.setText(editarAluno.getAlunoId());
            editText_editTelefone.setText(editarAluno.getAlunoId() + "");

            aluno.setAlunoId(editarAluno.getAlunoId());
        }
        else{
            btnPolimorf.setText("Cadastrar");
        }

        btnPolimorf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aluno.setAlunoId(Integer.parseInt(editText_IdAluno.getText().toString()));
                aluno.setNomeAluno(editText_editAluno.getText().toString());
                aluno.setCpf(editText_editCpf.getText().toString());
                aluno.setEmail(editText_editEmail.getText().toString());
                aluno.setTelefone(editText_editTelefone.getText().toString());

                if (btnPolimorf.getText().toString().equals("Cadastrar")){
                    alunoHelper.insereAlunos(aluno);
                    alunoHelper.close();
                }else{
                    alunoHelper.editarAluno(aluno);
                    alunoHelper.close();
                }
            }
        });
    }
}
