package com.nansoft.prored.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos on 01/10/2015.
 */
public class UsuarioRed
{
    @SerializedName("id")
    private String Id;

    @SerializedName("idusuario")
    private String IdUsuario;

    @SerializedName("idred")
    private String IdRed;

    @SerializedName("idcargo")
    private String IdCargo;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getIdRed() {
        return IdRed;
    }

    public void setIdRed(String idRed) {
        IdRed = idRed;
    }

    public String getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(String idCargo) {
        IdCargo = idCargo;
    }
}
