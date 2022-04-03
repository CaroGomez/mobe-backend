package co.udea.edu.mobe.controller;

import co.udea.edu.mobe.entity.ProductEntity;
import co.udea.edu.mobe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/productos/{id}")
    public List<ProductEntity> getProductsByStore(@PathVariable String id){
        return productService.getProductsByStore(id);
    }
}
