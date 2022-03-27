package co.udea.edu.mobe.controller;

import co.udea.edu.mobe.entity.StoreEntity;
import co.udea.edu.mobe.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/tiendas/{id}")
    public List<StoreEntity> getStoresByCategory(@PathVariable Integer id) {
        return storeService.getStoresByCategory(id);
    }
}
