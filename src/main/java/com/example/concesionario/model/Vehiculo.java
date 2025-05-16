package com.example.concesionario.model;
//Se puede llamar a todas las clases (JPA) si se pone un .*; al final
import jakarta.persistence.*;
/*import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Vehiculo {

    @Id
    private String placa;

    private String marca;
    private String modelo;
    private Integer valor;
    private String activo;
}*/
@Entity
public class Vehiculo {

    @Id
    private String placa;

    private String marca;
    private String modelo;
    private Integer valor;
    private String activo;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }


    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}

