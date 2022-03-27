package co.udea.edu.mobe.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.model.ClientModel;
import co.udea.edu.mobe.repositoryjpa.ClientRepositoryJPA;

@Service
public class ClientService {

    @Autowired
    private ClientRepositoryJPA clientRepositoryJPA;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public ResponseEntity<Object> register(ClientModel clientModel) {
        ClientEntity clientEntity = mapper.map(clientModel, ClientEntity.class); //separar dependencias
        ClientEntity clientEntity1;
        try {
            clientEntity1 = clientRepositoryJPA.findClientById(clientEntity.getId());
        } catch (Exception e) {
            return new ResponseEntity<>("Ha ocurrido un error en la consulta", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (clientEntity1 == null) {
            String passwordEncoded = bCryptPasswordEncoder.encode(clientEntity.getPassword());
            clientEntity.setPassword(passwordEncoded);
            clientRepositoryJPA.save(clientEntity);
            return new ResponseEntity<>("creado", new HttpHeaders(), HttpStatus.OK);
        }

        return new ResponseEntity<>("el usuario ya se encuentra registrado", new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }
}
