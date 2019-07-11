package com.example.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personal.dao.PersonaDAO;
import com.example.personal.models.PersonaModel;

public class MainActivity extends AppCompatActivity {

    EditText txtCodigo,txtNombre,txtApellido,txtDni,txtEdad;
    //ListView lista;
    Button btnAction;

    PersonaDAO personaDAO;
    private boolean esActualizable = false;

    public static void start(Context context,PersonaModel personaModel) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra("persona",personaModel);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCodigo = findViewById(R.id.txt_codigo);
        txtNombre = findViewById(R.id.txt_nombre);
        txtApellido = findViewById(R.id.txt_apellido);
        txtDni = findViewById(R.id.txt_dni);
        txtEdad = findViewById(R.id.txt_edad);

        //lista = findViewById(R.id.listPersonal);
         btnAction = findViewById(R.id.btn_guardar);

        PersonaModel personaModel = getIntent().getParcelableExtra("persona");
        if (personaModel != null)
        {
            txtCodigo.setText(personaModel.getCodigo()+"");
            txtNombre.setText(personaModel.getNombre());
            txtApellido.setText(personaModel.getApellido());
            txtDni.setText(personaModel.getDni());
            txtEdad.setText(String.valueOf(personaModel.getEdad()));
            btnAction.setText("ACTUALIZAR PERSONA");
            esActualizable = true;
        }
        else
        {
            btnAction.setText("REGISTRAR PERSONA");
            Toast.makeText(this, "Aki esta el error "+btnAction, Toast.LENGTH_SHORT).show();
        }

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (esActualizable)
                {
                    updatePersona();
                }
                else
                {
                    registerPersona();
                }
            }
        });
    }

    private void registerPersona(){
        try{
            Integer codigoL = Integer.parseInt(txtCodigo.getText().toString());
            String nombreL = txtNombre.getText().toString();
            String apellidoL = txtApellido.getText().toString();
            String dniL = txtDni.getText().toString();
            Integer edadL = Integer.parseInt(txtDni.getText().toString());

            personaDAO = new PersonaDAO(this);
            PersonaModel personaModel = new PersonaModel();
            personaModel.setCodigo(codigoL);
            personaModel.setNombre(nombreL);
            personaModel.setApellido(apellidoL);
            personaModel.setDni(dniL);
            personaModel.setEdad(edadL);

            Long estado = personaDAO.registerPersona(personaModel);

            if (estado != -1){
                Toast.makeText(this, "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "OPPS!! Ocurrio un problema", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Error al registrar "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updatePersona(){
        try{
            int codigoL = Integer.parseInt(txtCodigo.getText().toString());
            String nombreL = txtNombre.getText().toString();
            String apellidoL = txtApellido.getText().toString();
            String dniL = txtDni.getText().toString();
            int edadL = Integer.parseInt(txtDni.getText().toString());

            personaDAO = new PersonaDAO(this);
            PersonaModel personaModel = new PersonaModel();
            personaModel.setCodigo(codigoL);
            personaModel.setNombre(nombreL);
            personaModel.setApellido(apellidoL);
            personaModel.setDni(dniL);
            personaModel.setEdad(edadL);

            int estado = personaDAO.updatePersona(personaModel);

            if (estado == 1){
                Toast.makeText(this, "Se Actualizo Correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "OPPS!! Ocurrio un problema", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Error al Actualizar "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
