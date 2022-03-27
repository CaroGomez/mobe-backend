package co.udea.edu.mobe.service;

import co.udea.edu.mobe.entity.CategoryEntity;
import co.udea.edu.mobe.repositoryjpa.CategoryRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepositoryJPA categoryRepositoryJPA;

    public List<CategoryEntity> getCategories() {
        return categoryRepositoryJPA.findAll();
    }
}
