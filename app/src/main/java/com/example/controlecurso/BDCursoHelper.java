package com.example.controlecurso;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDCursoHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "CursosOline.db";
    private static final String TABLE_NAME = "Curso";
    private static final String COLUM_CURSOID = "cursoId";
    private static final String COLUM_NOMECURSO = "nomeCurso";
    private static final String COLUM_QTDHORAS = "qtdeHoras";
    SQLiteDatabase db;

    private static final String TABLE_CREATE="create table Curso" + "(cursoId integer primary Key autoincrement, " +
            "nomeCurso varchar (100), qtdeHoras integer)";

    public BDCursoHelper(Context context){
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

    public long insereAlunos(Curso c){
        long retornoDB;
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_NOMECURSO,c.getNomeCurso());
        values.put(COLUM_QTDHORAS, c.getQtdHoras ());
        retornoDB = db.insert(TABLE_NAME, null, values);
        String res=Long.toString(retornoDB);
        Log.i("DBCursoHelper",res);
        db.close();
        return retornoDB;
    }
}
