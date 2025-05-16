package com.example.concesionario.service;

import com.example.concesionario.model.Vehiculo;
import com.example.concesionario.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> getAllVehiculos() {
        try {
            return vehiculoRepository.findAll();
        } catch (Exception error) {
            throw new RuntimeException("Error al obtener la lista de vehículos", error);
        }
    }

    public Vehiculo getVehiculoByPlaca(String placa) {
        try {
            return vehiculoRepository.findById(placa)
                    .orElseThrow(() -> new RuntimeException("Vehículo con placa " + placa + " no encontrado"));
        } catch (Exception error) {
            throw new RuntimeException("Error al buscar el vehículo con placa " + placa, error);
        }
    }

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        try {
            return vehiculoRepository.save(vehiculo);
        } catch (Exception error) {
            throw new RuntimeException("Error al guardar el vehículo", error);
        }
    }

    public Vehiculo updateVehiculo(String placa, Vehiculo vehiculoActualizado) {
        try {
            Vehiculo existente = vehiculoRepository.findByPlaca(placa)
                    .orElseThrow(() -> new RuntimeException("Vehículo con placa " + placa + " no encontrado"));

            existente.setMarca(vehiculoActualizado.getMarca());
            existente.setModelo(vehiculoActualizado.getModelo());
            existente.setValor(vehiculoActualizado.getValor());
            existente.setActivo(vehiculoActualizado.getActivo());

            return vehiculoRepository.save(existente);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el vehículo con placa " + placa, e);
        }
    }


    public void deleteVehiculo(String placa) {
        try {
            Vehiculo vehiculo = vehiculoRepository.findById(placa)
                    .orElseThrow(() -> new RuntimeException("Vehículo con placa " + placa + " no encontrado"));
            vehiculoRepository.delete(vehiculo);
        } catch (Exception error) {
            throw new RuntimeException("Error al eliminar el vehículo con placa " + placa, error);
        }
    }
}
