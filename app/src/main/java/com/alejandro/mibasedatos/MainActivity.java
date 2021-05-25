package com.alejandro.mibasedatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Button btnCrear, btnBuscar;
    EditText txtFiltro;
    SimpleCursorAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final com.alejandro.mibasedatos.DAOContacto dao = new com.alejandro.mibasedatos.DAOContacto(this);

        lv = findViewById(R.id.Lv);
        btnCrear = findViewById(R.id.btnCrear);
        btnBuscar = findViewById(R.id.btnBuscar);
        txtFiltro = findViewById(R.id.txtBuscar);

        final Cursor c = dao.getAllCursor();

        refrescarLista(c);

        // Listener de los clicks en el ListView.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<com.alejandro.mibasedatos.Contacto> importar = dao.getAll();
                com.alejandro.mibasedatos.Contacto contactoSeleccionado = importar.get(position);
                Intent intent = new Intent(com.alejandro.mibasedatos.MainActivity.this, com.alejandro.mibasedatos.EditarContactoActivity.class);
                intent.putExtra("_id", contactoSeleccionado.getId());
                intent.putExtra("usuario", contactoSeleccionado.getUsuario());
                intent.putExtra("email", contactoSeleccionado.getEmail());
                intent.putExtra("tel", contactoSeleccionado.getTel());
                intent.putExtra("fecha_nacimiento", contactoSeleccionado.getFecha_nacimiento());
                startActivity(intent);
            }
        });

        // Listener de los clicks largos en el ListView
        //aqui es donde elimino
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                List<com.alejandro.mibasedatos.Contacto> importar = dao.getAll();
                final com.alejandro.mibasedatos.Contacto contactoParaEliminar = importar.get(position);

                AlertDialog dialog = new AlertDialog
                        .Builder(com.alejandro.mibasedatos.MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.delete(contactoParaEliminar);

                                Intent reconstruir = getIntent();
                                finish();
                                startActivity(reconstruir);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar al contacto " + contactoParaEliminar.getUsuario() + "?")
                        .create();
                dialog.show();
                return true;
            }
        });

        // Listener para el botón agregar.
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.alejandro.mibasedatos.MainActivity.this, com.alejandro.mibasedatos.AgregarContactoActivity.class);
                startActivity(intent);
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String obtenerTexto = txtFiltro.getText().toString();
                Cursor filtrarBusqueda = dao.filter(obtenerTexto, "usuario");
                refrescarLista(filtrarBusqueda);
            }
        });

        /*txtFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (MainActivity.this).adp.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        /*adp.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return dao.filter(constraint.toString(), "usuario");
            }
        });*/
    }

    public void refrescarLista(Cursor c) {
        adp = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_expandable_list_item_2,
                c,
                new String[]{"usuario", "email"},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        );
        lv.setAdapter(adp);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final com.alejandro.mibasedatos.DAOContacto dao = new com.alejandro.mibasedatos.DAOContacto(this);
        final Cursor c = dao.getAllCursor();
        refrescarLista(c);
    }
}
