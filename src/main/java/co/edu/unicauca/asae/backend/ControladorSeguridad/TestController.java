package co.edu.unicauca.asae.backend.ControladorSeguridad;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Contenido publico";
  }

  @GetMapping("/user")
  //PreAuthorize verifica si un rol tiene autorizacion antes de acceder a un metodo 
  @PreAuthorize("hasRole('COORDINADOR') or hasRole('PROFESOR')")
  public String userAccess() {
    return "Contenido privado. Datos retornados para el api de usuarios.";
  }

  @GetMapping("/coor")
  @PreAuthorize("hasRole('COORDINADOR')")
  public String moderatorAccess() {
    return "Contenido privado. Datos retornados para el api de moderador.";
  }

  @GetMapping("/prf")
  @PreAuthorize("hasRole('PROFESOR')")
  public String adminAccess() {
    return "Contenido privado. Datos retornados para el api de administrador.";
  }
}
