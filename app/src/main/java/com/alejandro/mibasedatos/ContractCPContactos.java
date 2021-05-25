package com.alejandro.mibasedatos;

import android.net.Uri;

public class ContractCPContactos {
    public static final Uri CONTENT_URI = Uri.parse("content://com.alejandro.mibasedatos.provider/contactos");

    public static final String FIELD_ID = "_id";
    public static final String FIELD_USUARIO = "usuario";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_TEL = "tel";
    public static final String FIELD_FECHANACIMIENTO = "fecha_nacimiento";

    public static final String[] PROJECTION = {"_id", "usuario", "email", "tel", "fecha_nacimiento"};
}
