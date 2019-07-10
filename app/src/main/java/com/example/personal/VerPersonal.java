package com.example.personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.personal.adapter.PersonaAdapter;
import com.example.personal.dao.PersonaDAO;
import com.example.personal.interfaces.onItemPersonalClickListener;
import com.example.personal.models.PersonaModel;

import java.util.List;

//paso 5: implementar los metodos de la clase PersonalAdapter
public class VerPersonal extends AppCompatActivity implements onItemPersonalClickListener {

    RecyclerView listPersona;
    PersonaDAO personaDAO;
    PersonaAdapter personaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personal);

        listPersona = findViewById(R.id.listPersonal);
        personaDAO = new PersonaDAO(this);
        personaAdapter = new PersonaAdapter(this);
        listPersona.setLayoutManager(new LinearLayoutManager(this));
        listPersona.setAdapter(personaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<PersonaModel> listPersonaExistente = personaDAO.listPersona();
        personaAdapter.addListPersonal(listPersonaExistente);
    }

    public void irRegistroPersona(View v){
        MainActivity.start(this,null);
    }

    @Override
    public void onItemClickDelete(PersonaModel personaModel) {
        int success = personaDAO.deletePersona(personaModel.getCodigo());
        if (success == 1)
        {
            Toast.makeText(this, "Se elimino Correctamente", Toast.LENGTH_SHORT).show();
            List<PersonaModel> listPersonaExistente = personaDAO.listPersona();
            personaAdapter.addListPersonal(listPersonaExistente);
        }
        else
        {
            Toast.makeText(this, "Ocurrio un problema", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClikUpdate(PersonaModel personaModel) {

        MainActivity.start(this,personaModel);
    }
}
