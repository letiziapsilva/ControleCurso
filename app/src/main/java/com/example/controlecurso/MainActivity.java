package com.example.controlecurso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
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

        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);
        btnNovoCadastro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastroAluno.class);
                startActivity(it);
            }
        });

        listAluno = findViewById(R.id.listAlunos);
        registerForContextMenu(listAluno);

        listAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);

                Intent it = new Intent(MainActivity.this, CadastroAluno.class);
                it.putExtra("aluno-escolhido", alunoEscolhido);
                startActivity(it);
            }
        });

        listAluno.setOnLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                aluno = (Aluno) adapterView.getItemAtPosition(position);
                return false;
                }
            });
        }

        public void onCreatContextMenu (ContextMenu menu, View view, ContextMenu.ContextMenuInfo
        menuInfo){
            MenuItem menuDelete = menu.add("Deletar Aluno");
            menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    alunoHelper = new BDAlunoHelper(MainActivity.this);
                    alunoHelper.deletarAluno(aluno);
                    preencherLista();
                    alunoHelper.close();

                    return true;
                }
            });
        }

        protected void onResume () {
            super.onResume();
            preencherLista();
        }

        public void preencherLista () {
            alunoHelper = new BDAlunoHelper(MainActivity.this);
            arrayList = alunoHelper.selecionaTodosAlunos();
            alunoHelper.close();

            if (arrayList != null) {
                arrayAdapter = new ArrayAdapter<Aluno>(MainActivity.this,
                        android.R.layout.simple_list_item_1, arrayList);
                listAluno.setAdapter(arrayAdapter);
            }
        }
    }