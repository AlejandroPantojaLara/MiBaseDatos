package com.alejandro.mibasedatos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarContactoActivity extends AppCompatActivity {
    EditText etUsuarioE, etMailE, etTelefonoE, etFechaNacE;
    Button btnActualizarE, btnCancelarE;
    Contacto contacto;
    com.alejandro.mibasedatos.DAOContacto daoContacto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etUsuarioE = findViewById(R.id.txtUsuarioEditar);
        etMailE = findViewById(R.id.txtEmailEditar);
        etTelefonoE = findViewById(R.id.txtTelefonoEditar);
        etFechaNacE = findViewById(R.id.txtFechaNacimientoEditar);
        btnActualizarE = findViewById(R.id.btnActualizarEditar);
        btnCancelarE = findViewById(R.id.btnCancelarEditar);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        daoContacto = new com.alejandro.mibasedatos.DAOContacto(com.alejandro.mibasedatos.EditarContactoActivity.this);

        final int idContacto = extras.getInt("_id");
        String usuarioContacto = extras.getString("usuario");
        String emailContacto = extras.getString("email");
        String telefonoContacto = extras.getString("tel");
        String fechaNacContacto = extras.getString("fecha_nacimiento");

        contacto = new Contacto(idContacto, usuarioContacto, emailContacto, telefonoContacto, fechaNacContacto);

        // Llenamos los campos Text con los datos del contacto.
        etUsuarioE.setText(String.valueOf(contacto.getUsuario()));
        etMailE.setText(String.valueOf(contacto.getEmail()));
        etTelefonoE.setText(String.valueOf(contacto.getTel()));
        etFechaNacE.setText(String.valueOf(contacto.getFecha_nacimiento()));

        // Listener para el botón de cancelar, para cerrar la actividad.
        btnCancelarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Listener del botón actualizar para guardar los cambios.
        btnActualizarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsuarioE.setError(null);
                etMailE.setError(null);
                etTelefonoE.setError(null);
                etFechaNacE.setError(null);

                //Crea el contacto con los nuevos cambios pero con el ID anterior.
                String nuevoUsuario = etUsuarioE.getText().toString();
                String nuevoMail = etMailE.getText().toString();
                String nuevoTelefono = etTelefonoE.getText().toString();
                String nuevaFechaNac = etFechaNacE.getText().toString();

                if (nuevoUsuario.isEmpty()) {
                    etUsuarioE.setError("Escribe el nuevo usuario");
                    etUsuarioE.requestFocus();
                    return;
                }
                if (nuevoMail.isEmpty()) {
                    etMailE.setError("Escribe el nuevo email");
                    etMailE.requestFocus();
                    return;
                }
                if (nuevoTelefono.isEmpty()) {
                    etTelefonoE.setError("Escribe el nuevo teléfono");
                    etTelefonoE.requestFocus();
                    return;
                }
                if (nuevaFechaNac.isEmpty()) {
                    etFechaNacE.setError("Escribe la nueva fecha de nacimiento");
                    etFechaNacE.requestFocus();
                    return;
                }

                // Actualizamos los datos de contacto.
                Contacto contactoActualizado = new Contacto((idContacto), nuevoUsuario, nuevoMail, nuevoTelefono, nuevaFechaNac);
                int filasModificadas = daoContacto.update(contactoActualizado);
                if (filasModificadas != 1) {
                    Toast.makeText(com.alejandro.mibasedatos.EditarContactoActivity.this, "Error al actualizar el contacto", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });
    }
}
