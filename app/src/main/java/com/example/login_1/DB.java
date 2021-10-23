package com.example.login_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    //String SQLTblDataUser = "CREATE TABLE tblDataUser (nombre VARCHAR(100), edad INT, genero CHAR(1), fecha VARCHAR(10))";

    String users = "CREATE TABLE users (id_user INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, name TEXT,pass TEXT, status TEXT, create_at TEXT)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(users);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL(users);
    }
}