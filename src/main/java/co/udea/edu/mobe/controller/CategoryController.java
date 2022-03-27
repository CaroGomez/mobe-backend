package co.udea.edu.mobe.controller;

import co.udea.edu.mobe.entity.CategoryEntity;
import co.udea.edu.mobe.model.CategoryModel;
import co.udea.edu.mobe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorias")
    public List<CategoryEntity> getCategories() {
        return categoryService.getCategories();
    }
}
