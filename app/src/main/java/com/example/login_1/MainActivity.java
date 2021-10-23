package com.example.login_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private EditText id;
    private EditText user;
    private EditText password;
    private EditText name;
    private EditText status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id       = (EditText)findViewById(R.id.txtId);
        user     = (EditText)findViewById(R.id.txtUser);
        password = (EditText)findViewById(R.id.txtPassword);
        name     = (EditText)findViewById(R.id.txtName);
        status   = (EditText)findViewById(R.id.txtStatus);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertar(View view){
        DB classDB = new DB(MainActivity.this, "DBLogin", null, 2);
        SQLiteDatabase db = classDB.getWritableDatabase();
        String userParam   = user.getText().toString();
        String passParam   = password.getText().toString();
        String nameParam   = name.getText().toString();
        String statusParam = status.getText().toString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        ContentValues data = new ContentValues();
        data.put("user", userParam);
        data.put("pass", passParam);
        data.put("name", nameParam);
        data.put("status", statusParam);
        data.put("create_at", dateTimeFormatter.format(LocalDateTime.now()));
        db.insert("users", null, data);
        db.close();
        Toast.makeText(MainActivity.this, "Usuario insertado", Toast.LENGTH_SHORT).show();
    }

    public void eliminar(View view){
        DB classDB = new DB(MainActivity.this, "DBLogin", null, 2);
        SQLiteDatabase db = classDB.getWritableDatabase();
        int id_user = Integer.parseInt(id.getText().toString());
        db.delete("users", "id_user=" + id_user, null);
        db.close();
        Toast.makeText(MainActivity.this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void modificar(View view){
        DB classDB = new DB(MainActivity.this, "DBLogin", null, 2);
        SQLiteDatabase db = classDB.getWritableDatabase();
        int id_user = Integer.parseInt(id.getText().toString());
        String userParam   = user.getText().toString();
        String passParam   = password.getText().toString();
        String nameParam   = name.getText().toString();
        String statusParam = status.getText().toString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        ContentValues data = new ContentValues();
        data.put("user", userParam);
        data.put("pass", passParam);
        data.put("name", nameParam);
        data.put("status", statusParam);
        data.put("create_at", dateTimeFormatter.format(LocalDateTime.now()));
        db.update("users", data, "id_user=" + id_user, null);
        db.close();
            Toast.makeText(MainActivity.this, "Usuario Modificado", Toast.LENGTH_SHORT).show();
    }

    public String datos(){
        DB classDB = new DB(MainActivity.this, "DBLogin", null, 2);
        SQLiteDatabase db = classDB.getReadableDatabase();
        int id_user = Integer.parseInt(id.getText().toString());
        String result;
        Cursor cursor = db.rawQuery(
                "SELECT id_user, user, name, status FROM users WHERE id_user =" + id_user, null
        );
        if (cursor.moveToFirst()){
            result = cursor.getString(0).concat(" - ")
                    .concat(cursor.getString(1).concat(" - ")
                            .concat(cursor.getString(2)).concat(" - ")
                            .concat(cursor.getString(3)));
        } else {
            result = "No se encontro al usuario";
        }
        db.close();
        return result;
    }

    public void mostrar(View view){
        Toast.makeText(MainActivity.this, datos(), Toast.LENGTH_SHORT).show();
    }
}
