package com.example.ivan_.almacenamientoconsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtIFE, edtNombre, edtLugar, edtMesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtIFE=(EditText)findViewById(R.id.edtIfe);
        edtNombre=(EditText)findViewById(R.id.edtNombre);
        edtLugar=(EditText)findViewById(R.id.edtLugar);
        edtMesa=(EditText)findViewById(R.id.edtMesa);

    }
    public void alta(View v) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String ife=edtIFE.getText().toString();
        String nombre=edtNombre.getText().toString();
        String lugar=edtLugar.getText().toString();
        String nromesa=edtMesa.getText().toString();
        ContentValues registro=new ContentValues();
        registro.put("IFE",ife );
        registro.put("nombre",nombre );
        registro.put("lugar",lugar );
        registro.put("nromesa",nromesa );
        bd.insert("votantes", null, registro);
        bd.close();
        edtIFE.setText("");
        edtNombre.setText("");
        edtLugar.setText("");
        edtMesa.setText("");
        Toast.makeText(this, "Se cargaron los datos de la persona",
                Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String ife=edtIFE.getText().toString();
        Cursor fila=bd.rawQuery("select nombre,lugar,nromesa  from votantes where ife="+ife+"",null);
        if (fila.moveToFirst())
        {
            edtNombre.setText(fila.getString(0));
            edtLugar.setText(fila.getString(1));
            edtMesa.setText(fila.getString(2));
        }
        else
            Toast.makeText(this, "No existe una persona con dicho IFE", Toast.LENGTH_SHORT).show();
                    bd.close();

    }
    public void baja(View v) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String ife=edtIFE.getText().toString();
        int cant=bd.delete("votantes", "ife="+ife+"",null);
        bd.close();
        edtIFE.setText("");
        edtNombre.setText("");
        edtLugar.setText("");
        edtMesa.setText("");
        if (cant==1)
            Toast.makeText(this, "Se borró la persona con dicho IFE", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una persona con dicho IFE", Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String ife=edtIFE.getText().toString();
        String nombre=edtNombre.getText().toString();
        String lugar=edtLugar.getText().toString();
        String nromesa=edtMesa.getText().toString();
        ContentValues registro=new ContentValues();
        registro.put("nombre",nombre);
        registro.put("colegio",lugar);
        registro.put("nromesa",nromesa);
        int cant = bd.update("votantes", registro, "ife="+ife, null);
        bd.close();
        if (cant==1)
            Toast.makeText(this, "se modificaron los datos",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "no existe una persona con dicho IFE", Toast.LENGTH_SHORT).show();
    }


}
