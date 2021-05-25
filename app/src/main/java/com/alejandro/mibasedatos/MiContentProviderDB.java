package com.alejandro.mibasedatos;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MiContentProviderDB extends ContentProvider {

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    SQLiteDatabase sqLiteDatabase;

    static {
        uriMatcher.addURI("com.alejandro.mibasedatos.provider", "contactos", 1);

        uriMatcher.addURI("com.alejandro.mibasedatos.provider", "contactos/#", 2);

        uriMatcher.addURI("com.alejandro.mibasedatos.provider", "contactos/*", 3);
    }

    public MiContentProviderDB() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.

        int result = 0;

        switch (uriMatcher.match(uri)) {
            case 1:
                result = sqLiteDatabase.delete(com.alejandro.mibasedatos.MiDB.TABLE_NAME_CONTACTOS, selection, selectionArgs
                );
                break;
        }

        return result;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        String result = "";

        switch (uriMatcher.match(uri)) {
            case 1:
                result = "vnd.android.cursor.dir/vnd.com.alejandro.mibasedatos.provider.contactos";
                break;
            case 2:
                result = "vnd.android.cursor.item/vnd..com.alejandro.mibasedatos.provider.contactos";
                break;
        }

        return result;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Uri result = null;

        switch (uriMatcher.match(uri)) {
            case 1:
                result = Uri.withAppendedPath(uri, String.valueOf(sqLiteDatabase.insert(com.alejandro.mibasedatos.MiDB.TABLE_NAME_CONTACTOS, null, values)));
                break;
        }

        return result;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        sqLiteDatabase = new com.alejandro.mibasedatos.MiDB(this.getContext()).getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        Cursor c = null;

        switch (uriMatcher.match(uri)) {
            case 1:
                c = sqLiteDatabase.query(com.alejandro.mibasedatos.MiDB.TABLE_NAME_CONTACTOS, projection, null, null, null, null, null);
                break;
            case 2:
                c = sqLiteDatabase.query(com.alejandro.mibasedatos.MiDB.TABLE_NAME_CONTACTOS, projection, com.alejandro.mibasedatos.MiDB.COLUMNS_CONTACTOS[0] + "=?", new String[]{uri.getLastPathSegment()}, null, null, null);
                break;
        }

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.

        int result = 0;

        switch (uriMatcher.match(uri)) {
            case 1:
                result = sqLiteDatabase.update(
                        com.alejandro.mibasedatos.MiDB.TABLE_NAME_CONTACTOS,
                        values,
                        selection,
                        selectionArgs
                );
                break;
        }

        return result;
    }
}
