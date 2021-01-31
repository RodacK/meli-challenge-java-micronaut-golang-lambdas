package com.meli.enums;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;

@Getter
@Introspected
public enum MessagesEnums {

    //-------------top-secret-save
    GET_SATELLITE_ERROR("500","100","Error al intentar obtener los satelites."),
    SAVE_SATELLITE_ERROR("500","101","Error al intentar guardar el satelite %s."),
    GET_SATELLITE_SUCCESSFUL("200","102","Se pudieron obtener los satelites."),
    SAVE_SATELLITE_SUCCESSFUL("201","103","Informacion del Satelite %s guardado."),
    INVALID_SATELLITE_NAME("422","103","Nombre del Satelite a guardar no valido."),
    INVALID_SATELLITE_MESSAGE("422","104","Mensaje del Satelite a guardar no valido. Verifique que no este vacio o nulo."),
    //-------------top-secret-get
    SATELLITE_NOT_FOUND("422","105","No se puede completar el proceso porque falta informacion del satelite %s."),
    TRILATERATION_ERROR("422","106","No se puede completar el proceso porque los datos ingresados previamente vuelven indeterminado el resultado"),
    //-------------top-secret
    INVALID_DISTANCES("422","107","No se puede completar el proceso, verifique que las distancias no sea negativas");

    private String httpCode;
    private String code;
    private String message;

    MessagesEnums(String httpCode,String code, String message){
        this.httpCode = httpCode;
        this.message = message;
        this.code = code;
    }

    public String getAll(){
        return (new StringBuffer(httpCode).append("-").append(code).append("-").append(message)).toString();
    }
}
