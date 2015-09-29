package com.nansoft.prored.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 16/09/2015.
 */
public class Red {

    @SerializedName("id")
    private String Id;

    @SerializedName("nombre")
    private String Nombre;

    @SerializedName("descripcion")
    private String Descripcion;

    @SerializedName("urlimagen")
    private String UrlImage;

    @SerializedName("idencargado")
    private String Id_Encargado;

    public String getId(){
        return Id;
    }
    public void setId(String pId){
        Id=pId;
    }
    public String getNombre(){
        return Nombre;
    }
    public void setNombre(String pNombre){
        Nombre=pNombre;
    }
    public String getDescripcion(){
        return Descripcion;
    }
    public void setDescripcion(String pDescripcion){
        Descripcion=pDescripcion;
    }
    public String getUrlImage(){
        return UrlImage;
    }
    public void setUrlImage(String pUrlImage){
        UrlImage=pUrlImage;
    }
    public String getId_Encargado(){
        return Id_Encargado;
    }
    public void setId_Encargado(String pId_Encargado){
        Id_Encargado=pId_Encargado;
    }


    //Constructores
    public Red(){
        Id="";
        Nombre="";
        Descripcion="";
        UrlImage="";
        Id_Encargado="";
    }
}