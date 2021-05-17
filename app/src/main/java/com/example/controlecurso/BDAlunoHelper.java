package com.example.controlecurso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class BDAlunoHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "CursosOline.db";
    private static final String TABLE_NAME = "Aluno";
    private static final String COLUM_ALUNOID = "alunoId";
    private static final String COLUM_NOMEALUNO = "nomeAluno";
    private static final String COLUM_CPF = "cpf";
    private static final String COLUM_EMAIL = "email";
    private static final String COLUM_TELEFONE ="telefone";
    SQLiteDatabase db;

    private static final String TABLE_CREATE="create table Aluno" + "(alunoId integer primary Key autoincrement, " +
            "nomeAluno varchar (100),cpf varchar (11), " +
            "email varchar (20),telefone varchar (11) )";

    public BDAlunoHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(TABLE_CREATE);
    this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
    db.execSQL(query);
    onCreate(db);
    }

    public long insereAlunos(Aluno a){
        long retornoDB;
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_NOMEALUNO,a.getNomeAluno());
        values.put(COLUM_CPF, a.getCpf());
        values.put(COLUM_EMAIL, a.getEmail());
        values.put(COLUM_TELEFONE, a.getTelefone());
        retornoDB = db.insert(TABLE_NAME, null, values);
        String res=Long.toString(retornoDB);
        Log.i("DBAlunoHelper",res);
        db.close();
        return retornoDB;
    }

    public long editarAluno(Aluno a){
        long retornoDB;
        db=this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUM_NOMEALUNO,a.getNomeAluno());
        values.put(COLUM_CPF, a.getCpf());
        values.put(COLUM_EMAIL, a.getEmail());
        values.put(COLUM_TELEFONE, a.getTelefone());
        retornoDB = db.update(TABLE_NAME, values, "id=?", null);
        String res=Long.toString(retornoDB);
        Log.i("DBAlunoHelper", res);

        db.close();
        return retornoDB;
    }

    public long deletarAluno(Aluno a){
        long retornoDB;
        db=this.getWritableDatabase();

        retornoDB = db.delete(TABLE_NAME, "id=?", null);
        String res=Long.toString(retornoDB);
        Log.i("DBAlunoHelper", res);

        db.close();
        return retornoDB;
    }

    public ArrayList<Aluno> selecionaTodosAlunos (){
        String [] coluns={COLUM_ALUNOID, COLUM_NOMEALUNO, COLUM_CPF,COLUM_EMAIL,COLUM_TELEFONE};
        Cursor cursor=getReadableDatabase().query(TABLE_NAME,
                coluns, null,null,null, null, "upper(nome)", null);
        ArrayList<Aluno> listAluno = new ArrayList<Aluno>();
        while (cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setAlunoId(cursor.getInt(0));
            a.setNomeAluno(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setEmail(cursor.getString(3));
            a.setTelefone(cursor.getString(4));
            listAluno.add(a);
        }
return listAluno;
    }
}
