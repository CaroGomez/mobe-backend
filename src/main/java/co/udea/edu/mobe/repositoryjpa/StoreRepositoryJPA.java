package co.udea.edu.mobe.repositoryjpa;

import co.udea.edu.mobe.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepositoryJPA extends JpaRepository<StoreEntity, String> {

    @Query("SELECT t FROM CategoryEntity AS c JOIN c.stores AS cs JOIN StoreEntity AS t ON cs.id = t.id" +
            " WHERE c.id = :id_category")
    List<StoreEntity> getStoreByCategory(@Param("id_category") Integer idCategory);
}
