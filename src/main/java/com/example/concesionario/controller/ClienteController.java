package com.example.concesionario.controller;

import com.example.concesionario.model.Cliente;
import com.example.concesionario.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        try {
            return ResponseEntity.ok(clienteService.getAllClientes());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Integer id) {
        try {
            Cliente cliente = clienteService.getClienteById(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente nuevo = clienteService.saveCliente(cliente);
            return ResponseEntity.ok(nuevo);
        } catch (Exception error) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }
}
