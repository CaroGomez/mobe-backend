package co.udea.edu.mobe.controller;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.model.ClientModel;
import co.udea.edu.mobe.model.UserModel;
import co.udea.edu.mobe.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }


    @PostMapping("/registro")
    public ResponseEntity<Object> registerClient(@RequestBody ClientModel clientModel){
        return clientService.register(clientModel);
    }


    @GetMapping("/cliente")
    private ResponseEntity<List<ClientEntity>> listarTodasLasPersona(){
        return ResponseEntity.ok(clientService.getAllClient());
    }

    @GetMapping (value = "/cliente/{id}")
    private ResponseEntity<Optional<ClientEntity>> listarPersonasPorID(@PathVariable ("id") String id){
        return ResponseEntity.ok(clientService.findById(id));
    }



}
