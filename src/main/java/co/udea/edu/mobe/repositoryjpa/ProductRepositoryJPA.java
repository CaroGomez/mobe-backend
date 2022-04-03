package co.udea.edu.mobe.repositoryjpa;

import co.udea.edu.mobe.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<ProductEntity, Integer> {

    @Query("SELECT u FROM ProductEntity AS u JOIN StoreEntity AS s ON u.store = s.id " +
            " WHERE s.id = :id")
    public List<ProductEntity> findProductsByStore(@Param("id") String idStore);

}
