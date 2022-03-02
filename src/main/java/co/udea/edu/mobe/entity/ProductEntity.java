package co.udea.edu.mobe.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "producto")
public class ProductEntity {

    @Id
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "typePhoto")
    private String typePhoto;

    @Column(name = "photo", length = 1000)
    private byte[] photo;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StoreEntity store;

}
