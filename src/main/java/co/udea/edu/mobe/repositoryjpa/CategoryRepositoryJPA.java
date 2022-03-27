package co.udea.edu.mobe.repositoryjpa;

import co.udea.edu.mobe.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepositoryJPA extends JpaRepository<CategoryEntity, Integer>{

    List<CategoryEntity> findAll();

}
