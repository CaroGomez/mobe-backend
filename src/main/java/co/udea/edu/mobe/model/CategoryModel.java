package co.udea.edu.mobe.model;

import co.udea.edu.mobe.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
public class CategoryModel {

    private Integer id;
    private String name;
    private List<StoreEntity> stores;
}
