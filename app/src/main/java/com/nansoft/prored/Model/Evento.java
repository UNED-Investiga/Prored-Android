package com.nansoft.prored.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PC on 16/09/2015.
 */
public class Evento {

    @SerializedName("id")
    private String Id;

    @SerializedName("nombre")
    private String Nombre;

    @SerializedName("descripcion")
    private String Descripcion;

    @SerializedName("urlimagen")
    private String UrlImagen;

    @SerializedName("fecha")
    private String Fecha;

    @SerializedName("costo")
    private  String Costo;

    @SerializedName("direccion")
    private String Direccion;

    public Evento(String id, String nombre, String descripcion, String urlImagen, String fecha, String costo, String direccion) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
        UrlImagen = urlImagen;
        Fecha = fecha;
        Costo = costo;
        Direccion = direccion;
    }

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
    public String getFecha(){
        return Fecha;
    }
    public void setFecha(String pFecha){
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
//Se crea Constructor
    public Evento(){
        Id = "1";
        Nombre ="Reunion Semanal de Prored";
        Descripcion = "Reunion para tratar asuntos importantes acerca de los metodos de estudio de los estudiantes de la Uned";
        UrlImagen ="https://www.google.com/search?q=te+amo&hl=es-419&biw=1538&bih=815&site=webhp&tbm=isch&imgil=MTsSlcVsc2eUpM%253A%253B7b07s1k8HVeTZM%253Bhttp%25253A%25252F%25252Fwww.imge1.tk%25252Fte-amo-6%25252F&source=iu&pf=m&fir=MTsSlcVsc2eUpM%253A%252C7b07s1k8HVeTZM%252C_&usg=__VeO3UXyzBmNr0dja1s-DWXD92zM%3D&ved=0CCcQyjdqFQoTCPGopt7Wk8gCFQqlHgod7_4J8w&ei=LQYGVrHeNYrKeu_9p5gP#hl=es-419&tbm=isch&q=reunion&imgrc=9mlaKoXXvK2UOM%3A";
        Fecha = "01/10/2015";
        Costo ="Gratis";
        Direccion ="Vicerrectoria de Investigación";



    }


}
