package com.example.personal.interfaces;

import com.example.personal.models.PersonaModel;

public interface onItemPersonalClickListener {
    //paso 1: metos para eliminar y editar
    void onItemClickDelete(PersonaModel personaModel);
    void onItemClikUpdate(PersonaModel personaModel);
}
