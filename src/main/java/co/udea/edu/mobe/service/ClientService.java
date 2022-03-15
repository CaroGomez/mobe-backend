package co.udea.edu.mobe.service;

import co.udea.edu.mobe.model.UserModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.model.ClientModel;
import co.udea.edu.mobe.repositoryjpa.ClientRepositoryJPA;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    private Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepositoryJPA clientRepositoryJPA;
    private final ModelMapper mapper = new ModelMapper();

    public ResponseEntity<Object> register(ClientModel clientModel){
        ClientEntity clientEntity = mapper.map(clientModel, ClientEntity.class);
        clientRepositoryJPA.save(clientEntity);
        return new ResponseEntity<>("creado", new HttpHeaders(), HttpStatus.OK);
    }

    public List<ClientEntity> getAllClient (){
        return clientRepositoryJPA.findAll();
    }

    public Optional<ClientEntity> findById (String id) {
        return clientRepositoryJPA.findById(id);
    }


}
