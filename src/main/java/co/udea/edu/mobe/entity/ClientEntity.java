package co.udea.edu.mobe.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "cliente")
public class ClientEntity implements Serializable {

    @Id
    @Column(nullable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length=60)
    private String password;

    @Column(nullable = false)
    private String cellphone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String city;

}
