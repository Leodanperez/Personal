package com.example.personal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.personal.R;
import com.example.personal.models.PersonaModel;
import com.example.personal.interfaces.onItemPersonalClickListener;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonalViewHolder>{

    //paso 2: definimos el metodo ke representa la clase
    private onItemPersonalClickListener onItemPersonalClick;

    //paso 3: obtener el contexto de la clase
    public PersonaAdapter(onItemPersonalClickListener onItemPersonalClick){
        this.onItemPersonalClick = onItemPersonalClick;
    }

    List<PersonaModel>listaRecogida = new ArrayList<>();
    public void addListPersonal(List<PersonaModel> listP){
        this.listaRecogida = listP;
        notifyDataSetChanged();
    }

    @Override
    public PersonaAdapter.PersonalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent
        ,false);
        return new PersonalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonalViewHolder holder, int position) {
        final PersonaModel personaM = listaRecogida.get(position);
        holder.lblName.setText(personaM.getNombre());
        holder.lblApellido.setText(personaM.getApellido());
        holder.lblDni.setText(personaM.getDni());
        holder.lblEdad.setText(personaM.getEdad());
        //holder.lblEdad.setText(personaM.getEdad());

        //paso 4: llamamoa a los metodos eliminar actualizar
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemPersonalClick != null)
                {
                    onItemPersonalClick.onItemClickDelete(personaM);
                }
            }
        });

        //update
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemPersonalClick != null)
                {
                    onItemPersonalClick.onItemClikUpdate(personaM);
                }
            }
        });

        //update

    }

    @Override
    public int getItemCount() {
        return listaRecogida == null ? 0 :listaRecogida.size();
    }

    //sub clase
    class PersonalViewHolder extends RecyclerView.ViewHolder{

        TextView lblName, lblApellido, lblDni, lblEdad;
        Button btnDelete;

        public PersonalViewHolder(View itemView) {
            super(itemView);
            lblName = itemView.findViewById(R.id.lblNombre);
            lblApellido = itemView.findViewById(R.id.lblApellido);
            lblDni = itemView.findViewById(R.id.lblDni);
            lblEdad = itemView.findViewById(R.id.lblEdad);
            btnDelete = itemView.findViewById(R.id.butonEliminar);
        }
    }

    /*public interface onItemPersonalClickListener {
        //paso 1: metos para eliminar y editar
        void onItemClickDelete(PersonaModel personaModel);
        void onItemClikUpdate(PersonaModel personaModel);
    }*/
}
