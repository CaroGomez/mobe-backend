package co.udea.edu.mobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {

    private String id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String cellphone;
    private String address;
    private String department;
    private String city;

}
