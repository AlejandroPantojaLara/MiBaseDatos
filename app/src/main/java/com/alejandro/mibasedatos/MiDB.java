package com.alejandro.mibasedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MiDB extends SQLiteOpenHelper {
    /* Variable constante por eso esta en mayúsculas. */
    final private String SCRIPT_TABLE_CONTACTOS = "CREATE TABLE contactos(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "usuario TEXT NOT NULL, " +
            "email TEXT NOT NULL, " +
            "tel TEXT NOT NULL, " +
            "fecha_nacimiento TEXT NOT NULL);";

    public static final String[] COLUMNS_CONTACTOS = {"_id", "usuario", "email", "tel", "fecha_nacimiento"};
    public static final String TABLE_NAME_CONTACTOS = "contactos";

    public MiDB(@Nullable Context context) {
        super(context, "DBFile", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_TABLE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
