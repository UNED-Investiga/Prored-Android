package com.nansoft.prored.Model;

/**
 * Created by Carlos on 20/09/2015.
 */
public class Opcion
{
    private String id;
    private String nombre;
    private String urlImagen;

    public Opcion() {
    }

    public Opcion(String id, String nombre, String urlImagen)
    {
        this.id = id;
        this.nombre = nombre;
        this.urlImagen = urlImagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
