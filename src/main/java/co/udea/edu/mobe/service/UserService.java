package co.udea.edu.mobe.service;

import co.udea.edu.mobe.model.UserModel;
import co.udea.edu.mobe.repositoryjpa.ClientRepositoryJPA;
import co.udea.edu.mobe.repositoryjpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String id, String name, String lastname, String email, String password,
                                  String cellphone, String address, String department, String city){
        if (email == null && password == null) {
            return null;
        }else{
            if(userRepository.findFirstByLogin(email).isPresent()){
                System.out.println("el email ya se encuentra registrado");
                return null;
            }
            UserModel userModel = new UserModel();
            UserModel.setId(id);
            UserModel.setName(name);
            UserModel.setLastname(lastname);
            UserModel.setEmail(email);
            UserModel.setPassword(password);
            UserModel.setCellphone(cellphone);
            UserModel.setAddress(address);
            UserModel.setDepartment(department);
           UserModel.setCity(city);

            return userRepository.save(userModel);


        }
    }


    public UserModel authenticate(String email, String password){
        return UserRepository.findByEmailAndPassword(email, password).orElse(null);

    }



}
