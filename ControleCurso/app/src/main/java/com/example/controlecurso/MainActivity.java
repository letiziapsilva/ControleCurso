package com.example.controlecurso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listAluno;
    private Button btnNovoCadastro;
    Aluno aluno;
    BDAlunoHelper alunoHelper;
    ArrayList<Aluno> arrayList;
    ArrayAdapter<Aluno> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listAluno=findViewById(R.id.listAlunos);
        btnNovoCadastro=findViewById(R.id.btnNovoCadastro);
        btnNovoCadastro.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick (View view){
               Intent it = new Intent(MainActivity.this,
                       CadastroAluno.class);
               startActivity(it);
           }
        });
listAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
});

    }
    public void preencherLista(){
        alunoHelper= new BDAlunoHelper(MainActivity.this);
        arrayList = alunoHelper.selecionaTodosAlunos();
        alunoHelper.close();
        if(arrayList!= null){
            arrayAdapter=new ArrayAdapter<Aluno>(MainActivity.this,
                    android.R.layout.simple_list_item_1, arrayList);
            listAluno.setAdapter(arrayAdapter);
        }
    }
}