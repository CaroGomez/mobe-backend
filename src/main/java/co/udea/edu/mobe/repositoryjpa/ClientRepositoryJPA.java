package co.udea.edu.mobe.repositoryjpa;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.model.ClientModel;
import co.udea.edu.mobe.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepositoryJPA extends JpaRepository<ClientEntity, String> {

     Optional<ClientModel> findByLoginAndPassword(String email, String password);

     Optional<UserModel> findFirstByLogin(String email);

}
