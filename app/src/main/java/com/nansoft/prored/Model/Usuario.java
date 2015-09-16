package com.nansoft.prored.Model;

/**
 * Created by PC on 16/09/2015.
 */
public class Usuario {
    private String Id;
    private String Nombre;
    private String PrimerApellido;
    private String SegundoApellido;
    private String Biografia;
    private String UrlImagen;
    private String Email;
    private String Telefono;

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
}
