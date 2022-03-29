package co.udea.edu.mobe.repositoryjpa;

import co.udea.edu.mobe.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepositoryJPA extends JpaRepository<ClientEntity, String> {
    @Query("SELECT u FROM ClientEntity u WHERE u.id=:id")
    ClientEntity findClientById(@Param("id") String id);


    ClientEntity findClientEntityByEmail(String email);

    Optional<ClientEntity> findByEmail(String s);

}
