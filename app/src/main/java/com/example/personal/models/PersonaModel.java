package com.example.personal.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonaModel implements Parcelable {

    public static final String TABLE_NAME = "personas";
    public static final String CODIGO_FIEL = "codigo";
    public static final String NAME_FIELD = "nombre";
    public static final String APELLIDO_FIELD = "apellido";
    public static final String DNI_FIELD = "dni";
    public static final String EDAD_FIELD = "edad";

    private int codigo;
    private String nombre;
    private String apellido;
    private String dni;
    private String edad;

    public PersonaModel(){
    }

    protected PersonaModel(Parcel in) {
        codigo = in.readInt();
        nombre = in.readString();
        apellido = in.readString();
        dni = in.readString();
        edad = in.readString();
    }

    public static final Creator<PersonaModel> CREATOR = new Creator<PersonaModel>() {
        @Override
        public PersonaModel createFromParcel(Parcel in) {
            return new PersonaModel(in);
        }

        @Override
        public PersonaModel[] newArray(int size) {
            return new PersonaModel[size];
        }
    };

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(codigo);
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeString(dni);
        dest.writeString(edad);
    }
}
