package co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.services;

import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.request.LoginRequestDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.request.SignupRequestDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.response.JwtResponseDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.response.MessageResponseDTO;

public interface AuthInt {
     JwtResponseDTO authenticateUser( LoginRequestDTO loginRequest);
   MessageResponseDTO registerUser(SignupRequestDTO signUpRequest);
}
 