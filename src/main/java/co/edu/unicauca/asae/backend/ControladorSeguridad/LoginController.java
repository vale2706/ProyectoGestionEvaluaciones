package co.edu.unicauca.asae.backend.ControladorSeguridad;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.request.LoginRequestDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.request.SignupRequestDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.response.JwtResponseDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.response.MessageResponseDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.services.AuthImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
  
  @Autowired
  AuthImpl objAuthImpl;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {    
    System.out.println("entrando a generar token");
    JwtResponseDTO token=this.objAuthImpl.authenticateUser(loginRequest);    
    return ResponseEntity.ok(token);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
    System.out.println("entrando a crear usuario");
    MessageResponseDTO mensajeRespuesta=this.objAuthImpl.registerUser(signUpRequest);
    return ResponseEntity.ok(mensajeRespuesta);
  }
}
