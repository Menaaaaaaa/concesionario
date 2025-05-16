package com.example.concesionario.repository;

import com.example.concesionario.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    Optional<Venta> findByVehiculoPlaca(String placa);
}
