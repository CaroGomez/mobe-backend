package co.udea.edu.mobe.oauth;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.repositoryjpa.ClientRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LoginService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private ClientRepositoryJPA clientRepositoryJPA;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientEntity clientEntity = clientRepositoryJPA.findClientEntityByEmail(username);

        if(clientEntity == null){
            logger.error("Error en el login: No existe el usuario en el sistema");
            throw new UsernameNotFoundException("Error en el login: No existe el usuario en el sistema");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new User(clientEntity.getEmail(), clientEntity.getPassword(), true, true, true, true, authorities);
    }


}
