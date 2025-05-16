package com.example.concesionario.service;

import com.example.concesionario.model.Venta;
import com.example.concesionario.model.Cliente;
import com.example.concesionario.model.Vehiculo;
import com.example.concesionario.repository.VentaRepository;
import com.example.concesionario.repository.ClienteRepository;
import com.example.concesionario.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;

    public VentaService(VentaRepository ventaRepository, ClienteRepository clienteRepository, VehiculoRepository vehiculoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Venta> getAllVentas() {
        try {
            return ventaRepository.findAll();
        } catch (Exception error) {
            throw new RuntimeException("Error al obtener las ventas", error);
        }
    }

    public Venta getVentaById(Integer id) {
        try {
            return ventaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Venta con ID " + id + " no encontrada"));
        } catch (Exception error) {
            throw new RuntimeException("Error al buscar la venta con ID " + id, error);
        }
    }

    public Venta saveVenta(Integer idCliente, String placaVehiculo) {
        try {
            if (ventaRepository.findByVehiculoPlaca(placaVehiculo).isPresent()) {
                throw new RuntimeException("El vehículo con placa " + placaVehiculo + " ya fue vendido.");
            }

            Cliente cliente = clienteRepository.findById(idCliente)
                    .orElseThrow(() -> new RuntimeException("Cliente con ID " + idCliente + " no encontrado"));

            Vehiculo vehiculo = vehiculoRepository.findById(placaVehiculo)
                    .orElseThrow(() -> new RuntimeException("Vehículo con placa " + placaVehiculo + " no encontrado"));

            Venta venta = new Venta();
            venta.setCliente(cliente);
            venta.setVehiculo(vehiculo);
            venta.setFecha(LocalDate.now());

            return ventaRepository.save(venta);
        } catch (Exception error) {
            throw new RuntimeException("Error al guardar la venta", error);
        }
    }

    public void deleteVenta(Integer id) {
        try {
            Venta venta = ventaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Venta con ID " + id + " no encontrada"));
            ventaRepository.delete(venta);
        } catch (Exception error) {
            throw new RuntimeException("Error al eliminar la venta con ID " + id, error);
        }
    }
}
