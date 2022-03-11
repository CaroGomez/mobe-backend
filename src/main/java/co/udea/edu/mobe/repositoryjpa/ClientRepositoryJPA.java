package co.udea.edu.mobe.repositoryjpa;

import co.udea.edu.mobe.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryJPA extends JpaRepository<ClientEntity, String> {


}
