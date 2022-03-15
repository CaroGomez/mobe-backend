package co.udea.edu.mobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "usuario")
public class UserModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;

        private String name;
        private String lastname;
        private String email;
        private String password;
        private String cellphone;
        private String address;
        private String department;
        private String city;


@Override
public boolean equals(Object o){
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserModel that = (UserModel) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(email, that.email)
            && Objects.equals(password, that.password) && Objects.equals(cellphone, that.cellphone) && Objects.equals(address, that.address)
            && Objects.equals(department, that.department) && Objects.equals(city, that.city) ;
}

@Override
public int hashCode(){
        return Objects.hash(id, name, lastname, password, email, password, cellphone, address, department, city);
}

@Override
public String toString(){
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", city='" + city + '\'' +
                '}';
}

}
