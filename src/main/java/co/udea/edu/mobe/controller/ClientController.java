package co.udea.edu.mobe.controller;

import co.udea.edu.mobe.model.ClientModel;
import co.udea.edu.mobe.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin( origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST },
        allowCredentials = "true" )
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/registro")
    public ResponseEntity<Object> registerClient(@RequestBody ClientModel clientModel){
        return clientService.register(clientModel);
    }
}
