package com.example.concesionario.service;

import com.example.concesionario.model.Cliente;
import com.example.concesionario.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes() {
        try {
            return clienteRepository.findAll();
        } catch (Exception error) {
            throw new RuntimeException("Error al obtener los clientes", error);
        }
    }

    public Cliente getClienteById(Integer id) {
        try {
            return clienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrado"));
        } catch (Exception error) {
            throw new RuntimeException("Error al obtener el cliente con ID " + id, error);
        }
    }

    public Cliente saveCliente(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception error) {
            throw new RuntimeException("Error al guardar el cliente", error);
        }
    }

    public void deleteCliente(Integer id) {
        try {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrado"));
            clienteRepository.delete(cliente);
        } catch (Exception error) {
            throw new RuntimeException("Error al eliminar el cliente con ID " + id, error);
        }
    }
}
