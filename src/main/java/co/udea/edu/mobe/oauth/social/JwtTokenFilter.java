package co.udea.edu.mobe.oauth.social;

import co.udea.edu.mobe.entity.ClientEntity;
import co.udea.edu.mobe.repositoryjpa.ClientRepositoryJPA;
import co.udea.edu.mobe.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    ClientRepositoryJPA clientRepositoryJPA;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            String email = jwtProvider.getEmailFromToken(token);
            ClientEntity clientEntity = clientRepositoryJPA.findClientEntityByEmail(email);
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(email, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            logger.error("fail en el método doFilter");
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest req) {
        String authReq = req.getHeader("Authorization");
        if (authReq != null && authReq.startsWith("Bearer")) {
            return authReq.replace("Bearer", "");
        }

        return null;
    }
}
