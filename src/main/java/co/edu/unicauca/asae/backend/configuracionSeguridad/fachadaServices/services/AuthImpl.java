package co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Entidades.ERole;
import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Entidades.Role;
import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Entidades.User;
import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Repositorios.RoleRepository;
import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Repositorios.UserRepository;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.request.LoginRequestDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.request.SignupRequestDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.response.JwtResponseDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.response.MessageResponseDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.security.jwt.JwtUtils;
import co.edu.unicauca.asae.backend.configuracionSeguridad.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

public class AuthImpl implements AuthInt {
     @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return new JwtResponseDTO(jwt, 
        userDetails.getId(), 
        userDetails.getUsername(), 
        userDetails.getEmail(), 
        roles);
    }

    @Override
    public MessageResponseDTO registerUser(SignupRequestDTO signUpRequest) {
       
        User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "coor":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_COORDINADOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                    break;
                    case "prf":
                        Role modRole = roleRepository.findByName(ERole.ROLE_PROFESOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                    break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponseDTO("Usuario "+user.getUsername()+" creado exitosamente");
    }

    
}
