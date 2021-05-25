package com.alejandro.mibasedatos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarContactoActivity extends AppCompatActivity {
    EditText etUsuarioA, etMailA, etTelefonoA, etFechaNacA;
    Button btnAgregarA, btnCancelarA;
    DAOContacto dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etUsuarioA = findViewById(R.id.txtUsuarioCrear);
        etMailA = findViewById(R.id.txtEmailCrear);
        etTelefonoA = findViewById(R.id.txtTelefonoCrear);
        etFechaNacA = findViewById(R.id.txtFechaNacimientoCrear);
        btnAgregarA = findViewById(R.id.btnAgregarCrear);
        btnCancelarA = findViewById(R.id.btnCancelarCrear);

        dao = new DAOContacto(com.alejandro.mibasedatos.AgregarContactoActivity.this);

        btnAgregarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsuarioA.setError(null);
                etMailA.setError(null);
                etTelefonoA.setError(null);
                etFechaNacA.setError(null);

                String usuario = etUsuarioA.getText().toString(),
                        email = etMailA.getText().toString(),
                        telefono = etTelefonoA.getText().toString(),
                        fechaNac = etFechaNacA.getText().toString();

                if ("".equals(usuario)) {
                    etUsuarioA.setError("Escribre el nombre del usuario");
                    etUsuarioA.requestFocus();
                    return;
                }
                if ("".equals(email)) {
                    etMailA.setError("Escribre el email del usuario");
                    etMailA.requestFocus();
                    return;
                }
                if ("".equals(telefono)) {
                    etTelefonoA.setError("Escribre el teléfono del usuario");
                    etTelefonoA.requestFocus();
                    return;
                }
                if ("".equals(fechaNac)) {
                    etFechaNacA.setError("Escribre la fecha de nacimiento del usuario");
                    etFechaNacA.requestFocus();
                    return;
                }

                Contacto nuevoContacto = new Contacto(0, usuario, email, telefono, fechaNac);
                long id = dao.insert(nuevoContacto);

                if (id == -1) {
                    Toast.makeText(com.alejandro.mibasedatos.AgregarContactoActivity.this, "Error al guardar.\nIntentar de nuevo.", Toast.LENGTH_LONG).show();
                } else {
                    finish();
                }
            }
        });

        btnCancelarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
