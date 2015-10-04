package com.nansoft.prored.Model;

import com.google.gson.annotations.SerializedName;

import java.util.IdentityHashMap;

/**
 * Created by PC on 16/09/2015.
 */
public class Usuario {

    @SerializedName("id")
    private String Id;

    @SerializedName("nombre")
    private String Nombre;

    @SerializedName("primerapellido")
    private String PrimerApellido;

    @SerializedName("segundoapellido")
    private String SegundoApellido;




    private String Cargo;


    @SerializedName("biografia")
    private String Biografia;

    @SerializedName("urlimagen")
    private String UrlImagen;

    @SerializedName("email")
    private String Email;

    @SerializedName("telefono")
    private String Telefono;

    @SerializedName("direccion")
    private String Direccion;

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
    ////a
    public String getPrimerApellido(){
        return PrimerApellido;
    }
    public void setPrimerApellido(String pPrimerApellido){
        PrimerApellido=pPrimerApellido;
    }
    public String getSegundoApellido(){
        return SegundoApellido;
    }
    public void setSegundoApellido(String pSegundoApellido){
        SegundoApellido=pSegundoApellido;
    }
    public String getDireccion(){
        return Direccion;
    }
    public void setDireccion(String pDireccion){
        Direccion=pDireccion;
    }
    public String getCargo(){
        return "Administrador";
    }
    public void setCargo(String pcargo){
        Direccion=pcargo;
    }
    public String getBiografia(){
        return Biografia;
    }
    public void setBiografia(String pBiografia){
        Biografia=pBiografia;
    }
    public String getUrlImagen(){
        return UrlImagen;
    }
    public void setUrlImagen(String pUrlImagen){
        UrlImagen=pUrlImagen;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String pEmail){
        Email=pEmail;
    }
    public String getTelefono(){
        return Telefono;
    }
    public void setTelefono(String pTelefono){
        Telefono=pTelefono;
    }
//Constructor
    public Usuario(){
        Id = "";
        Nombre="";
        PrimerApellido="";
        SegundoApellido="";
        Direccion="";
        Cargo="";
        Biografia="";
        UrlImagen="";
        Email="";
        Telefono="";
    }




}
