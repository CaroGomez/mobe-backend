package co.udea.edu.mobe.controller;

import ch.qos.logback.core.net.server.Client;
import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.model.ClientModel;
import co.udea.edu.mobe.model.TokenModel;
import co.udea.edu.mobe.oauth.social.JwtProvider;
import co.udea.edu.mobe.service.ClientService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/oauht")
public class OauthController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    String googleClientId;

    @Value("${secretPsw}")
    String secretPsw;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    ClientService clientService;



    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenModel tokenModel) throws IOException {
        final NetHttpTransport netHttpTransport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier =
                new GoogleIdTokenVerifier.Builder(netHttpTransport, jacksonFactory)
                        .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenModel.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        ClientEntity clientEntity = new ClientEntity();
        if (clientService.validateUserEmail(payload.getEmail())) {
            clientEntity = clientService.getClientByEmail(payload.getEmail());
        } else {
            //TODO implement save client
        }

        return new ResponseEntity(payload, HttpStatus.OK);
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> facebook(@RequestBody TokenModel tokenModel) throws IOException {
        Facebook facebook = new FacebookTemplate(tokenModel.getValue());
        User user = facebook.fetchObject("me", User.class);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    private TokenModel login(ClientModel clientModel) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(clientModel.getEmail(), secretPsw)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        TokenModel tokenModel = new TokenModel();
        tokenModel.setValue(jwt);
        return tokenModel;
    }

    private ResponseEntity<Object> saveClient(String email) {
        ClientModel clientModel = null;
        return clientService.register(clientModel);
    }
}
