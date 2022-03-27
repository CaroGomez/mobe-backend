package co.udea.edu.mobe.service;

import co.udea.edu.mobe.entity.StoreEntity;
import co.udea.edu.mobe.repositoryjpa.StoreRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepositoryJPA storeRepositoryJPA;

    public List<StoreEntity> getStoresByCategory(Integer idCategory) {
        List<StoreEntity> returnedStores = storeRepositoryJPA.getStoreByCategory(idCategory);
        return returnedStores;
    }
}
