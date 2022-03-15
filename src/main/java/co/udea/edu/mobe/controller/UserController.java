package co.udea.edu.mobe.controller;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.model.ClientModel;
import co.udea.edu.mobe.model.UserModel;
import co.udea.edu.mobe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
        return "register_page";

    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        System.out.println("register request: " + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getId(), userModel.getName(),
                userModel.getLastname(),
                userModel.getEmail(),
        userModel.getPassword(), userModel.getCellphone(), userModel.getAddress(), userModel.getDepartment(),
                userModel.getCity());
        return registeredUser == null ? "error_page" : "redirect:/login";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model){
        System.out.println("login request: " + userModel);
        UserModel authenticated = userService.authenticate(userModel.getEmail(), userModel.getPassword());
        if(authenticated != null){
            model.addAttribute("userEmail", authenticated.getEmail());
            System.out.print("página lista de categoria");
        }else{
            System.out.print("el usuario o contraseña es icorrecto");
        }
        return null;

    }

/*
    @GetMapping("/cliente")
    private ResponseEntity<List<ClientEntity>> listarTodasLasPersona(){
        return ResponseEntity.ok(clientService.getAllClient());
    }

    @GetMapping (value = "/cliente/{id}")
    private ResponseEntity<Optional<ClientEntity>> listarPersonasPorID(@PathVariable("id") String id){
        return ResponseEntity.ok(clientService.findById(id));
    }


 */
}
