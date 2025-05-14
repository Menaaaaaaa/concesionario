package com.example.concesionario.controller;

import com.example.concesionario.model.Vehiculo;
import com.example.concesionario.service.VehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> listarVehiculos() {
        try {
            return ResponseEntity.ok(vehiculoService.getAllVehiculos());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> obtenerVehiculo(@PathVariable String placa) {
        try {
            Vehiculo vehiculo = vehiculoService.getVehiculoByPlaca(placa);
            return ResponseEntity.ok(vehiculo);
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Vehiculo> guardarVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            return ResponseEntity.ok(vehiculoService.saveVehiculo(vehiculo));
        } catch (Exception error) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable String placa) {
        try {
            vehiculoService.deleteVehiculo(placa);
            return ResponseEntity.noContent().build();
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }
}
