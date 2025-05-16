package com.example.concesionario.controller;

import com.example.concesionario.model.Venta;
import com.example.concesionario.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        try {
            return ResponseEntity.ok(ventaService.getAllVentas());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVenta(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ventaService.getVentaById(id));
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestParam Integer idCliente, @RequestParam String placaVehiculo) {
        try {
            return ResponseEntity.ok(ventaService.saveVenta(idCliente, placaVehiculo));
        } catch (Exception error) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Integer id) {
        try {
            ventaService.deleteVenta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }
}
