package co.udea.edu.mobe.service;

import co.udea.edu.mobe.entity.ProductEntity;
import co.udea.edu.mobe.repositoryjpa.ProductRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepositoryJPA productRepositoryJPA;

    public List<ProductEntity> getProductsByStore(String idStore){
        return productRepositoryJPA.findProductsByStore(idStore);
    }
}
