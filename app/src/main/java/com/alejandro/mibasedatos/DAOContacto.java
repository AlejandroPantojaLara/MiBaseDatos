package com.alejandro.mibasedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOContacto {
    SQLiteDatabase _sqlSqLiteDatabase;

    public DAOContacto(Context ctx) {
        MiDB miDB = new MiDB(ctx);
        _sqlSqLiteDatabase = miDB.getWritableDatabase();
    }

    public long insert(Contacto contacto) {
        ContentValues cv = new ContentValues();
        cv.put(MiDB.COLUMNS_CONTACTOS[1], contacto.getUsuario());
        cv.put(MiDB.COLUMNS_CONTACTOS[2], contacto.getEmail());
        cv.put(MiDB.COLUMNS_CONTACTOS[3], contacto.getTel());
        cv.put(MiDB.COLUMNS_CONTACTOS[4], contacto.getFecha_nacimiento());
        return _sqlSqLiteDatabase.insert(MiDB.TABLE_NAME_CONTACTOS, null, cv);
    }

    public int update(Contacto contacto) {
        ContentValues cv = new ContentValues();
        cv.put(MiDB.COLUMNS_CONTACTOS[1], contacto.getUsuario());
        cv.put(MiDB.COLUMNS_CONTACTOS[2], contacto.getEmail());
        cv.put(MiDB.COLUMNS_CONTACTOS[3], contacto.getTel());
        cv.put(MiDB.COLUMNS_CONTACTOS[4], contacto.getFecha_nacimiento());

        String idObjetivo = "_id = ?";
        String[] argumentosParaActualizar = {String.valueOf(contacto.getId())};
        return _sqlSqLiteDatabase.update(MiDB.TABLE_NAME_CONTACTOS, cv, idObjetivo, argumentosParaActualizar);
    }

    public int delete(Contacto contacto) {
        String[] argumentosParaEliminar = {String.valueOf(contacto.getId())};
        return _sqlSqLiteDatabase.delete(MiDB.TABLE_NAME_CONTACTOS, "_id = ?", argumentosParaEliminar);
    }

    public Cursor filter(String inputText, String filterColumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM " + MiDB.TABLE_NAME_CONTACTOS;
        if (inputText == null || inputText.length() == 0) {
            row = _sqlSqLiteDatabase.rawQuery(query, null);
        } else {
            query = "SELECT * FROM " + MiDB.TABLE_NAME_CONTACTOS + " WHERE " + filterColumn + " like '%" + inputText + "%'";
            row = _sqlSqLiteDatabase.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }

    public List<Contacto> getAll() {
        List<Contacto> lst = null;
        Cursor c = _sqlSqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS, MiDB.COLUMNS_CONTACTOS,
                null, null, null, null, null);
        if (c.moveToFirst()) {
            lst = new ArrayList<Contacto>();
            do {
                Contacto ctc = new Contacto(c.getInt(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4));
                lst.add(ctc);
            } while (c.moveToNext());
        }
        return lst;
    }

    public Cursor getAllCursor() {
        Cursor c = _sqlSqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS, MiDB.COLUMNS_CONTACTOS,
                null, null, null, null, null);
        return c;
    }
}
