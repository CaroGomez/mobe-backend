package co.udea.edu.mobe.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cliente")
public class ClientEntity implements Serializable {

    @Id
    @Column(nullable = false)
    private String id;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String lastname;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String email;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String password;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String cellphone;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String address;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String department;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private String city;

    @JsonIgnoreProperties(value={ "clientEntity", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<CategoryEntity> categoryEntities;

    public ClientEntity() {
        this.categoryEntities = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CategoryEntity> getCategoryEntities() {
        return categoryEntities;
    }

    public void setCategoryEntities(List<CategoryEntity> categoryEntities) {
        this.categoryEntities = categoryEntities;
    }

    private static final long serialVersionUID = 1L;
}
