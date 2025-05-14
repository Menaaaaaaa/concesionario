package com.example.concesionario.model;
//Se puede llamar a todas las clases (JPA) si se pone un .*; al final
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
}
