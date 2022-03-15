package co.udea.edu.mobe.repositoryjpa;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.model.ClientModel;
import co.udea.edu.mobe.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ClientEntity, String> {

  Optional<ClientModel> findByEmailAndPassword(String email, String password);

  Optional<UserModel> findFirstByLogin(String email);
  }

