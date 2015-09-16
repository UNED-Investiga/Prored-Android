package com.nansoft.prored.Model;

import java.util.Date;

/**
 * Created by PC on 16/09/2015.
 */
public class Eventos {
    private String Id;
    private String Nombre;
    private String Descripcion;
    private String UrlImagen;
    private Date Fecha;
    private  String Costo;
    private String Direccion;

    public String getId(){
        return Id;

    }
    public void setId(String pId){
        Id=pId;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String pNombre){
        Nombre=pNombre;
    }
    public String getDescripcion(){
        return Descripcion;
    }
    public void setDescripción(String pDescripcion){
        Descripcion=pDescripcion;
    }
    public String getUrlImagen(){
        return UrlImagen;
    }
    public void setUrlImagen(String pUrlImagen){
        UrlImagen=pUrlImagen;
    }
    public Date getFecha(){
        return Fecha;
    }
    public void setFecha(Date pFecha){
        Fecha=pFecha;
    }
    public String getCosto(){
        return Costo;
    }
    public void setCosto(String pCosto){
        Costo=pCosto;
    }
    public String getDireccion(){
        return Direccion;
    }
    public void setDireccion(String pdireccion){
        Direccion=pdireccion;
    }
}
